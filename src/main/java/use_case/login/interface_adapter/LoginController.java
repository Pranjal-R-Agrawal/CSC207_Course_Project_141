package use_case.login.interface_adapter;

import use_case.login.application_business_rules.LoginInputBoundary;
import use_case.login.application_business_rules.LoginInputData;
import use_case.login.application_business_rules.LoginOutputBoundary;

public class LoginController {
    final LoginInputBoundary loginUsecaseInteractor;
    final LoginOutputBoundary loginPresenter;

    /**
     * @param loginUsecaseInteractor the interactor to be used by the controller
     * @param loginPresenter the presenter to be used by the controller
     */
    public LoginController(LoginInputBoundary loginUsecaseInteractor, LoginOutputBoundary loginPresenter) {
        this.loginUsecaseInteractor = loginUsecaseInteractor;
        this.loginPresenter = loginPresenter;
    }

    /**
     * Attempts to log in the user with the given username and password.
     * @param username the username of the user attempting to log in
     * @param password the password of the user attempting to log in
     */
    public void execute(String username, String password) {
        loginUsecaseInteractor.execute(new LoginInputData(username, password));
    }

    /**
     * Changes the active view to the login view
     */
    public void goToSignUp() {
        loginPresenter.goToSignUp();
    }
}
