package google.keytransparency.v1;

import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import static junit.framework.Assert.assertEquals;

public class GetUserTest {
    String target = "johnbrooke.cs.byu.edu:443";
    String user = "jon@arm.com"; //email of the user you want
    String dir = "default"; //for now always this
    private static final Logger logger = Logger.getLogger(GetUserTest.class.getName());
    ManagedChannel channel = ManagedChannelBuilder.forTarget(target).usePlaintext().build(); //Need to use AndroidChannelBuilder for the actual app

    @Test
    public void getUser() {
        //TODO: right port 443 or 8080? that is that port we used when running the client in the cmd line or 8080 as seen in line 91 of the docker-compose.yaml on official GKT repo
//      ManagedChannelBuilder builder = ManagedChannelBuilder.forAddress("johnbrooke.cs.byu.edu", 8080)
        KeyTransparencyGrpc.KeyTransparencyBlockingStub  blockingStub = KeyTransparencyGrpc.newBlockingStub(channel);
        //KeyTransparencyGrpc.KeyTransparencyStub asyncStub = KeyTransparencyGrpc.newStub(channel);

        Gkt.GetUserRequest request = Gkt.GetUserRequest.newBuilder().setDirectoryId(dir).setUserId(user).build();
        try {
            Gkt.GetUserResponse response = blockingStub.getUser(request);
            System.out.println(response);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
    }
}
