package supervisor.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import supervisor.dialogs.StuffAmountDialog;
import supervisor.dialogs.StuffChangeNameDialog;
import supervisor.dialogs.UtilDialogs;
import supervisor.exceptions.OperationFailure;
import u1467085.feedbincontroller.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

/**
 * Controller for ListView cells that show feed bin status
 */
public class BinCell extends ListCell<ControllerBinStatusUpdate> implements Initializable {

    @FXML
    private Label addressLabel;
    @FXML
    private Label contentsLabel;
    @FXML
    private HBox boxContainer;
    @FXML
    private Button addButton;
    @FXML
    private Button inspectButton;
    @FXML
    private Button changeButton;
    @FXML
    private Button flushButton;

    public BinCell() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BinListCellView.fxml"));
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateItem(ControllerBinStatusUpdate item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty || item != null) {
            //set info labels inside the view
            addressLabel.setText(String.format("%s:%d", item.getRecord().getAddress(), item.getRecord().getPort()));
            contentsLabel.setText(String.format("%s %d/%d", item.getStuff().getStuffName(), item.getAmount(), item.getMaxAmount()));
            setGraphic(boxContainer);
        } else {
            //clear the cell
            setGraphic(null);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set up control buttons inside the cellview
        //button to add/remove contents of the bin
        addButton.setOnMouseClicked(event -> {
            //show dialog that allows use to specify amount of stuff to add/remove
            ControllerBinStatusUpdate currentItem = this.getItem();
            StuffAmountDialog dialog = new StuffAmountDialog(currentItem.getMaxAmount() - currentItem.getAmount(),
                    -currentItem.getAmount());
            dialog.showAndWait().ifPresent(result -> {
                try {
                    //send gRPC request to the controller server
                    OperationStatusResponse callResult = ServiceConnector.getInstance().getServiceStub().addStuff(
                            FillBin.newBuilder().setAmount(result).setId(currentItem.getRecord()).build()
                    ).get(10, TimeUnit.SECONDS);
                    //if response was not a SUCCESS show an error dialog
                    if (callResult.getResult() != OperationStatusResponse.OperationStatus.SUCCESS) {
                        UtilDialogs.ShowErrorDialog(new OperationFailure(), callResult.getMessage());
                    }
                } catch (Exception e) {
                    UtilDialogs.ShowErrorDialog(e, "Error contacting controller server.");
                }
            });
            event.consume();
        });

        //button that allows to update bin status information
        inspectButton.setOnMouseClicked(event -> {
            try {
                //send request to gRPC end-point
                ServiceConnector connector = ServiceConnector.getInstance();
                ControllerBinStatusUpdate binStatus = connector.getServiceStub().inspectBin(getItem().getRecord())
                        .get(10, TimeUnit.SECONDS);
                //update status list with a new status
                connector.updateBinRecord(binStatus);
            } catch (Exception e) {
                UtilDialogs.ShowErrorDialog(e, "Error contacting controller server.");
            }
            event.consume();
        });

        //button that allows user to change stuff that is assigned to the bin
        changeButton.setOnMouseClicked(event -> {
            //show dialog that allows to specify name for a new bin stuff
            ControllerBinStatusUpdate currentItem = this.getItem();
            StuffChangeNameDialog dialog = new StuffChangeNameDialog(currentItem.getStuff().getStuffName());
            dialog.showAndWait().ifPresent(result -> {
                try {
                    //send request to gRPC end-point
                    OperationStatusResponse callResult = ServiceConnector.getInstance().getServiceStub().changeStuff(
                            ChangeBinStuff.newBuilder().setStuff(Stuff.newBuilder().setStuffName(result))
                                    .setId(currentItem.getRecord()).build()).get(10, TimeUnit.SECONDS);
                    //show error dialog if controller server responded with an error operation status
                    if (callResult.getResult() != OperationStatusResponse.OperationStatus.SUCCESS) {
                        UtilDialogs.ShowErrorDialog(new OperationFailure(), callResult.getMessage());
                    }
                } catch (Exception e) {
                    UtilDialogs.ShowErrorDialog(e, "Error contacting controller server.");
                }
            });
            event.consume();
        });

        //button that allows to flush contents of the bin
        flushButton.setOnMouseClicked(event -> {
            ControllerBinStatusUpdate currentItem = this.getItem();
            try {
                //send request to gRPC end-point
                OperationStatusResponse callResult = ServiceConnector.getInstance().getServiceStub().flushBin(
                        currentItem.getRecord()).get(10, TimeUnit.SECONDS);
                //show error dialog if controller server responded with an error operation status
                if (callResult.getResult() != OperationStatusResponse.OperationStatus.SUCCESS) {
                    UtilDialogs.ShowErrorDialog(new OperationFailure(), callResult.getMessage());
                }
            } catch (Exception e) {
                UtilDialogs.ShowErrorDialog(e, "Error contacting controller server.");
            }
            event.consume();
        });
    }
}
