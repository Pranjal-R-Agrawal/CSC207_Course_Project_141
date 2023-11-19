package use_case.login.interface_adapter;

import use_case.login.application_business_rules.LoginInputBoundary;
import use_case.login.application_business_rules.LoginInputData;

public class LoginController {
    final LoginInputBoundary loginUsecaseInteractor;

    public LoginController(LoginInputBoundary loginUsecaseInteractor) {
        this.loginUsecaseInteractor = loginUsecaseInteractor;
    }

    public void execute(String username, String password) {
        loginUsecaseInteractor.execute(new LoginInputData(username, password));
    }
}
