package use_case.login.interface_adapter;

public class LoginState {
    private String username;
    private String password;
    private String errorMessage;

    public String getUsername() {
        return username;
    }

    public LoginState setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginState setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public LoginState setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}
