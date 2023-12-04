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

    /**
     * Resets the fields of the login view model
     * Changes view to the home page
     */
    @Override
    public void prepareSuccessView() {
        loginViewModel.setState(new LoginState());
        loginViewModel.firePropertyChanged("reset_fields");
    }

    /**
     * Sets the error message of the login view model
     * @param error the error message
     */
    @Override
    public void prepareFailView(String error) {
        loginViewModel.getState().setErrorMessage(error);
        loginViewModel.firePropertyChanged("log_in_error");
    }

    /**
     * Changes view to the sign up page
     */
    @Override
    public void goToSignUp() {
        viewManagerModel.setActiveView(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
