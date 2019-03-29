import io.grpc.Server;
import io.grpc.ServerBuilder;
import picocli.CommandLine;

import java.io.IOException;

public class CLI implements Runnable {
    @CommandLine.Option(names = { "-h", "--help", "-?", "-help"}, usageHelp = true, description = "Display this help and exit")
    private boolean help;

    @CommandLine.Option(names = { "-p", "--port", "-port"}, required = true, description = "Port that the bin will start the service on")
    private int port;

    @CommandLine.Option(names = { "-s", "--size", "-size"}, defaultValue = "40", description = "Size of the bin in cubic liters")
    private int binSize;

    public void run() {
        //Create instance of gRPC server
        Server server = ServerBuilder.forPort(port).addService(new FeedBinServiceImpl(new Bin(binSize))).build();
        try {
            //Start the server and then either await server termination of app termination
            server.start();
            System.out.println("Server started at port "+port);
            server.awaitTermination();
        } catch (InterruptedException | IOException e) {
            System.out.println("Error running bin server.");
            e.printStackTrace();
        }
    }
}
