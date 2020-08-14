package google.keytransparency.v1;

import org.junit.Test;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import static junit.framework.Assert.assertEquals;

public class GetUserTest {
    String target = "johnbrooke.cs.byu.edu:8080";
    ManagedChannel channel = ManagedChannelBuilder.forTarget(target).usePlaintext().build();

    @Test
    public void getUser() {
        // todo get right port
//      ManagedChannelBuilder builder = ManagedChannelBuilder.forAddress("johnbrooke.cs.byu.edu", 8080)
        KeyTransparencyGrpc.KeyTransparencyBlockingStub  blockingStub = KeyTransparencyGrpc.newBlockingStub(channel);

        // TODO: first thing is figure out how to make a GetUserRequest. It has a private constructor
        // so you have to construct it another way, not sure
//      Gkt.GetUserRequest req = new Gkt.GetUserRequest()
//      Gkt.GetUserResponse resp = blockingStub.getUser(req)
//      assertEquals(whatever we think response should be, resp);
    }
}
