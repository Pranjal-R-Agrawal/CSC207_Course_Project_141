package use_case.login.interface_adapter;

import use_case.login.application_business_rules.LoginInputBoundary;
import use_case.login.application_business_rules.LoginInputData;
import use_case.login.application_business_rules.LoginOutputBoundary;

public class LoginController {
    final LoginInputBoundary loginUsecaseInteractor;
    final LoginOutputBoundary loginPresenter;

    public LoginController(LoginInputBoundary loginUsecaseInteractor, LoginOutputBoundary loginPresenter) {
        this.loginUsecaseInteractor = loginUsecaseInteractor;
        this.loginPresenter = loginPresenter;
    }

    public void execute(String username, String password) {
        loginUsecaseInteractor.execute(new LoginInputData(username, password));
    }

    public void goToSignUp() {
        loginPresenter.goToSignUp();
    }
}
