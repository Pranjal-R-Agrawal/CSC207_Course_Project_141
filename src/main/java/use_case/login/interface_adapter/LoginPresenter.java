package use_case.login.interface_adapter;

import use_case.login.application_business_rules.LoginOutputBoundary;

import view.LoginViewModel;
import view.SignupViewModel;
import view.ViewManagerModel;

// TODO: Connect with StartupGenerator Home Page

public class LoginPresenter implements LoginOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final SignupViewModel signupViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView() {
        loginViewModel.setState(new LoginState());
        loginViewModel.firePropertyChanged("reset_fields");
    }

    @Override
    public void prepareFailView(String error) {
        loginViewModel.getState().setErrorMessage(error);
        loginViewModel.firePropertyChanged("log_in_error");
    }

    @Override
    public void goToSignUp() {
        viewManagerModel.setActiveView(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
