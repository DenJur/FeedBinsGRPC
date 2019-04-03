package supervisor.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import supervisor.models.Ingredient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for ListView cell that shows recipe ingredient
 */
public class IngredientCell extends ListCell<Ingredient> implements Initializable {

    private final RecipeBuilder builder;
    @FXML
    private Label ingredientInfoLabel;
    @FXML
    private HBox boxContainer;
    @FXML
    private Button removeButton;

    public IngredientCell(RecipeBuilder builder) {
        this.builder = builder;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/IngredientListCellView.fxml"));
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateItem(Ingredient item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty || item != null) {
            //update ingredient label
            ingredientInfoLabel.setText(String.format("%s %.2f%%", item.getProductName(), item.getIngredientAmount() * 100));
            setGraphic(boxContainer);
        } else {
            //clear the cell
            setGraphic(null);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //on remove button click remove this item from the list
        removeButton.setOnMouseClicked(event -> builder.removeIngredient(this.getItem()));
    }
}
