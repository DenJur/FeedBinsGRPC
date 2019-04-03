import org.junit.jupiter.api.Test;
import supervisor.components.Recipe;
import supervisor.components.RecipeBuilder;
import supervisor.exceptions.IngredientOperationException;
import supervisor.exceptions.RecipeBuildingException;
import supervisor.models.Ingredient;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class RecipeBuilderTests {

    @Test
    public void TestEmptyBuilder() {
        try {
            new RecipeBuilder().build("Product", 100);
            fail("Should fail due to having less than 100% ingredients");
        } catch (IngredientOperationException e) {
            fail(e);
        } catch (RecipeBuildingException ignored) {
        }
    }

    @Test
    public void TestLessThan100Builder() {
        try {
            new RecipeBuilder().addIngredient(new Ingredient("a", 0.99))
                    .build("Product", 100);
            fail("Should fail due to having less than 100% ingredients");
        } catch (IngredientOperationException e) {
            fail(e);
        } catch (RecipeBuildingException ignored) {
        }
    }

    @Test
    public void TestMoreThan100Builder() {
        try {
            new RecipeBuilder().addIngredient(new Ingredient("a", 1.01))
                    .build("Product", 100);
            fail("Should fail due to having more than 100% ingredients");
        } catch (IngredientOperationException e) {
            fail(e);
        } catch (RecipeBuildingException ignored) {
        }
    }

    @Test
    public void TestValidBuilder() {
        try {
            Recipe recipe = new RecipeBuilder().addIngredient(new Ingredient("a", 0.6))
                    .addIngredient(new Ingredient("b", 0.4)).build("Product", 100);

            assertEquals("Product", recipe.getFinalProductName(), "Final product name was not set");
            assertEquals(100, recipe.getFinalProductAmount(), "Final product amount was not set");

            Field ingredientsField = Recipe.class.getDeclaredField("ingredients");
            ingredientsField.setAccessible(true);

            List<Ingredient> ingredients = (List<Ingredient>) ingredientsField.get(recipe);

            if (ingredients.stream().noneMatch(ingredient -> ingredient.getProductName().equals("a")))
                fail("Ingredient a was not added");

            if (ingredients.stream().noneMatch(ingredient -> ingredient.getProductName().equals("b")))
                fail("Ingredient b was not added");

            if (ingredients.stream().noneMatch(ingredient -> ingredient.getIngredientAmount() == 60))
                fail("Ingredient a amount was not correct");

            if (ingredients.stream().noneMatch(ingredient -> ingredient.getIngredientAmount() == 40))
                fail("Ingredient b amount was not correct");

        } catch (IngredientOperationException | RecipeBuildingException | NoSuchFieldException | IllegalAccessException e) {
            fail(e);
        }
    }

    @Test
    public void TestRemoveBuilder() {
        try {
            Ingredient removedIngredient = new Ingredient("c", 0.01);
            Recipe recipe = new RecipeBuilder().addIngredient(new Ingredient("a", 0.6))
                    .addIngredient(new Ingredient("b", 0.4))
                    .addIngredient(removedIngredient).removeIngredient(removedIngredient).build("Product", 100);

            assertEquals("Product", recipe.getFinalProductName(), "Final product name was not set");
            assertEquals(100, recipe.getFinalProductAmount(), "Final product amount was not set");

            Field ingredientsField = Recipe.class.getDeclaredField("ingredients");
            ingredientsField.setAccessible(true);

            List<Ingredient> ingredients = (List<Ingredient>) ingredientsField.get(recipe);

            if (ingredients.stream().noneMatch(ingredient -> ingredient.getProductName().equals("a")))
                fail("Ingredient a was not added");

            if (ingredients.stream().noneMatch(ingredient -> ingredient.getProductName().equals("b")))
                fail("Ingredient b was not added");

            if (ingredients.stream().noneMatch(ingredient -> ingredient.getIngredientAmount() == 60))
                fail("Ingredient a amount was not correct");

            if (ingredients.stream().noneMatch(ingredient -> ingredient.getIngredientAmount() == 40))
                fail("Ingredient b amount was not correct");

        } catch (IngredientOperationException | RecipeBuildingException | NoSuchFieldException | IllegalAccessException e) {
            fail(e);
        }
    }

    @Test
    public void TestCombineBuilder() {
        try {
            Recipe recipe = new RecipeBuilder().addIngredient(new Ingredient("a", 0.3))
                    .addIngredient(new Ingredient("b", 0.4))
                    .addIngredient(new Ingredient("a", 0.3)).build("Product", 100);

            assertEquals("Product", recipe.getFinalProductName(), "Final product name was not set");
            assertEquals(100, recipe.getFinalProductAmount(), "Final product amount was not set");

            Field ingredientsField = Recipe.class.getDeclaredField("ingredients");
            ingredientsField.setAccessible(true);

            List<Ingredient> ingredients = (List<Ingredient>) ingredientsField.get(recipe);

            if (ingredients.size() != 2) fail("Ingredients were not combined");

            if (ingredients.stream().noneMatch(ingredient -> ingredient.getProductName().equals("a")))
                fail("Ingredient a was not added");

            if (ingredients.stream().noneMatch(ingredient -> ingredient.getProductName().equals("b")))
                fail("Ingredient b was not added");

            if (ingredients.stream().noneMatch(ingredient -> ingredient.getIngredientAmount() == 60))
                fail("Ingredient a amount was not combined correctly");

            if (ingredients.stream().noneMatch(ingredient -> ingredient.getIngredientAmount() == 40))
                fail("Ingredient b amount was not correct");

        } catch (IngredientOperationException | RecipeBuildingException | NoSuchFieldException | IllegalAccessException e) {
            fail(e);
        }
    }
}
