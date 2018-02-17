package exceptions;

public class DatabaseFailure extends Exception {

    private String message;

    public DatabaseFailure() {
        message = "Database problem occurred!";
    }

    public String getMessage() {
        return message;
    }
}
