package google.keytransparency.v1;

import com.squareup.okhttp.ConnectionSpec;

import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.conscrypt.Conscrypt;
import org.junit.Test;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.okhttp.OkHttpChannelBuilder;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class GetUserTest {
    static {
        Security.insertProviderAt(Conscrypt.newProvider(), 1);
    }
    /* If testing on grpc-test.sandbox.googleapis.com, connection worked if response code should say UNIMPLEMENTED, and no "negotiation failed" error given. */
    String target = "grpc-test.sandbox.googleapis.com:443"; // Port 443 for json and grpc, as seen on line 91 in docker-compose.yml
    String address = System.getenv("GKT_SERVER_ADDRESS");
    int port = 443;
    String user = "alice@domain.com"; // Email of the user you want
    String dir = "default"; // Name of directory
    private static final Logger logger = Logger.getLogger(GetUserTest.class.getName());

/*
    @Test
    public void getUser() {
        //SSLContext sc = SSLContext.getInstance("TLSv1.2");
        //sc.init(null,null,null);
        ManagedChannel channel = OkHttpChannelBuilder
                .forAddress(address, port)
                .useTransportSecurity()
                .connectionSpec(ConnectionSpec.MODERN_TLS)
                .hostnameVerifier((hostname, session) -> hostname.equals(address))
                //.sslSocketFactory(sc.getSocketFactory())
                .build();

        KeyTransparencyGrpc.KeyTransparencyBlockingStub blockingStub = KeyTransparencyGrpc.newBlockingStub(channel);
        //KeyTransparencyGrpc.KeyTransparencyStub asyncStub = KeyTransparencyGrpc.newStub(channel);

        Gkt.GetUserRequest request = Gkt.GetUserRequest.newBuilder().setDirectoryId(dir).setUserId(user).build();
        try {
            Gkt.GetUserResponse response = blockingStub.getUser(request);
            System.out.println(response);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
        }
    }
*/

    @Test
    public void verifyCommitment() {
        /* Get the GetUserResponse */
        ManagedChannel channel = OkHttpChannelBuilder
                .forAddress(address, port)
                .useTransportSecurity()
                .connectionSpec(ConnectionSpec.MODERN_TLS)
                .hostnameVerifier((hostname, session) -> hostname.equals(address))
                .build();

        KeyTransparencyGrpc.KeyTransparencyBlockingStub blockingStub = KeyTransparencyGrpc.newBlockingStub(channel);
        Gkt.GetUserRequest request = Gkt.GetUserRequest.newBuilder().setDirectoryId(dir).setUserId(user).build();
        Gkt.GetUserResponse response;
        try {
            response = blockingStub.getUser(request);
            System.out.println(response);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }

        /* Verify the commitment */
        assertTrue(response.getLeaf().hasCommitted());
        Gkt.Committed committed = response.getLeaf().getCommitted();
        HMac hmac_sha256 = new HMac(new SHA256Digest());
        KeyParameter keyParameter = new KeyParameter(committed.getKey().toByteArray());
        hmac_sha256.init(keyParameter);
        hmac_sha256.update(committed.getData().toByteArray(), 0, committed.getData().toByteArray().length);
        byte[] commitment = new byte[hmac_sha256.getMacSize()];
        hmac_sha256.doFinal(commitment, 0);
        byte[] leaf_value = response.getLeaf().getMapInclusion().getLeaf().getLeafValue().toByteArray();
        assertEquals(leaf_value, commitment);
    }
}
