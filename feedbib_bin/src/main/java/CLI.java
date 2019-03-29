import io.grpc.Server;
import io.grpc.ServerBuilder;
import picocli.CommandLine;

import java.io.IOException;

public class CLI implements Runnable {
    @CommandLine.Option(names = { "-h", "--help", "-?", "-help"}, usageHelp = true, description = "Display this help and exit")
    private boolean help;

    @CommandLine.Option(names = { "-p", "--port", "-port"}, required = true, description = "Port that the bin will start the service on")
    private int port;


    public void run() {
        Server server = ServerBuilder.forPort(port).addService(new FeedBinServiceImpl(new Bin())).build();
        try {
            server.start();
            server.awaitTermination();
        } catch (InterruptedException | IOException e) {
            System.out.println("Error creating bin server.");
            e.printStackTrace();
        }
    }
}
