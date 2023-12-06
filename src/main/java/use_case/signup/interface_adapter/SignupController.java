package use_case.signup.interface_adapter;

import use_case.signup.application_business_rules.SignupInputBoundary;
import use_case.signup.application_business_rules.SignupInputData;
import use_case.signup.application_business_rules.SignupOutputBoundary;

public class SignupController {
    final SignupInputBoundary signupUseCaseInteractor;
    final SignupOutputBoundary signupPresenter;

    /**
     * Constructor for SignupController
     * @param signupUseCaseInteractor the interactor for the use case
     * @param signupPresenter the presenter for the use case
     */
    public SignupController(SignupInputBoundary signupUseCaseInteractor, SignupOutputBoundary signupPresenter) {
        this.signupUseCaseInteractor = signupUseCaseInteractor;
        this.signupPresenter = signupPresenter;
    }

    /**
     * Packages the data and sends it to the interactor
     * @param username the username
     * @param password the password
     * @param repeatPassword the repeated password
     * @param name the name
     * @param email the email
     * @param phoneNumber the phone number
     * @param city the city
     * @param fieldOfExpertise the field of expertise
     */
    public void execute(String username, String password, String repeatPassword, String name, String email, String phoneNumber, String city, String fieldOfExpertise) {
        SignupInputData signupInputData = new SignupInputData(
                username, password, repeatPassword, name, email, phoneNumber, city, fieldOfExpertise
        );
        signupUseCaseInteractor.execute(signupInputData);
    }

    /**
     * Changes the active view to the login view
     */
    public void goToLogin() {
        signupPresenter.goToLogin();
    }
}
