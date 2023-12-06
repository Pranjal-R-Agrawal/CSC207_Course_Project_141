package use_case.signup.application_business_rules;

public interface SignupOutputBoundary {
    void prepareSuccessView(SignupOutputData user);
    void prepareFailView(String error);
    void goToLogin();
}