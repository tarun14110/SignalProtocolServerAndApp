package google.keytransparency.v1;

import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import static junit.framework.Assert.assertEquals;

public class GetUserTest {
    String target = "johnbrooke.cs.byu.edu:8080";
    String user = "userIDstr"; //TODO: figure out what userID string needs to be to get the right user for the GetUserRequest
    String dir = "directoryIDstr"; //TODO: figure out what directroyID needs to be to get the right user for the GetUserRequest
    private static final Logger logger = Logger.getLogger(GetUserTest.class.getName());
    ManagedChannel channel = ManagedChannelBuilder.forTarget(target).usePlaintext().build();

    @Test
    public void getUser() {
        // todo get right port 443? that is that port we used when running the client in the cmd line or 8080 as seen in line 91 of the docker-compose.yaml on official GKT repo
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


        // so you have to construct it another way, not sure
//      Gkt.GetUserRequest req = new Gkt.GetUserRequest()
//      Gkt.GetUserResponse resp = blockingStub.getUser(req)
//      assertEquals(whatever we think response should be, resp);
    }
}
