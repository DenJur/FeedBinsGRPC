package supervisor.dialogs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Dialog used to get network connection information(address & port) from the user
 */
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

            //add dialog buttons
            ButtonType connect = new ButtonType("Connect", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            setResultConverter(buttonType -> {
                //if button pressed was the connect button
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
        //setup value factory for the port spinner
        SpinnerValueFactory.IntegerSpinnerValueFactory factory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 65535, 9090);
        portSpinner.setValueFactory(factory);

        //add text formatter due o Java bug https://bugs.openjdk.java.net/browse/JDK-8150962
        TextFormatter<Object> textFormatterDigit = new TextFormatter<>(c -> {

            if (c.getText().matches("[^0-9]+") && !c.getText().isEmpty())
                return null;

            try {
                Integer newVal = Integer.parseInt(c.getControlNewText());
                return (newVal >= factory.getMin() && factory.getMax() >= newVal) ? c : null;
            } catch (Exception ex) {
                c.setText("0");
                return c;
            }
        });
        portSpinner.getEditor().setTextFormatter(textFormatterDigit);


        //add text formatter to the address field that does not allow empty address
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
