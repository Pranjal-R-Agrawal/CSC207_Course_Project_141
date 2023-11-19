package use_case.login.application_business_rules;

public class LoginInputData {
    final private String username;
    final private String password;

    public LoginInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
