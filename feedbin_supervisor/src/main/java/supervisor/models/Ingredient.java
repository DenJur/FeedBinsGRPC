package supervisor.models;

import supervisor.exceptions.IngredientOperationException;

public class Ingredient {
    private String productName;
    private double ingredientAmount;

    public Ingredient(String productName, double ingredientAmount) {
        this.productName = productName;
        this.ingredientAmount = ingredientAmount;
    }

    public Ingredient(Ingredient ingredient) {
        this.productName = ingredient.getProductName();
        this.ingredientAmount = ingredient.getIngredientAmount();
    }

    public String getProductName() {
        return productName;
    }

    public double getIngredientAmount() {
        return ingredientAmount;
    }

    public void addAmount(double amount) throws IngredientOperationException {
        if (amount < 0) throw new IngredientOperationException("Trying to add less than 0 ingredients");
        ingredientAmount += amount;
    }

    public void removeAmount(double amount) throws IngredientOperationException {
        if (amount > this.ingredientAmount)
            throw new IngredientOperationException("Tried to remove more product than is required in the recipe.");
        this.ingredientAmount -= amount;
    }

    public void quantize(int max) {
        ingredientAmount = max * ingredientAmount;
    }

    public void round() {
        this.ingredientAmount = Math.round(ingredientAmount);
    }
}
