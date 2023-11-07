package use_case.login.interface_adapter;

import use_case.login.application_business_rules.LoginOutputBoundary;

import view.LoginViewModel;
import view.ViewManagerModel;

// TODO: Connect with StartupGenerator Home Page

public class LoginPresenter implements LoginOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView() {

    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setErrorMessage(error);
        loginViewModel.firePropertyChanged("login_error");
    }
}
