import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Server server = ServerBuilder.forPort(9090).addService(new FeedBinServiceImpl(new Bin())).build();
        try {
            server.start();
            server.awaitTermination();
        } catch (InterruptedException | IOException e) {
            System.out.println("Error creating bin server.");
            e.printStackTrace();
        }
    }
}
