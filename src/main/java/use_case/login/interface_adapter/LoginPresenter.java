package use_case.login.interface_adapter;

import use_case.login.application_business_rules.LoginOutputBoundary;

import view.HomePageViewModel;
import use_case.signup.interface_adapter.SignupViewModel;
import view.ViewManagerModel;

/**
 * Concrete implementation of the LoginOutputBoundary.
 * Presenter updates state of the LoginViewModel and triggers te LoginView to observe and display updated LoginViewModel.
 * @author Tanmay Shinde
 */
public class LoginPresenter implements LoginOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final SignupViewModel signupViewModel;
    private final HomePageViewModel homePageViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel, HomePageViewModel homePageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
        this.homePageViewModel = homePageViewModel;
    }

    /**
     * Resets the fields of the login view model
     * Changes view to the home page
     */
    @Override
    public void prepareSuccessView() {
        loginViewModel.setState(new LoginState());
        viewManagerModel.setActiveView(homePageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
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
