package supervisor.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import supervisor.components.BinCell;
import supervisor.components.ServiceConnector;
import supervisor.dialogs.ConnectDialog;
import supervisor.dialogs.RecipeDialog;
import supervisor.dialogs.UtilDialogs;
import supervisor.exceptions.IngredientOperationException;
import supervisor.exceptions.OperationFailure;
import supervisor.exceptions.RecipeExecutionException;
import u1467085.feedbincontroller.BinId;
import u1467085.feedbincontroller.ControllerBinStatusUpdate;
import u1467085.feedbincontroller.OperationStatusResponse;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class MainSupervisorController implements Initializable {

    @FXML
    private Button connectController;
    @FXML
    private Button addBin;
    @FXML
    private Button prepareRecipe;
    @FXML
    private Button inspectAll;
    @FXML
    private ListView<ControllerBinStatusUpdate> binList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //setup bin status list
        ServiceConnector connector = ServiceConnector.getInstance();
        binList.setCellFactory(param -> new BinCell());
        binList.setItems(connector.getBinStatusList());

        connectController.setOnMouseClicked(event -> {
            ConnectDialog dialog = new ConnectDialog();
            dialog.showAndWait().ifPresent(result -> {
                //try to connect to the address user provided
                try {
                    ServiceConnector.getInstance().connectToController(result);
                } catch (Exception e) {
                    UtilDialogs.ShowErrorDialog(e, "Error contacting controller server.");
                }
            });
            event.consume();
        });

        addBin.setOnMouseClicked(event -> {
            ConnectDialog dialog = new ConnectDialog();
            dialog.showAndWait().ifPresent(result -> {
                try {
                    //send bin registration request to the controller service
                    OperationStatusResponse callResult = ServiceConnector.getInstance().getServiceStub().addBin(
                            BinId.newBuilder().setAddress(result.getKey()).setPort(result.getValue()).build()
                    ).get(10, TimeUnit.SECONDS);
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

        inspectAll.setOnMouseClicked(event -> {
            //call inspect bin method for every bin record
            connector.getBinStatusList().forEach(controllerBinStatusUpdate -> {
                try {
                    ControllerBinStatusUpdate binStatus = connector.getServiceStub()
                            .inspectBin(controllerBinStatusUpdate.getRecord()).get(10, TimeUnit.SECONDS);
                    connector.updateBinRecord(binStatus);
                } catch (Exception e) {
                    UtilDialogs.ShowErrorDialog(e, "Error contacting controller server.");
                }
            });
            event.consume();
        });

        prepareRecipe.setOnMouseClicked(event -> {
            RecipeDialog dialog = new RecipeDialog();
            dialog.showAndWait().ifPresent(result -> {
                try {
                    //try to execute resulting recipe
                    result.executeIfValid();
                    //if no exception was thrown then production was successful and we can show product result information
                    UtilDialogs.ShowInformationDialog(String.format("Produced %d cubic liters of %s",
                            result.getFinalProductAmount(), result.getFinalProductName()));
                } catch (RecipeExecutionException | IngredientOperationException e) {
                    UtilDialogs.ShowErrorDialog(e, e.getMessage());
                }
            });
            event.consume();
        });
    }
}
