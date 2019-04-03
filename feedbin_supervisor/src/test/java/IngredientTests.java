import org.junit.jupiter.api.Test;
import supervisor.exceptions.IngredientOperationException;
import supervisor.models.Ingredient;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class IngredientTests {

    @Test
    public void TestConstructors() {
        try {
            Field nameField = Ingredient.class.getDeclaredField("productName");
            nameField.setAccessible(true);
            Field amountField = Ingredient.class.getDeclaredField("ingredientAmount");
            amountField.setAccessible(true);

            Ingredient ingredient = new Ingredient("Product", 100);
            assertEquals(100.0d, amountField.get(ingredient), "Amount was not set by constructor");
            assertEquals("Product", nameField.get(ingredient), "Product name was not set by constructor");

            ingredient = new Ingredient(ingredient);
            assertEquals(100.0d, amountField.get(ingredient), "Amount was not set by constructor");
            assertEquals("Product", nameField.get(ingredient), "Product name was not set by constructor");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail(e);
        }
    }

    @Test
    public void TestAddAmount() {
        try {
            Ingredient ingredient = new Ingredient("Product", 100);
            ingredient.addAmount(10);
            assertEquals(110, ingredient.getIngredientAmount(), "Amount not added correctly");

            ingredient = new Ingredient("Product", 100);
            ingredient.addAmount(0);
            assertEquals(100, ingredient.getIngredientAmount(), "Zero amount is not handled as expected");

            try {
                ingredient = new Ingredient("Product", 100);
                ingredient.addAmount(-1);
                fail("Negative amounts should throw an error");
            } catch (IngredientOperationException ignored) {
            }
        } catch (IngredientOperationException e) {
            fail(e);
        }
    }

    @Test
    public void TestRemoveAmount() {
        try {
            Ingredient ingredient = new Ingredient("Product", 100);
            ingredient.removeAmount(10);
            assertEquals(90, ingredient.getIngredientAmount(), "Amount not removed correctly");

            ingredient = new Ingredient("Product", 100);
            ingredient.removeAmount(0);
            assertEquals(100, ingredient.getIngredientAmount(), "Zero amount is not handled as expected");

            ingredient = new Ingredient("Product", 100);
            ingredient.removeAmount(100);
            assertEquals(0, ingredient.getIngredientAmount(), "Max amount was not removed correctly");

            try {
                ingredient = new Ingredient("Product", 100);
                ingredient.removeAmount(-1);
                fail("Negative amounts should throw an error");
            } catch (IngredientOperationException ignored) {
            }

            try {
                ingredient = new Ingredient("Product", 100);
                ingredient.removeAmount(101);
                fail("Amount larger than the current ingredient amount should throw");
            } catch (IngredientOperationException ignored) {
            }
        } catch (IngredientOperationException e) {
            fail(e);
        }
    }

    @Test
    public void TestQuantize() {
        Ingredient ingredient = new Ingredient("Product", 1.0);
        ingredient.quantize(25);
        assertEquals(25, ingredient.getIngredientAmount(), "100% ingredient was not quantized correctly");

        ingredient = new Ingredient("Product", 0.25);
        ingredient.quantize(100);
        assertEquals(25, ingredient.getIngredientAmount(), "25% ingredient was not quantized correctly");
    }

    @Test
    public void TestRound() {
        Ingredient ingredient = new Ingredient("Product", 123.5);
        ingredient.round();
        assertEquals(124, ingredient.getIngredientAmount(), "Should round up");

        ingredient = new Ingredient("Product", 123.49);
        ingredient.round();
        assertEquals(123, ingredient.getIngredientAmount(), "Should round down");
    }
}
