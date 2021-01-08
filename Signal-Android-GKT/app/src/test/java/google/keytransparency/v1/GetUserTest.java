package google.keytransparency.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.squareup.okhttp.ConnectionSpec;

import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.conscrypt.Conscrypt;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import io.grpc.ManagedChannel;
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
        System.out.println("Running setUp...");
        // Load CAs from an InputStream
        // (could be from a resource or ByteArrayInputStream or ...)
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        // Retrieve locally-stored self-signed certificate cert.pem
        InputStream caInput = new BufferedInputStream(new FileInputStream("/home/jordan/cert.pem"/*System.getenv("GKT_CERT_PATH")*/));
        Certificate ca;
        try {
            ca = cf.generateCertificate(caInput);
            //System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
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

        System.out.println("✓ User retrieved!");
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

        /*  Verify the commitment in 3 steps.
         *   1. Recreate HMAC(prefix, nonce, len(userId), userId, data) */
        assertTrue(response.getLeaf().hasCommitted());
        Gkt.Committed committed = response.getLeaf().getCommitted();

        /* 1a. Create hmac and initialize with key. */
        HMac hmac_sha256 = new HMac(new SHA256Digest());
        byte[] fixed_key = {(byte) 0x19, (byte) 0x6e, (byte) 0x7e, (byte) 0x52, (byte) 0x84, (byte) 0xa7, (byte) 0xef, (byte) 0x93, (byte) 0x0e, (byte) 0xcb, (byte) 0x9a, (byte) 0x19, (byte) 0x78, (byte) 0x74, (byte) 0x97, (byte) 0x55};
        KeyParameter keyParameter = new KeyParameter(fixed_key);
        hmac_sha256.init(keyParameter);

        /* 1b. Append prefix */
        String prefix = "Key Transparency Commitment";
        hmac_sha256.update(prefix.getBytes(), 0, prefix.getBytes().length);

        /* 1c. Append nonce */
        byte[] nonce = committed.getKey().toByteArray();
        hmac_sha256.update(nonce, 0, nonce.length);

        /* 1d. Append userId length */
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.putInt(user.length());
        hmac_sha256.update(buffer.array(), 0, buffer.array().length);

        /* 1e. Append userId */
        hmac_sha256.update(user.getBytes(), 0, user.getBytes().length);

        /* 1f. Append committed data */
        byte[] data = committed.getData().toByteArray();
        hmac_sha256.update(data, 0, data.length);

        /* 1g. Hash input and return digest */
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
        byte[] commitment_bytearr = commitmentbs.toByteArray();
        for (int i = 0; i < hmac_output.length; i++) {
            assertEquals(commitment_bytearr[i], hmac_output[i]);
        }

        System.out.println("✓ Commitment verified!");
    }

    @Test
    public void verifyVRFproof() {
        Gkt.GetUserResponse response;
        try {
            response = blockingStub.getUser(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }

        /* Verify the VRF proof to ensure the leaf index */
        ByteString vrfProof = response.getLeaf().getVrfProof();
        assertNotNull(vrfProof);

        assertEquals(64+65, vrfProof.size());

        /* Parse proof into s, t, and vrf. */
        ByteString s = vrfProof.substring(0, 32);
        ByteString t = vrfProof.substring(32, 64);
        ByteString vrf = vrfProof.substring(64, 64+65);

        //for (Enumeration e = CustomNamedCurves.getNames(); e.hasMoreElements();) {
        //    System.out.println(e.nextElement());
        //}
        X9ECParameters x9ECParameters = CustomNamedCurves.getByName("secp256r1");
        ECCurve curve = x9ECParameters.getCurve();
        /* Base point G for secp256r1 or P-256 curve
           from Appendix D of https://oag.ca.gov/sites/all/files/agweb/pdfs/erds1/fips_pub_07_2013.pdf.
        */
        BigInteger Gx = new BigInteger(1, new byte[]
                {(byte)0x6b,(byte)0x17,(byte)0xd1,(byte)0xf2, (byte)0xe1,(byte)0x2c,(byte)0x42,(byte)0x47,
                 (byte)0xf8,(byte)0xbc,(byte)0xe6,(byte)0xe5, (byte)0x63,(byte)0xa4,(byte)0x40,(byte)0xf2,
                 (byte)0x77,(byte)0x03,(byte)0x7d,(byte)0x81, (byte)0x2d,(byte)0xeb,(byte)0x33,(byte)0xa0,
                 (byte)0xf4,(byte)0xa1,(byte)0x39,(byte)0x45, (byte)0xd8,(byte)0x98,(byte)0xc2,(byte)0x96});
        BigInteger Gy = new BigInteger(1, new byte[]
                {(byte)0x4f,(byte)0xe3,(byte)0x42,(byte)0xe2, (byte)0xfe,(byte)0x1a,(byte)0x7f,(byte)0x9b,
                 (byte)0x8e,(byte)0xe7,(byte)0xeb,(byte)0x4a, (byte)0x7c,(byte)0x0f,(byte)0x9e,(byte)0x16,
                 (byte)0x2b,(byte)0xce,(byte)0x33,(byte)0x57, (byte)0x6b,(byte)0x31,(byte)0x5e,(byte)0xce,
                 (byte)0xcb,(byte)0xb6,(byte)0x40,(byte)0x68, (byte)0x37,(byte)0xbf,(byte)0x51,(byte)0xf5});
        ECPoint GPoint = curve.validatePoint(Gx, Gy);
        ECPoint uHPoint = unmarshal(curve, vrf).normalize();
        assertNotNull(uHPoint);

        /* Perform scalar multiplication; [t]G + [s]([k]G) = [t+ks]G */
        //byte[] tcpy = new byte[] {(byte)0xd2,0x5b,0x61,(byte)0xe3,(byte)0xa0,(byte)0xf5,(byte)0x89,(byte)0xe6,0x26,0x64,0x0a,0x15,0x22,0x04,0x7e,(byte)0x85,0x37,0x34,(byte)0xb0,(byte)0xf9,0x7f,0x19,(byte)0xf3,0x4b,(byte)0xf2,0x24,(byte)0xcd,(byte)0xe1,0x37,0x27,(byte)0xeb,0x28};
        BigInteger tbi = new BigInteger(1, /*tcpy*/t.toByteArray());
        ECPoint tGPoint = GPoint.multiply(tbi).normalize();
        BigInteger tGx = tGPoint.getAffineXCoord().toBigInteger();
        BigInteger tGy = tGPoint.getAffineYCoord().toBigInteger();

        /* Need pk.X and pk.Y. pk is crypto/ecdsa.PublicKey.
           pk.X = 3ae09f14f25911d1e4fcbcf092123290cb6cabebfe416362ec794fdf4bd513c9
           pk.Y = 23d6688367c70fd3ea0c0c3baa39fa965ccd1279dd41a26542c4a133472dccf2
           TODO: Find programmatic way of retrieving the public key.
         */
        byte[] pkX_bytes = {(byte)0x3a, (byte)0xe0, (byte)0x9f, (byte)0x14, (byte)0xf2, (byte)0x59, (byte)0x11,
                (byte)0xd1, (byte)0xe4, (byte)0xfc, (byte)0xbc, (byte)0xf0, (byte)0x92, (byte)0x12, (byte)0x32,
                (byte)0x90, (byte)0xcb, (byte)0x6c, (byte)0xab, (byte)0xeb, (byte)0xfe, (byte)0x41, (byte)0x63,
                (byte)0x62, (byte)0xec, (byte)0x79, (byte)0x4f, (byte)0xdf, (byte)0x4b, (byte)0xd5, (byte)0x13, (byte)0xc9};
        byte[] pkY_bytes = {(byte)0x23, (byte)0xd6, (byte)0x68, (byte)0x83, (byte)0x67, (byte)0xc7, (byte)0x0f,
                (byte)0xd3, (byte)0xea, (byte)0x0c, (byte)0x0c, (byte)0x3b, (byte)0xaa, (byte)0x39, (byte)0xfa,
                (byte)0x96, (byte)0x5c, (byte)0xcd, (byte)0x12, (byte)0x79, (byte)0xdd, (byte)0x41, (byte)0xa2,
                (byte)0x65, (byte)0x42, (byte)0xc4, (byte)0xa1, (byte)0x33, (byte)0x47, (byte)0x2d, (byte)0xcc, (byte)0xf2};
        BigInteger pkX = new BigInteger(1, pkX_bytes);
        BigInteger pkY = new BigInteger(1, pkY_bytes);
        ECPoint pkPoint = curve.createPoint(pkX, pkY);

        //byte[] scpy = new byte[] {0x1c,(byte)0xfe,0x25,0x6c,(byte)0xc1,0x21,(byte)0xa4,(byte)0xcf,(byte)0xfc,(byte)0x8d,0x26,(byte)0xb2,0x71,0x0a,0x19,(byte)0xfc,(byte)0xd0,0x11,(byte)0xda,(byte)0xc7,(byte)0xab,(byte)0xd6,(byte)0x86,0x63,(byte)0xc2,0x3d,0x19,0x21,(byte)0xa7,0x67,(byte)0xb6,(byte)0x93};
        BigInteger sbi = new BigInteger(1, /*scpy*/s.toByteArray());
        ECPoint ksGPoint = pkPoint.multiply(sbi).normalize();
        ECPoint tksGPoint = tGPoint.add(ksGPoint).normalize();
        BigInteger tksGx = tksGPoint.getAffineXCoord().toBigInteger();
        BigInteger tksGy = tksGPoint.getAffineYCoord().toBigInteger();

        /* H = H1(m) where m=userId
           [t]H + [s]VRF = [t+ks]H
         */
        ECPoint HPoint = H1(curve, user).normalize();
        BigInteger Hx = HPoint.getAffineXCoord().toBigInteger();
        BigInteger Hy = HPoint.getAffineYCoord().toBigInteger();
        ECPoint tHPoint = HPoint.multiply(tbi).normalize();
        BigInteger tHx = tHPoint.getAffineXCoord().toBigInteger();
        BigInteger tHy = tHPoint.getAffineYCoord().toBigInteger();
        ECPoint sHPoint = uHPoint.multiply(sbi).normalize();
        BigInteger sHx = sHPoint.getAffineXCoord().toBigInteger();
        BigInteger sHy = sHPoint.getAffineYCoord().toBigInteger();
        ECPoint tksHPoint = tHPoint.add(sHPoint).normalize();
        BigInteger tksHx = tksHPoint.getAffineXCoord().toBigInteger();
        BigInteger tksHy = tksHPoint.getAffineYCoord().toBigInteger();

        /* H2(G, H, [k]G, VRF, [t]G + [s]([k]G), [t]H + [s]VRF)
           = H2(G, H, [k]G, VRF, [t+ks]G, [t+ks]H)
           = H2(G, H, [k]G, VRF, [r]G, [r]H)
         */
        byte[] m1 = marshal(curve, Gx, Gy);
        byte[] m2 = marshal(curve, removeLeadingZeroBytes(Hx), removeLeadingZeroBytes(Hy));
        byte[] m3 = marshal(curve, pkX, pkY);
        byte[] m4 = marshal(curve, removeLeadingZeroBytes(tksGx), removeLeadingZeroBytes(tksGy));
        byte[] m5 = marshal(curve, removeLeadingZeroBytes(tksHx), removeLeadingZeroBytes(tksHy));
        ByteBuffer b = ByteBuffer.allocate(m1.length + m2.length + m3.length + m4.length + m5.length + vrf.toByteArray().length);
        b.put(m1);
        b.put(m2);
        b.put(m3);
        b.put(vrf.toByteArray());
        b.put(m4);
        b.put(m5);
        BigInteger h2 = removeLeadingZeroBytes(H2(curve, b.array()));

        /*  Left pad h2 with zeros if needed. This will ensure that h2 is padded
            the same way s is. */
        ByteBuffer buf = ByteBuffer.allocate((32 - h2.toByteArray().length) + h2.toByteArray().length);
        byte[] tmp = new byte[32 - h2.toByteArray().length];
        buf.put(tmp);
        buf.put(h2.toByteArray());

        if (!Arrays.equals(s.toByteArray(), buf.array())) {
            logger.log(Level.WARNING, "Invalid VRF");
            assertTrue(Arrays.equals(s.toByteArray(), buf.array()));
        }

        System.out.println("✓ VRF verified!");

        MessageDigest h = null;
        try {
            h = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.WARNING, "Get instance failed: {0}", e.getCause());
        }
        byte[] vrf_digest = h.digest(vrf.toByteArray());
        byte[] expected = new byte[] {(byte)0x96,(byte)0x65,(byte)0x2b,(byte)0xed,(byte)0x1d,(byte)0x71,(byte)0x62,(byte)0x27,(byte)0x69,(byte)0xca,
                (byte)0x42,(byte)0xbe,(byte)0x94,(byte)0xe6,(byte)0x27,(byte)0xfe,(byte)0x64,(byte)0x51,(byte)0xa9,(byte)0x3b,(byte)0x04,(byte)0xbb,
                (byte)0x17,(byte)0xd5,(byte)0x84,(byte)0x5e,(byte)0x28,(byte)0xa3,(byte)0x63,(byte)0x56,(byte)0x20,(byte)0x5f};
        for (int i = 0; i < expected.length; i++) { assertEquals(expected[i], vrf_digest[i]); }
    }

    /*
     * Unmarshal a point into an x,y pair
     * based off https://golang.org/src/crypto/elliptic/elliptic.go?s=9365:9421#L340.
     * BouncyCastle docs https://www.bouncycastle.org/docs/docs1.5on/index.html.
     */
    public ECPoint unmarshal(ECCurve curve, ByteString vrf) {
        int byteLen = (curve.getFieldSize() + 7) / 8;
        assertEquals( 1 + 2 * byteLen, vrf.size() );
        assertEquals(4, (int) vrf.byteAt(0));

        BigInteger p = curve.getOrder();
        BigInteger uHx = new BigInteger(1, vrf.substring(1, 1+byteLen).toByteArray());
        //long x = bigInteger.longValue();
        BigInteger uHy = new BigInteger(1, vrf.substring(1+byteLen).toByteArray());
        //long y = bigInteger.longValue();
        assert uHx.compareTo(p) < 0;
        assert uHy.compareTo(p) < 0;
        return curve.validatePoint(uHx, uHy);
    }

    /*
     * Marshal converts a point on the curve into the uncompressed form specified in
     * section 4.3.6 of ANSI X9.62.
     * Based off https://golang.org/src/crypto/elliptic/elliptic.go?s=8578:8625#L305.
     */
    public byte[] marshal(ECCurve curve, BigInteger x, BigInteger y) {
        int byteLen = (curve.getFieldSize() + 7) / 8;
        byte[] ret = new byte[1+2*byteLen];
        ret[0] = 4; // uncompressed point
        System.arraycopy(x.toByteArray(), 0, ret, 1, byteLen);
        System.arraycopy(y.toByteArray(), 0, ret, 1+byteLen, byteLen);
        return ret;
    }

    /*
     * H2 hashes to an integer [1,N-1]
     */
    public BigInteger H2(ECCurve curve, byte[] m) {
        // NIST SP 800-90A § A.5.1: Simple discard method.
        int byteLen = (curve.getFieldSize() + 7) >> 3;
        MessageDigest h = null;
        try {
            h = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.WARNING, "Get instance failed: {0}", e.getCause());
        }
        for (int i = 0; ; i++) {
            h.reset();

            byte[] i_bytes = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(i).array();
            h.update(i_bytes); // byte-converted integers are signed, but I think i is small enough to avoid this.
            //System.out.print("m="); for (byte byt : m) { System.out.printf("%02x", byt); } System.out.println();
            byte[] b = h.digest(m);
            byte[] k_arr = new byte[byteLen];
            System.arraycopy(b, 0, k_arr, 0, byteLen);
            BigInteger k = new BigInteger(1, k_arr);
            //System.out.print("b="); for (byte byt: b) { System.out.printf("%02x", byt); } System.out.println();
            //System.out.print("k="); for (byte byt: k.toByteArray()) { System.out.printf("%02x", byt); } System.out.println();
            BigInteger one = new BigInteger(1, new byte[] {1});
            if (k.compareTo(curve.getOrder().subtract(one)) == -1) {
                return k.add(one);
            }
        }
    }

    /*
     * https://github.com/google/keytransparency/blob/master/core/crypto/vrf/p256/p256.go#L81
     * H1 hashes m to a curve point
     */
    public ECPoint H1(ECCurve curve, String m) {
        MessageDigest h = null;
        try {
            h = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.WARNING, "Get instance failed: {0}", e.getCause());
        }
        int bytelen = (curve.getFieldSize() + 7) >> 3;
        BigInteger x = null;
        BigInteger y = null;
        int i = 0;
        ECPoint point = null;
        while (x == null && i < 100) {
            h.reset();

            byte[] i_bytes = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(i).array();
            h.update(i_bytes); // byte-converted integers are signed, but I think i is small enough to avoid this.

            byte[] sum = new byte[65];
            sum[0] = (byte)2;

            byte[] digest = h.digest(m.getBytes());
            System.arraycopy(digest, 0, sum, 1, h.getDigestLength());

            byte[] tmp = new byte[bytelen+1];
            System.arraycopy(sum, 0, tmp, 0, bytelen+1);

            point = Unmarshal(curve, tmp);
            if (point != null) {
                x = point.getXCoord().toBigInteger();
                y = point.getYCoord().toBigInteger();
            }
            i++;
        }
        return curve.createPoint(x, y);
    }

    /*
     * https://github.com/google/keytransparency/blob/master/core/crypto/vrf/p256/unmarshal.go#L27
     * Unmarshal a compressed point in the form specified in section 4.3.6 of ANSI X9.62.
     */
    public ECPoint Unmarshal(ECCurve curve, byte[] data) {
        int byteLen = (curve.getFieldSize() + 7) >> 3;
        if ((data[0] & (~1)) != 2) {
            logger.log(Level.WARNING, "unrecognized point encoding");
        }
        if (data.length != 1+byteLen) {
            logger.log(Level.WARNING, "Lengths aren't correct.");
        }

        // Based on Routine 2.2.4 in NIST Mathematical routines paper
        byte[] tx_bytes = new byte[byteLen];
        System.arraycopy(data, 1, tx_bytes, 0, byteLen);
        BigInteger tx = new BigInteger(1, tx_bytes);
        BigInteger y2 = y2Func(curve, tx);
        BigInteger ty = defaultSqrt(y2, curve.getField().getCharacteristic());

        if (ty == null) {
            return null; // "y^2" is not a square: invalid point
        }

        BigInteger y2c = ty.multiply(ty).mod(curve.getField().getCharacteristic());
        if (y2c.compareTo(y2) != 0) {
            return null; // sqrt(y^2) != y2: invalid point
        }
        int ty_bytelen = ty.toByteArray().length;
        if ((ty.toByteArray()[ty_bytelen-1] & 1) != (data[0] & 1)) {
            ty = ty.subtract(new BigInteger(1, curve.getField().getCharacteristic().toByteArray()));
        }

        return curve.createPoint(tx, ty); // valid point; return
    }

    public BigInteger y2Func(ECCurve curve, BigInteger x) {
        /*  Use the curve equation to calculate y^2 given x.
            Only applies to curves of the form y^2 = x^3 - 3x + b.
         */
        BigInteger x3 = x.multiply(x);
        x3 = x3.multiply(x);
        BigInteger threeX = x.shiftLeft(1);
        threeX = threeX.add(x);
        x3 = x3.subtract(threeX);
        x3 = x3.add(curve.getB().toBigInteger());
        x3 = x3.mod(curve.getField().getCharacteristic());
        return x3;
    }

    /*
     * https://github.com/google/keytransparency/blob/master/core/crypto/vrf/p256/unmarshal.go#L74
     */
    public BigInteger defaultSqrt(BigInteger x, BigInteger p) {
        return modSqrt(x, p);
    }

    public BigInteger modSqrt(BigInteger x, BigInteger p) {
        // Naive approach; need to separate optimized approaches into separate functions.
        /*
        a = a.mod(p);
        BigInteger x = new BigInteger(1, new byte[] {2});
        BigInteger exp = new BigInteger(1, new byte[] {2});
        for (; x.longValue() < p.longValue(); x = x.add(new BigInteger(1, new byte[] {1}))) {
            // if x**2 mod p == a
            if (x.modPow(exp, p).equals(a)) {
                return x;
            }
        }
        return null;
         */
        switch (jacobi(x, p)) {
            case -1:
                return null; // x is not a square mod p
            case 0:
                return new BigInteger(1, new byte[]{0}); // sqrt(0) mod p = 0
            case 1:
                break;
        }
        if (x.longValue() < 0 || x.compareTo(p) >= 0) { // ensure 0 <= x < =
            x = x.mod(p);
        }

        String pabsstr = p.abs().toString();
        int p_lastdigit = pabsstr.charAt(pabsstr.length() - 1);
        if (p_lastdigit % 4 == 3) {
            // Check whether p is 3 mod 4, and if so, use the faster algorithm
            return modSqrt3Mod4Prime(x, p);
        }
        else if (p_lastdigit % 8 == 5) {
            // Check whether p is 5 mod 8, use Atkin's algorithm
            return modSqrt5Mod8Prime(x, p);
        }
        else {
            // Otherwise, use Tonelli-Shanks
            return modSqrtTonelliShanks(x, p);
        }
    }

    /*
     * modSqrt3Mod4 uses the identity
     * (a^((p+1)/4))^2  mod p
     * == u^(p+1)          mod p
     * == u^2              mod p
     * to calculate the square root of any quadratic residue mod p quickly for 3
     * mod 4 primes.
     */
    public BigInteger modSqrt3Mod4Prime(BigInteger x, BigInteger p) {
        BigInteger e = p.add(new BigInteger(1, new byte[]{1}));
        e = e.shiftRight(2);
        return x.modPow(e, p);
    }

    /*
     * modSqrt5Mod8 uses Atkin's observation that 2 is not a square mod p
     *     alpha == (2*a)^((p-5)/8)    mod p
     *     beta  == 2*a*alpha^2        mod p is a square root of -1
     *     b     == a*alpha*(beta-1)   mod p is a square root of a
     * to calculate the square root of any quadratic residue mod p quickly for 5
     * mod 8 primes.
     */
    public BigInteger modSqrt5Mod8Prime(BigInteger x, BigInteger p) {
        // p == 5 mod 8 implies p = e*8 + 5
        // e is the quotient and 5 the remainder on division by 8
        BigInteger e = p.shiftRight(3);
        BigInteger tx = x.shiftLeft(1);
        BigInteger alpha = tx.modPow(e, p);
        BigInteger beta = alpha.multiply(alpha);
        beta = beta.mod(p);
        beta = beta.multiply(tx);
        beta = beta.mod(p);
        beta = beta.subtract(new BigInteger(1, new byte[]{1}));
        beta = beta.multiply(x);
        beta = beta.mod(p);
        beta = beta.multiply(alpha);
        return beta.mod(p);
    }

    /*
     * modSqrtTonelliShanks uses the Tonelli-Shanks algorithm to find the
     * square root of a quadratic residue modulo any prime.
     */
    public BigInteger modSqrtTonelliShanks(BigInteger x, BigInteger p) {
        // Break p-1 into s*2^e such that s is odd.
        BigInteger intOne = new BigInteger(1, new byte[]{1});
        BigInteger s = p.subtract(intOne);
        int e = countTrailingZeroBits(s);
        s = s.shiftRight(e);

        // find some non-square n
        BigInteger n = new BigInteger(1, new byte[]{2});
        while (jacobi(n, p) != -1) {
            n = n.add(intOne);
        }

        // Core of the Tonelli-Shanks algorithm. Follows the description in
        // section 6 of 'Square roots from 1; 24, 51, 10 to Dan Shanks"
        // by Ezra Brown:
        // https://www.maa.org/sites/default/files/pdf/upload_library/22/Polya/07468342.di020786.02p0470a.pdf
        BigInteger y = s.add(intOne);
        y = y.shiftRight(1);
        y = x.modPow(y, p);
        BigInteger b = x.modPow(s, p);
        BigInteger g = n.modPow(s, p);
        int r = e;
        BigInteger t = null;
        while (true) {
            // find the least m such that ord_p(b) = 2^m
            int m = 0;
            t = b;
            while (t.compareTo(intOne) != 0) {
                t = t.multiply(t).mod(p);
                m++;
            }

            if (m == 0) {
                return y;
            }

            // t = g^(2^(r-m-1)) mod p
            t = new BigInteger(1, new byte[]{0}).setBit(r-m-1);
            t = g.modPow(t, p);
            g = t.multiply(t);
            g = g.mod(p); // g = g^(2^(r-m)) mod p
            y = y.multiply(t).mod(p);
            b = b.multiply(g).mod(p);
            r = m;
        }
    }

    public int jacobi(BigInteger x, BigInteger y) {
        String yabsstr = y.abs().toString();
        int y_lastdigit = yabsstr.charAt(yabsstr.length() - 1);
        if ( (y.abs().bitCount() == 0) || ((y_lastdigit & 1) == 0) ) {
            logger.log(Level.WARNING, "Invalid 2nd arg for Jacobi: need odd integer but got %l", y.longValue());
        }

        // We use the formulation described in chapter 2, section 2.4,
        // "The Yacas Book of Algorithms":
        // http://yacas.sourceforge.net/Algo.book.pdf

        BigInteger a = x;
        BigInteger b = y;
        BigInteger c = new BigInteger(1, new byte[]{0});
        int j = 1;

        BigInteger zero = new BigInteger(1, new byte[]{0});
        if (b.compareTo(zero) < 0) {
            if (a.compareTo(zero) < 0) {
                j = -1;
            }
        }

        BigInteger one = new BigInteger(1, new byte[]{1});
        int iters = 1;
        while (true) {
            if (b.compareTo(one) == 0) {
                //System.out.printf("%d. a=%s, b=%s, j=%d\n", iters, a.toString(), b.toString(), j);
                return j;
            }
            if (a.abs().bitCount() == 0) {
                //System.out.printf("%d. a.Bits()=0\n", iters);
                return 0;
            }
            a = a.mod(b);
            if (a.abs().bitCount() == 0) {
                //System.out.printf("%d. After a.Mod(&a, &b), a.Bits()=0\n", iters);
                return 0;
            }
            // a > 0
            // handle factors of 2 in 'a'
            int s = countTrailingZeroBits(a);
            int b_bytelen = b.abs().toByteArray().length;
            byte b_lastbyte = b.abs().toByteArray()[b_bytelen-1];
            if ((s & 1) != 0) {
                int bmod8 = b_lastbyte & 7;
                if (bmod8 == 3 || bmod8 == 5) {
                    j = -j;
                    //System.out.printf("%d. b mod 8=3||5, j=%d\n", iters, j);
                }
            }
            c = a.shiftRight(s);

            // swap numerator and denominator
            int c_bytelen = c.abs().toByteArray().length;
            byte c_lastbyte = c.abs().toByteArray()[c_bytelen-1];
            if ((b_lastbyte & 3) == 3 && (c_lastbyte & 3) == 3) {
                j = -j;
                //System.out.printf("%d. b[0]&3=c[0]&3=3, j=%d\n", iters, j);
            }
            a = b;
            b = c;
            iters += 1;
        }
    }

    public int countTrailingZeroBits(BigInteger x) {
        int index = 0;
        while (!x.testBit(index)) {
            index++;
        }
        return index;
    }

    /*
     * Because the Golang big.Int instances don't have leading zero bytes,
     * it's important to remove the leading zero bytes in BigInteger instances
     * when copying BigInteger byte arrays during VRF verification.
     */
    public BigInteger removeLeadingZeroBytes(BigInteger x) {
        int index = x.toByteArray().length * 8;
        int count = 0;
        while (!x.testBit(index)) {
            index--;
            count++;
        }
        byte[] xbytes = x.toByteArray();
        byte[] ret = new byte[x.toByteArray().length - (count / 8)];
        System.arraycopy(xbytes, count / 8, ret, 0, ret.length);
        return new BigInteger(ret);
    }

    public byte[] reverseBytes(byte[] b) {
        byte[] reverse_b = new byte[b.length];
        for (int i = b.length - 1; i >= 0; i--) {
            reverse_b[(b.length - 1) - i] = b[i];
        }
        return reverse_b;
    }
}
