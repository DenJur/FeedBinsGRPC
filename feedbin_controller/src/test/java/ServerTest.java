import bin.Bin;
import bin.FeedBinServiceImpl;
import bin.exceptions.BinOverflow;
import bin.exceptions.BinUnderflow;
import controller.FeedBinControllerServiceImpl;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import u1467085.feedbincontroller.*;
import u1467085.feedbins.FeedBinServiceGrpc;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ServerTest {

    private FeedBinServiceGrpc.FeedBinServiceFutureStub serviceStub;
    private Bin bin;
    private ManagedChannel channel;
    private Server server;

    @BeforeEach
    public void TestSetup() throws IOException, BinUnderflow, BinOverflow {
        String serverName = InProcessServerBuilder.generateName();
        bin = new Bin(40);
        bin.changeStuffName("Product");
        bin.addAmount(20);
        server = InProcessServerBuilder.forName(serverName)
                .addService(new FeedBinServiceImpl(bin)).build().start();
        channel = InProcessChannelBuilder.forName(serverName).directExecutor().build();
        serviceStub = FeedBinServiceGrpc.newFutureStub(channel);
    }

    @AfterEach
    public void TestTeardown() throws InterruptedException {
        channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        server.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
    }

    @Test
    public void test1() throws IOException {
        Server server = ServerBuilder.forPort(9091).addService(new FeedBinControllerServiceImpl()).build();
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9091).usePlaintext().build();
        try {
            //Start the server and then either await server termination of app termination
            server.start();
            FeedBinControllerServiceGrpc.FeedBinControllerServiceBlockingStub serviceStub = FeedBinControllerServiceGrpc.newBlockingStub(channel);

            OperationStatusResponse r1 = serviceStub.addBin(BinId.newBuilder().setAddress("localhost").setPort(9090).build());
            System.out.println(r1.getResult() + " - " + r1.getMessage());

            OperationStatusResponse r2 = serviceStub.addStuff(FillBin.newBuilder().setId(BinId.newBuilder().setAddress("localhost").setPort(9090))
                    .setAmount(1).build());
            System.out.println(r2.getResult() + " - " + r2.getMessage());

            synchronized (this) {
                try {
                    this.wait(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e){}
    }
}
