package supervisor.components;

import javafx.collections.ObservableList;
import supervisor.exceptions.IngredientOperationException;
import supervisor.exceptions.RecipeExecutionException;
import supervisor.models.Ingredient;
import u1467085.feedbincontroller.ControllerBinStatusUpdate;
import u1467085.feedbincontroller.FillBin;
import u1467085.feedbincontroller.OperationStatusResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Recipe {

    private final List<Ingredient> ingredients;
    private final String finalProductName;
    private final int finalProductAmount;

    public Recipe(List<Ingredient> quantizedIngredients, String finalProductName) {
        ingredients = quantizedIngredients;
        this.finalProductName=finalProductName;
        finalProductAmount = (int)quantizedIngredients.stream().mapToDouble(Ingredient::getIngredientAmount).sum();
    }

    public void executeIfValid() throws IngredientOperationException, RecipeExecutionException {
        String errors = validate(cloneIngredientList());
        if (!errors.isEmpty()) {
            throw new RecipeExecutionException(errors);
        }
        ServiceConnector connector=ServiceConnector.getInstance();
        ObservableList<ControllerBinStatusUpdate> bins = connector.getBinStatusList();
        OperationStatusResponse response;
        for (Ingredient ingredient:ingredients) {
            for (ControllerBinStatusUpdate bin : bins) {
                if (ingredient.getProductName().equalsIgnoreCase(bin.getStuff().getStuffName())) {
                    try {
                        int amountToRemove;
                        if (bin.getAmount() <= ingredient.getIngredientAmount()) {
                            amountToRemove=bin.getAmount();
                        } else {
                            amountToRemove=(int)ingredient.getIngredientAmount();
                        }
                        response=connector.getServiceStub().addStuff(FillBin.newBuilder()
                                .setId(bin.getRecord()).setAmount(-amountToRemove).build()).get(10, TimeUnit.SECONDS);
                        if(response.getResult()!=OperationStatusResponse.OperationStatus.SUCCESS)
                            throw new RecipeExecutionException("Controller failed operation with a message"+response.getMessage());
                        ingredient.removeAmount(amountToRemove);
                        if (ingredient.getIngredientAmount() == 0) break;
                    } catch (Exception e){
                        throw new RecipeExecutionException("Failed to contact controller");
                    }
                }
            }

            if (ingredient.getIngredientAmount() >= 1)
                throw new RecipeExecutionException(String.format("Failed to collect required amount %d of ingredients for ingredient %s",
                        (int)ingredient.getIngredientAmount(), ingredient.getProductName()));
        }

    }

    public String getFinalProductName() {
        return finalProductName;
    }

    public int getFinalProductAmount() {
        return finalProductAmount;
    }

    private List<Ingredient> cloneIngredientList() {
        ArrayList<Ingredient> clone = new ArrayList<>(ingredients.size());
        ingredients.forEach(ingredient -> clone.add(new Ingredient(ingredient)));
        return clone;
    }

    private String validate(List<Ingredient> ingredientList) throws IngredientOperationException {
        StringBuilder errors = new StringBuilder();
        ObservableList<ControllerBinStatusUpdate> bins = ServiceConnector.getInstance().getBinStatusList();

        for (Ingredient ingredient : ingredientList) {

            for (ControllerBinStatusUpdate bin : bins) {
                if (ingredient.getProductName().equalsIgnoreCase(bin.getStuff().getStuffName())) {
                    if (bin.getAmount() <= ingredient.getIngredientAmount()) {
                        ingredient.removeAmount(bin.getAmount());
                    } else {
                        ingredient.removeAmount(ingredient.getIngredientAmount());
                    }
                    if (ingredient.getIngredientAmount() == 0) break;
                }
            }

            if (ingredient.getIngredientAmount() >= 1) errors.append(
                    String.format("Bins are lacking %d %s.%n", (int) ingredient.getIngredientAmount(),
                            ingredient.getProductName()));
        }

        return errors.toString();
    }
}
