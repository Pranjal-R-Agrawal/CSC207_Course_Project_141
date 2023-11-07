package use_case.login.application_business_rules;

public interface LoginOutputBoundary {
    void prepareSuccessView();
    void prepareFailView(String error);
}
