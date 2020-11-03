package google.keytransparency.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.squareup.okhttp.ConnectionSpec;

import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.conscrypt.Conscrypt;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.security.KeyStore;
import java.security.Security;

import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import edu.emory.mathcs.backport.java.util.Collections;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.okhttp.OkHttpChannelBuilder;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class GetUserTest {
    private static final Logger logger = Logger.getLogger(GetUserTest.class.getName());
    KeyTransparencyGrpc.KeyTransparencyBlockingStub blockingStub;
    Gkt.GetUserRequest request;
    String address;
    int port;
    String dir;
    String user;
    ManagedChannel channel;
    private SSLContext context;

    static {
        Security.insertProviderAt(Conscrypt.newProvider(), 1);
    }

    @Before
    public void setUp() throws Exception {
        // Load CAs from an InputStream
        // (could be from a resource or ByteArrayInputStream or ...)
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        // Retrieve locally-stored self-signed certificate cert.pem
        InputStream caInput = new BufferedInputStream(new FileInputStream(System.getenv("GKT_CERT_PATH")));
        Certificate ca;
        try {
            ca = cf.generateCertificate(caInput);
            System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
        } finally {
            caInput.close();
        }

        // Create a KeyStore containing our trusted CAs
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(null, null);
        keyStore.setCertificateEntry("ca", ca);

        // Create a TrustManager that trusts the CAs in our KeyStore
        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        tmf.init(keyStore);

        // Create an SSLContext that uses our TrustManager
        context = SSLContext.getInstance("TLS");
        context.init(null, tmf.getTrustManagers(), null);

        // Tell the URLConnection to use a SocketFactory from our SSLContext
        //URL url = new URL("https://certs.cac.washington.edu/CAtest/");
        //HttpsURLConnection urlConnection =
        //        (HttpsURLConnection)url.openConnection();
        //urlConnection.setSSLSocketFactory(context.getSocketFactory());
        //InputStream in = urlConnection.getInputStream();
        //copyInputStreamToOutputStream(in, System.out);

        /* If testing on grpc-test.sandbox.googleapis.com, connection worked if response code should say UNIMPLEMENTED, and no "negotiation failed" error given. */
        String target = "grpc-test.sandbox.googleapis.com:443"; // Port 443 for json and grpc, as seen on line 91 in docker-compose.yml
        address = System.getenv("GKT_SERVER_ADDR2");//System.getenv("GKT_SERVER_ADDR");
        port = 443;
        user = "alice@domain.com"; // Email of the user you want
        dir = "default"; // Name of directory
        //SSLContext sc = SSLContext.getInstance("TLSv1.2");
        //sc.init(null,null,null);
        channel = OkHttpChannelBuilder
                .forAddress(address, port)
                .useTransportSecurity()
                .connectionSpec(ConnectionSpec.MODERN_TLS)
                .hostnameVerifier((hostname, session) -> hostname.equals(address))
                .sslSocketFactory(context.getSocketFactory())
                .build();

        blockingStub = KeyTransparencyGrpc.newBlockingStub(channel);
        //KeyTransparencyGrpc.KeyTransparencyStub asyncStub = KeyTransparencyGrpc.newStub(channel);
        request = Gkt.GetUserRequest.newBuilder().setDirectoryId(dir).setUserId(user).build();
    }

    @Test
    public void getUser() {

        try {
            Gkt.GetUserResponse response = blockingStub.getUser(request);
            //System.out.println(response);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
        }
    }

    private byte[] reverseBytes(byte[] arr) {
        int len = arr.length;
        byte[] arr2 = new byte[len];
        for (int i = 0; i < arr.length; i++) {
            arr2[len - i - 1] = arr[i];
        }
        return arr2;
    }

    @Test
    public void verifyCommitment() {
        Gkt.GetUserResponse response;
        try {
            response = blockingStub.getUser(request);
            //System.out.println(response);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }

        /*  Verify the commitment in 5 steps.
        *   1. Recreate HMAC(userId, data, nonce) */
        assertTrue(response.getLeaf().hasCommitted());
        Gkt.Committed committed = response.getLeaf().getCommitted();
        /* Create hmac and initialize with key. First tried SHA256Digest() */
        HMac hmac_sha256 = new HMac(new SHA256Digest());
        byte[] fixed_key = {(byte) 0x19, (byte) 0x6e, (byte) 0x7e, (byte) 0x52, (byte) 0x84, (byte) 0xa7, (byte) 0xef, (byte) 0x93, (byte) 0x0e, (byte) 0xcb, (byte) 0x9a, (byte) 0x19, (byte) 0x78, (byte) 0x74, (byte) 0x97, (byte) 0x55};
        KeyParameter keyParameter = new KeyParameter(fixed_key);
        hmac_sha256.init(keyParameter);
        /* Append prefix */
        String prefix = "Key Transparency Commitment";
        hmac_sha256.update(prefix.getBytes(), 0, prefix.getBytes().length);
        /* Append nonce */
        hmac_sha256.update(committed.getKey().toByteArray(), 0, committed.getKey().toByteArray().length);
        /* Append userId length */
        long userlen = user.length() & 0xFFFFFFFFL;
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(userlen);
        byte[] userlenbytes = reverseBytes(buffer.array());
        hmac_sha256.update(userlenbytes, 0, userlenbytes.length);
        /* Append userId */
        hmac_sha256.update(user.getBytes(), 0, user.getBytes().length);
        /* Append committed data */
        byte[] data = committed.getData().toByteArray();
        hmac_sha256.update(committed.getData().toByteArray(), 0, committed.getData().toByteArray().length);
        /* Hash input and return digest */
        byte[] hmac_output = new byte[hmac_sha256.getMacSize()];
        hmac_sha256.doFinal(hmac_output, 0);

        /* 2. Retrieve stored commitment */
        byte[] leaf_value = response.getLeaf().getMapInclusion().getLeaf().getLeafValue().toByteArray();
        //EntryOuterClass.SignedEntry.Builder bd = EntryOuterClass.SignedEntry.newBuilder();
        EntryOuterClass.SignedEntry signedEntry;
        try {
            signedEntry = EntryOuterClass.SignedEntry.parseFrom(leaf_value);
        } catch (InvalidProtocolBufferException e) {
            logger.log(Level.WARNING, "SignedEntry.parseFrom failed: {0}", e.getUnfinishedMessage());
            return;
        }
        assertNotNull(signedEntry);
        ByteString entrybs = signedEntry.getEntry();

        EntryOuterClass.Entry entry;
        try {
            entry = EntryOuterClass.Entry.parseFrom(entrybs);
        } catch (InvalidProtocolBufferException e) {
            logger.log(Level.WARNING, "Entry.parseFrom failed: {0}", e.getUnfinishedMessage());
            return;
        }

        ByteString commitmentbs = entry.getCommitment();

        /* 3. Assert the stored commitment and HMAC are equal */
        assertEquals(commitmentbs.size(), hmac_output.length);
        System.out.printf("Commit: ");
        for (byte b : commitmentbs.toByteArray()) {
            System.out.printf("%x ", b);
        }
        System.out.printf("\nDigest: ");
        for (byte b : hmac_output) {
            System.out.printf("%x ", b);
        }
        assertEquals(commitmentbs.toByteArray(), hmac_output);
    }
}
