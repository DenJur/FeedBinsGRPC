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
        ServiceConnector connector = ServiceConnector.getInstance();
        binList.setCellFactory(param -> new BinCell());
        binList.setItems(connector.getBinStatusList());

        connectController.setOnMouseClicked(event -> {
            ConnectDialog dialog = new ConnectDialog();
            dialog.showAndWait().ifPresent(result -> {
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
                    connectController.setDisable(true);
                    addBin.setDisable(true);
                    prepareRecipe.setDisable(true);
                    OperationStatusResponse callResult = ServiceConnector.getInstance().getServiceStub().addBin(
                            BinId.newBuilder().setAddress(result.getKey()).setPort(result.getValue()).build()
                    ).get(10, TimeUnit.SECONDS);
                    if (callResult.getResult() != OperationStatusResponse.OperationStatus.SUCCESS) {
                        UtilDialogs.ShowErrorDialog(new OperationFailure(), callResult.getMessage());
                    }
                } catch (Exception e) {
                    UtilDialogs.ShowErrorDialog(e, "Error contacting controller server.");
                } finally {
                    connectController.setDisable(false);
                    addBin.setDisable(false);
                    prepareRecipe.setDisable(false);
                }
            });
            event.consume();
        });

        inspectAll.setOnMouseClicked(event -> {
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
                try{
                    result.executeIfValid();
                    UtilDialogs.ShowInformationDialog(String.format("Produced %d cubic liters of %s",
                            result.getFinalProductAmount(), result.getFinalProductName()));
                } catch (RecipeExecutionException | IngredientOperationException e) {
                    UtilDialogs.ShowErrorDialog(e,e.getMessage());
                }
            });
            event.consume();
        });
    }
}
