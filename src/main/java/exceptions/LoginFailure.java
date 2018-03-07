package exceptions;

public class LoginFailure extends Exception {

    private String message;

    public LoginFailure() {
        message = "Invalid login or password!";
    }

    public String getMessage() {
        return message;
    }
}
