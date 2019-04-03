package supervisor.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import supervisor.exceptions.IngredientOperationException;
import supervisor.exceptions.RecipeBuildingException;
import supervisor.models.Ingredient;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

/**
 * Builder for the Recipe object
 */
public class RecipeBuilder {

    //list of currently supplied ingredients
    private final ObservableList<Ingredient> ingredients;

    public RecipeBuilder() {
        ingredients = FXCollections.observableArrayList();
    }

    /**
     * Getter for the ingredient list
     *
     * @return - list of current ingredients
     */
    public ObservableList<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * Add ingredient to the builder
     *
     * @param toAdd - ingredient to be added
     */
    public RecipeBuilder addIngredient(Ingredient toAdd) {
        ingredients.add(toAdd);
        return this;
    }

    /**
     * Remove ingredient from the builder
     *
     * @param toRemove - ingredient to be removed
     */
    public RecipeBuilder removeIngredient(Ingredient toRemove) {
        ingredients.remove(toRemove);
        return this;
    }

    /**
     * Build the recipe object
     *
     * @param name   - name of the final recipe product
     * @param amount - amount of the product that needs to be produced
     * @return - Recipe object
     * @throws RecipeBuildingException      - thrown if ingredient amounts do not add up to 100%
     * @throws IngredientOperationException - thrown if there is an error while combining identical ingredients
     */
    public Recipe build(String name, int amount) throws RecipeBuildingException, IngredientOperationException {
        //calculate total ingredient amount
        double totalAmount = ingredients.stream().mapToDouble(Ingredient::getIngredientAmount).sum();
        if (totalAmount != 1.0) throw new RecipeBuildingException("Provided ingredients do not add up to 100%");

        //convert percentage ingredient amount to absolute amounts
        ingredients.forEach(ingredient -> ingredient.quantize(amount));

        //combine identical ingredients based on name
        ArrayList<Ingredient> ingredientsStacked = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            Optional<Ingredient> existing = ingredientsStacked.stream()
                    .filter(quantizedIngredient ->
                            Objects.equals(quantizedIngredient.getProductName(), ingredient.getProductName()))
                    .findFirst();
            if (existing.isPresent()) existing.get().addAmount(ingredient.getIngredientAmount());
            else ingredientsStacked.add(ingredient);
        }

        //round of ingredient amount to full cubic litter values
        ingredientsStacked.forEach(Ingredient::round);
        return new Recipe(ingredientsStacked, name);
    }
}
