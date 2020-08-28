package google.keytransparency.v1;

import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.params.KeyParameter;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLSocketFactory;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
//import io.grpc.netty.NettyChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.okhttp.OkHttpChannelBuilder;

import org.bouncycastle.crypto.macs.HMac;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class GetUserTest {
    String target = "grpc-test.sandbox.googleapis.com:443"; // Port 443 for json and grpc, as seen on line 91 in docker-compose.yml
    int port = 443;
    String user = "alice@domain.com"; //email of the user you want
    String dir = "default"; //for now always this
    private static final Logger logger = Logger.getLogger(GetUserTest.class.getName());
    //ManagedChannel channel = ManagedChannelBuilder.forTarget(target).useTransportSecurity().build();
    //ManagedChannel channel = ManagedChannelBuilder.forAddress(address, port).useTransportSecurity().build();
    //ManagedChannel channel = OkHttpChannelBuilder.forAddress(address, port).socketFactory(SSLSocketFactory.getDefault()).build();
    ManagedChannel channel = OkHttpChannelBuilder.forTarget(target).useTransportSecurity().build();

    @Test
    public void getUser() {
        //ManagedChannelBuilder builder = ManagedChannelBuilder.forAddress("johnbrooke.cs.byu.edu", 8080)
        KeyTransparencyGrpc.KeyTransparencyBlockingStub blockingStub = KeyTransparencyGrpc.newBlockingStub(channel);
        //KeyTransparencyGrpc.KeyTransparencyStub asyncStub = KeyTransparencyGrpc.newStub(channel);

        Gkt.GetUserRequest request = Gkt.GetUserRequest.newBuilder().setDirectoryId(dir).setUserId(user).build();
        try {
            Gkt.GetUserResponse response = blockingStub.getUser(request);
            System.out.println(response);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            //logger.log(Level.WARNING, "{0}", e.getStackTrace());
            return;
        }
    }

/*
    @Test
    public void verifyCommitment() {
        // Get the GetUserResponse
        KeyTransparencyGrpc.KeyTransparencyBlockingStub blockingStub = KeyTransparencyGrpc.newBlockingStub(channel);
        Gkt.GetUserRequest request = Gkt.GetUserRequest.newBuilder().setDirectoryId(dir).setUserId(user).build();
        Gkt.GetUserResponse response;
        try {
            response = blockingStub.getUser(request);
            System.out.println(response);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            //logger.log(Level.WARNING, "{0}", e.getStackTrace());
            return;
        }

        // Verify the commitment
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
*/
}
