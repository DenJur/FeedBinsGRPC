package supervisor.dialogs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.util.Pair;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConnectDialog extends Dialog<Pair<String, Integer>> implements Initializable {

    @FXML
    private TextField addressField;
    @FXML
    private Spinner<Integer> portSpinner;


    public ConnectDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ConnectDialog.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            getDialogPane().setContent(root);


            ButtonType connect = new ButtonType("Connect", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            setResultConverter(buttonType -> {
                if (buttonType == connect)
                    return new Pair<>(addressField.getText(), portSpinner.getValue());
                return null;
            });
            getDialogPane().getButtonTypes().setAll(connect, cancel);
            setTitle("Connect");
            setHeaderText("Provide information to establish the connection");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory.IntegerSpinnerValueFactory factory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 65535, 9090);
        portSpinner.setValueFactory(factory);
        portSpinner.getEditor().setOnAction(action -> {
            String text = portSpinner.getEditor().getText();
            StringConverter<Integer> converter = factory.getConverter();
            if (converter != null) {
                try {
                    Integer value = converter.fromString(text);
                    if (null != value) {
                        factory.setValue(value);
                    } else {
                        portSpinner.getEditor().setText(converter.toString(factory.getValue()));
                    }
                } catch (NumberFormatException e) {
                    portSpinner.getEditor().setText(converter.toString(factory.getValue()));
                }
            }
            action.consume();
        });


        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            String text = change.getControlNewText();
            if (!text.trim().isEmpty()) {
                return change;
            }
            return null;
        });
        addressField.setTextFormatter(textFormatter);
    }
}
