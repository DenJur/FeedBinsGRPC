package supervisor.exceptions;

public class RecipeExecutionException extends Exception {
    public RecipeExecutionException(String reason) {
        super(reason);
    }

    public RecipeExecutionException(String reason, Exception e) {
        super(reason, e);
    }
}
