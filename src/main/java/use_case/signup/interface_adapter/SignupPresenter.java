package use_case.signup.interface_adapter;

import use_case.signup.application_business_rules.SignupOutputBoundary;
import use_case.signup.application_business_rules.SignupOutputData;

import view.SignupViewModel;
import view.ViewManagerModel;

// TODO: Connect with LoginViewModel

public class SignupPresenter implements SignupOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final SignupViewModel signupViewModel;

    public SignupPresenter(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView(SignupOutputData user) {
        signupViewModel.firePropertyChanged("reset_input_fields");
    }

    @Override
    public void prepareFailView(String error) {
        SignupState signupState = signupViewModel.getState();
        signupState.setErrorMessage(error);
        signupViewModel.firePropertyChanged("sign_up_error");
    }
}
