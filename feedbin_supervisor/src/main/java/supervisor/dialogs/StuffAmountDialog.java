package supervisor.dialogs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StuffAmountDialog extends Dialog<Integer> implements Initializable {
    @FXML
    private Spinner<Integer> amountSpinner;
    private int maximum;
    private int minimum;

    public StuffAmountDialog(int max, int min) {
        maximum = max;
        minimum = min;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AmountDialog.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            getDialogPane().setContent(root);


            ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            setResultConverter(buttonType -> {
                if (buttonType == ok)
                    return amountSpinner.getValue();
                return null;
            });
            getDialogPane().getButtonTypes().setAll(ok, cancel);
            setTitle("Add Stuff");
            setHeaderText("Provide information about the amount of stuff to be added");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory.IntegerSpinnerValueFactory factory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(minimum, maximum, 0);
        amountSpinner.setValueFactory(factory);
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
        amountSpinner.getEditor().setTextFormatter(textFormatterDigit);
    }
}
