package supervisor.dialogs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import supervisor.components.IngredientCell;
import supervisor.components.Recipe;
import supervisor.components.RecipeBuilder;
import supervisor.exceptions.IngredientOperationException;
import supervisor.exceptions.RecipeBuildingException;
import supervisor.models.Ingredient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Dialog used to build a recipe
 */
public class RecipeDialog extends Dialog<Recipe> implements Initializable {

    private final RecipeBuilder builder;
    @FXML
    private TextField ingredientNameField;
    @FXML
    private Spinner<Double> ingredientAmountSpinner;
    @FXML
    private ListView<Ingredient> ingredientListView;
    @FXML
    private Button ingredientAddButton;
    @FXML
    private Spinner<Integer> productAmountSpinner;
    @FXML
    private TextField productNameField;


    public RecipeDialog() {
        builder = new RecipeBuilder();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RecipeDialog.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            getDialogPane().setContent(root);

            //add dialog buttons
            ButtonType produce = new ButtonType("Produce", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            setResultConverter(buttonType -> {
                //if button pressed was the connect button
                if (buttonType == produce) {
                    //try to build the Recipe object
                    try {
                        return builder.build(productNameField.getText(), productAmountSpinner.getValue());
                    } catch (RecipeBuildingException | IngredientOperationException e) {
                        UtilDialogs.ShowErrorDialog(e, e.getMessage());
                    }
                }
                return null;
            });
            getDialogPane().getButtonTypes().setAll(produce, cancel);
            setTitle("Recipe");
            setHeaderText("Provide ingredient information for the recipe production");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //setup value factory for the ingredient spinner
        SpinnerValueFactory.DoubleSpinnerValueFactory factory =
                new SpinnerValueFactory.DoubleSpinnerValueFactory(1.0, 100.0, 0.0, 1.0);
        ingredientAmountSpinner.setValueFactory(factory);

        //add text formatter due o Java bug https://bugs.openjdk.java.net/browse/JDK-8150962
        TextFormatter<Object> textFormatterDigit = new TextFormatter<>(c -> {

            if (c.getText().matches("[^0-9]+") && !c.getText().isEmpty())
                return null;

            try {
                Double newVal = Double.parseDouble(c.getControlNewText());
                return (newVal >= factory.getMin() && factory.getMax() >= newVal) ? c : null;
            } catch (Exception ex) {
                c.setText("0");
                return c;
            }
        });
        ingredientAmountSpinner.getEditor().setTextFormatter(textFormatterDigit);

        //setup value factory for the final product amount spinner
        SpinnerValueFactory.IntegerSpinnerValueFactory factoryProduct =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1);
        productAmountSpinner.setValueFactory(factoryProduct);

        //add text formatter due o Java bug https://bugs.openjdk.java.net/browse/JDK-8150962
        textFormatterDigit = new TextFormatter<>(c -> {

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
        productAmountSpinner.getEditor().setTextFormatter(textFormatterDigit);

        //setup ingredient list
        ingredientListView.setCellFactory(param -> new IngredientCell(builder));
        ingredientListView.setItems(builder.getIngredients());

        //setup ingredient and final product name text fields
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            String text = change.getControlNewText();
            if (!text.trim().isEmpty()) {
                return change;
            }
            return null;
        });
        ingredientNameField.setTextFormatter(textFormatter);

        textFormatter = new TextFormatter<>(change -> {
            String text = change.getControlNewText();
            if (!text.trim().isEmpty()) {
                return change;
            }
            return null;
        });
        productNameField.setTextFormatter(textFormatter);

        ingredientAddButton.setOnMouseClicked(event ->
                builder.addIngredient(new Ingredient(ingredientNameField.getText(), ingredientAmountSpinner.getValue() / 100)));
    }
}
