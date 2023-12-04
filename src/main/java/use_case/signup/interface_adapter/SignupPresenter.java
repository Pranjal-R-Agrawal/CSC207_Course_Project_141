package use_case.signup.interface_adapter;

import use_case.signup.application_business_rules.SignupOutputBoundary;
import use_case.signup.application_business_rules.SignupOutputData;

import view.LoginViewModel;
import view.SignupViewModel;
import view.ViewManagerModel;

public class SignupPresenter implements SignupOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;

    /**
     * This class is used to prepare the data for the view model and/or change the view
     * @param viewManagerModel The main view manager model
     * @param signupViewModel The signup view model
     * @param loginViewModel The login view model
     */
    public SignupPresenter(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
    }

    /**
     * This method is used when the user has been signed up successfully
     * It will reset the signup view and change the view to the login view
     * @param user The data of the user that has been created
     */
    @Override
    public void prepareSuccessView(SignupOutputData user) {
        signupViewModel.setState(new SignupState());
        signupViewModel.firePropertyChanged("reset_input_fields");

        loginViewModel.getState().setUsername(user.getUsername());
        loginViewModel.firePropertyChanged("update_username");

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * This method is used when the user has not been signed up successfully
     * It will set the error message in the signup view model
     * @param error The error message
     */
    @Override
    public void prepareFailView(String error) {
        signupViewModel.getState().setErrorMessage(error);
        signupViewModel.firePropertyChanged("sign_up_error");
    }

    /**
     * This method is used when the user wants to go back to the login view
     */
    @Override
    public void goToLogin() {
        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
