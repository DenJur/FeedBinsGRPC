package supervisor.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import supervisor.exceptions.IngredientOperationException;
import supervisor.exceptions.RecipeBuildingException;
import supervisor.models.Ingredient;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class RecipeBuilder {

    private final ObservableList<Ingredient> ingredients;

    public RecipeBuilder() {
        ingredients = FXCollections.observableArrayList();
    }

    public ObservableList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void addIngredient(Ingredient toAdd) {
        ingredients.add(toAdd);
    }

    public void removeIngredient(Ingredient toRemove) {
        ingredients.remove(toRemove);
    }

    public Recipe build(String name,int amount) throws RecipeBuildingException, IngredientOperationException {
        double totalAmount = ingredients.stream().mapToDouble(Ingredient::getIngredientAmount).sum();
        if (totalAmount != 1.0) throw new RecipeBuildingException("Provided ingredients do not add up to 100%");

        ingredients.forEach(ingredient -> ingredient.quantize(amount));
        ArrayList<Ingredient> ingredientsStacked = new ArrayList<>();

        for (Ingredient ingredient : ingredients) {
            Optional<Ingredient> existing = ingredientsStacked.stream()
                    .filter(quantizedIngredient ->
                            Objects.equals(quantizedIngredient.getProductName(), ingredient.getProductName()))
                    .findFirst();
            if (existing.isPresent()) existing.get().addAmount(ingredient.getIngredientAmount());
            else ingredientsStacked.add(ingredient);
        }

        ingredientsStacked.forEach(Ingredient::round);
        return new Recipe(ingredientsStacked, name);
    }
}
