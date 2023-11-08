package use_case.signup.interface_adapter;

import use_case.login.interface_adapter.LoginState;
import use_case.signup.application_business_rules.SignupOutputBoundary;
import use_case.signup.application_business_rules.SignupOutputData;

import view.LoginViewModel;
import view.SignupViewModel;
import view.ViewManagerModel;

public class SignupPresenter implements SignupOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;

    public SignupPresenter(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(SignupOutputData user) {
        signupViewModel.firePropertyChanged("reset_input_fields");

        loginViewModel.getState().setUsername(user.getUsername());
        loginViewModel.firePropertyChanged("update_username");

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        signupViewModel.getState().setErrorMessage(error);
        signupViewModel.firePropertyChanged("sign_up_error");
    }
}
