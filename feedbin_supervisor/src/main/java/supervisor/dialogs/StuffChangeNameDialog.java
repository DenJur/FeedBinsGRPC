package supervisor.dialogs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StuffChangeNameDialog extends Dialog<String> implements Initializable {
    @FXML
    private TextField stuffName;
    private String oldName;

    public StuffChangeNameDialog(String oldName) {
        this.oldName = oldName;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ChangeDialog.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            getDialogPane().setContent(root);


            ButtonType change = new ButtonType("Change", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            setResultConverter(buttonType -> {
                if (buttonType == change)
                    return stuffName.getText();
                return null;
            });
            getDialogPane().getButtonTypes().setAll(change, cancel);
            setTitle("Change name of Stuff");
            setHeaderText("Provide information about what new stuff is in the bin");
            stuffName.setText(oldName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            String text = change.getControlNewText();
            if (!text.trim().isEmpty()) {
                return change;
            }
            return null;
        });
        stuffName.setTextFormatter(textFormatter);
    }
}