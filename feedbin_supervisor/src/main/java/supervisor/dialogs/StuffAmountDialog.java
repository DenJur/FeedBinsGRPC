package supervisor.dialogs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.util.StringConverter;

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


            ButtonType add = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            setResultConverter(buttonType -> {
                if (buttonType == add)
                    return amountSpinner.getValue();
                return null;
            });
            getDialogPane().getButtonTypes().setAll(add, cancel);
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
        amountSpinner.getEditor().setOnAction(action -> {
            String text = amountSpinner.getEditor().getText();
            StringConverter<Integer> converter = factory.getConverter();
            if (converter != null) {
                try {
                    Integer value = converter.fromString(text);
                    if (null != value) {
                        factory.setValue(value);
                    } else {
                        amountSpinner.getEditor().setText(converter.toString(factory.getValue()));
                    }
                } catch (NumberFormatException e) {
                    amountSpinner.getEditor().setText(converter.toString(factory.getValue()));
                }
            }
            action.consume();
        });
    }
}
