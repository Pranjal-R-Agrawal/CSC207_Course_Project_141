package use_case.signup.interface_adapter;

import use_case.signup.application_business_rules.SignupInputBoundary;
import use_case.signup.application_business_rules.SignupInputData;
import use_case.signup.application_business_rules.SignupOutputBoundary;

public class SignupController {
    final SignupInputBoundary signupUseCaseInteractor;
    final SignupOutputBoundary signupPresenter;

    public SignupController(SignupInputBoundary signupUseCaseInteractor, SignupOutputBoundary signupPresenter) {
        this.signupUseCaseInteractor = signupUseCaseInteractor;
        this.signupPresenter = signupPresenter;
    }

    public void execute(String username, String password, String repeatPassword, String name, String email, String phoneNumber, String city, String fieldOfExpertise) {
        SignupInputData signupInputData = new SignupInputData(
                username, password, repeatPassword, name, email, phoneNumber, city, fieldOfExpertise
        );
        signupUseCaseInteractor.execute(signupInputData);
    }

    public void goToLogin() {
        signupPresenter.goToLogin();
    }
}
