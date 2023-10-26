package bookstore.exception;

public class CustomEntityNotFoundResponse {
    private int status;
    private String message;

    public CustomEntityNotFoundResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
