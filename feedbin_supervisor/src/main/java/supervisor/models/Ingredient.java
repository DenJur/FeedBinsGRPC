package supervisor.models;

import supervisor.exceptions.IngredientOperationException;

/**
 * Stores ingredients used for recipe production
 */
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

    /**
     * Add ingredient amount to the internal counter
     * @param amount - amount to be added, must be 0 or more
     * @throws IngredientOperationException - is thrown if amount provided is less than 0
     */
    public void addAmount(double amount) throws IngredientOperationException {
        if (amount < 0) throw new IngredientOperationException("Trying to add less than 0 ingredients");
        ingredientAmount += amount;
    }

    /**
     * Remove ingredient amount from the internal counter
     * @param amount - amount to be removed, must be less or equal to the internal amount
     * @throws IngredientOperationException - is thrown if amount provided is less than 0 or more than the internal counter
     */
    public void removeAmount(double amount) throws IngredientOperationException {
        if (amount > this.ingredientAmount)
            throw new IngredientOperationException("Tried to remove more product than is required in the recipe.");
        else if(amount<0)
            throw new IngredientOperationException("Tried to remove negative amount");
        this.ingredientAmount -= amount;
    }

    /**
     * Translate internal counter to absolute amount based on value provided
     * @param max - total that percentages should be translated to
     */
    public void quantize(int max) {
        ingredientAmount = max * ingredientAmount;
    }

    /**
     * Rounds the internal counter value to represent cubic litters the same way the rest of the system does
     */
    public void round() {
        this.ingredientAmount = Math.round(ingredientAmount);
    }
}
