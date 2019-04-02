package supervisor.exceptions;

public class RecipeExecutionException extends Exception {
    public RecipeExecutionException(String reason) {
        super(reason);
    }
}
