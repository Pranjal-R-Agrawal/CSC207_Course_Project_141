package use_case.signup.application_business_rules;

public class SignupOutputData {
    private final String username;

    public SignupOutputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
