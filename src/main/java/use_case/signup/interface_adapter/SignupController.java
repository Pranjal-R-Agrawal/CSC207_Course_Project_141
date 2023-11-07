package use_case.signup.interface_adapter;

import use_case.signup.application_business_rules.SignupInputBoundary;
import use_case.signup.application_business_rules.SignupInputData;

public class SignupController {
    final SignupInputBoundary signupUseCaseInteractor;

    public SignupController(SignupInputBoundary signupUseCaseInteractor) {
        this.signupUseCaseInteractor = signupUseCaseInteractor;
    }

    public void execute(String username, String password, String repeatPassword, String name, String email, String phoneNumber) {
        SignupInputData signupInputData = new SignupInputData(
                username, password, repeatPassword, name, email, phoneNumber
        );
        signupUseCaseInteractor.execute(signupInputData);
    }
}
