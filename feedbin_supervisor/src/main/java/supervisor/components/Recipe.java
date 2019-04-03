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

/**
 * Holds a finalize version of the recipe and is responsible for validating that recipe and managing production
 */
public class Recipe {
    //list of recipe ingredient that will be used in production
    private final List<Ingredient> ingredients;
    //title of recipe product
    private final String finalProductName;
    //amount of the stuff that is produced in the recipe
    private final int finalProductAmount;

    public Recipe(List<Ingredient> quantizedIngredients, String finalProductName) {
        ingredients = quantizedIngredients;
        this.finalProductName = finalProductName;
        //sum-up ingredients to get the resulting product amount
        //convert to int to get amount in cubic litters
        finalProductAmount = (int) quantizedIngredients.stream().mapToDouble(Ingredient::getIngredientAmount).sum();
    }

    /**
     * Validates recipe ingredients and executes the recipe if valid
     *
     * @throws IngredientOperationException - can be throw by ingredient objects if invalid operation is attempted
     * @throws RecipeExecutionException     - can be thrown if recipe attempted cannot be produced or if there are any errors
     *                                      in actually producing the recipe
     */
    public void executeIfValid() throws IngredientOperationException, RecipeExecutionException {
        //validate the recipe and stop if recipe cannot be produced
        String errors = validate();
        if (!errors.isEmpty()) {
            throw new RecipeExecutionException(errors);
        }
        //start recipe execution
        ServiceConnector connector = ServiceConnector.getInstance();
        ObservableList<ControllerBinStatusUpdate> bins = connector.getBinStatusList();
        OperationStatusResponse response;
        //iterate through the ingredients and try to find bins that contain said ingredient
        for (Ingredient ingredient : ingredients) {
            for (ControllerBinStatusUpdate bin : bins) {
                //if the bin contains an ingredient for the recipe
                if (ingredient.getProductName().equalsIgnoreCase(bin.getStuff().getStuffName())) {
                    try {
                        //if bin contains less stuff then is required remove all otherwise remove only required amount
                        int amountToRemove;
                        if (bin.getAmount() <= ingredient.getIngredientAmount()) {
                            amountToRemove = bin.getAmount();
                        } else {
                            amountToRemove = (int) ingredient.getIngredientAmount();
                        }
                        //send request to the controller
                        response = connector.getServiceStub().addStuff(FillBin.newBuilder()
                                .setId(bin.getRecord()).setAmount(-amountToRemove).build()).get(10, TimeUnit.SECONDS);
                        //error with dispensing the ingredient, stop the production
                        if (response.getResult() != OperationStatusResponse.OperationStatus.SUCCESS)
                            throw new RecipeExecutionException("Controller failed operation with a message" + response.getMessage());
                        ingredient.removeAmount(amountToRemove);
                        //if all of the ingredient is added go to next ingredient
                        if (ingredient.getIngredientAmount() == 0) break;
                    } catch (Exception e) {
                        //pass exception to parent
                        throw new RecipeExecutionException("Failed to contact controller", e);
                    }
                }
            }

            //if ingredient was not completely provided throw an error and stop production
            if (ingredient.getIngredientAmount() >= 1)
                throw new RecipeExecutionException(String.format("Failed to collect required amount %d of ingredients for ingredient %s",
                        (int) ingredient.getIngredientAmount(), ingredient.getProductName()));
        }

    }

    /**
     * Final product name getter
     *
     * @return - name of the product produced
     */
    public String getFinalProductName() {
        return finalProductName;
    }

    /**
     * Final product amount getter
     *
     * @return - amount of the product produced
     */
    public int getFinalProductAmount() {
        return finalProductAmount;
    }

    /**
     * Creates a clone of the ingredient list
     *
     * @return - ingredient list clone
     */
    private List<Ingredient> cloneIngredientList() {
        ArrayList<Ingredient> clone = new ArrayList<>(ingredients.size());
        ingredients.forEach(ingredient -> clone.add(new Ingredient(ingredient)));
        return clone;
    }

    /**
     * Validates the recipe and returns string error containing missing ingredients if invalid
     *
     * @return - missing ingredients
     * @throws IngredientOperationException
     */
    private String validate() throws IngredientOperationException {
        //create a clone of the ingredients list because we will need to keep track of used ingredients in case of
        //multiple bins containing stuff required for production
        List<Ingredient> ingredientList = cloneIngredientList();
        StringBuilder errors = new StringBuilder();
        ObservableList<ControllerBinStatusUpdate> bins = ServiceConnector.getInstance().getBinStatusList();


        //iterate through the ingredients and try to find bins that contain said ingredient
        for (Ingredient ingredient : ingredientList) {

            for (ControllerBinStatusUpdate bin : bins) {
                //if the bin contains an ingredient for the recipe
                if (ingredient.getProductName().equalsIgnoreCase(bin.getStuff().getStuffName())) {
                    //remove available stuff in the bin from the ingredient
                    if (bin.getAmount() <= ingredient.getIngredientAmount()) {
                        ingredient.removeAmount(bin.getAmount());
                    } else {
                        ingredient.removeAmount(ingredient.getIngredientAmount());
                    }
                    //go to next ingredient if current ingredient is fully processed
                    if (ingredient.getIngredientAmount() == 0) break;
                }
            }

            //add error message string for every ingredient that was not available in the bins in full
            //values of less than 1 cubic litter are disregarded
            if (ingredient.getIngredientAmount() >= 1) errors.append(
                    String.format("Bins are lacking %d %s.%n", (int) ingredient.getIngredientAmount(),
                            ingredient.getProductName()));
        }

        return errors.toString();
    }
}
