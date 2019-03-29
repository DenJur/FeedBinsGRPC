import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import u1467085.feedbins.BinStatusUpdate;
import u1467085.feedbins.FeedBinServiceGrpc;
import u1467085.feedbins.OperationStatusResponse;
import u1467085.feedbins.StuffAmount;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class ChatServer {
    public static void main(String[] args) throws InterruptedException, IOException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();
        FeedBinServiceGrpc.FeedBinServiceStub chatService = FeedBinServiceGrpc.newStub(channel);
        CountDownLatch finishedLatch = new CountDownLatch(1);

        chatService.registerForUpdates(Empty.newBuilder().build(), new StreamObserver<BinStatusUpdate>() {
            @Override
            public void onNext(BinStatusUpdate value) {
                System.out.println(value.getStuff().getStuffName()+" - "+value.getAmount().getStuffAmount());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        });

        Scanner scanner = new Scanner(System. in);
        String inputString = scanner. nextLine();
        while (!inputString.equalsIgnoreCase("quit")){
            chatService.addStuff(StuffAmount.newBuilder().setStuffAmount(Integer.parseInt(inputString)).build(), new StreamObserver<OperationStatusResponse>() {
                @Override
                public void onNext(OperationStatusResponse value) {
                    System.out.println(value.getResult()+" - "+value.getMessage());
                }

                @Override
                public void onError(Throwable t) {

                }

                @Override
                public void onCompleted() {

                }
            });
            inputString=scanner.nextLine();
        }

        channel.shutdown();
    }
}