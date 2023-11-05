package use_case.signup.interface_adapter;

import use_case.signup.application_business_rules.SignupOutputBoundary;
import use_case.signup.application_business_rules.SignupOutputData;

import view.SignupViewModel;

// TODO: Connect with LoginViewModel

public class SignupPresenter implements SignupOutputBoundary {
    private final SignupViewModel signupViewModel;
    public SignupPresenter(SignupViewModel signupViewModel) {
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView(SignupOutputData user) {

    }

    @Override
    public void prepareFailView(String error) {
        SignupState signupState = signupViewModel.getState();
        signupState.setErrorMessage(error);
        signupViewModel.firePropertyChanged("signup");
    }
}
