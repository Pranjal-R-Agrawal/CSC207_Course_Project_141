package app;

import data_access.SignupUserDataAccessInterface;
import use_case.signup.application_business_rules.*;
import use_case.signup.interface_adapter.SignupController;
import use_case.signup.interface_adapter.SignupPresenter;
import view.SignupView;
import view.SignupViewModel;

import javax.swing.*;

public class SignupUseCaseFactory {
    public SignupUseCaseFactory() {}

    public SignupView create(SignupViewModel signupViewModel, SignupUserDataAccessInterface signupUserDataAccessObject) {
        try {
            SignupController signupController = createUserSignupUseCase(signupViewModel, signupUserDataAccessObject);
            return new SignupView(signupViewModel, signupController);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Could not connect to the database.");
        }
        return null;
    }

    private SignupController createUserSignupUseCase(SignupViewModel signupViewModel, SignupUserDataAccessInterface signupUserDataAccessObject) {
        SignupOutputBoundary signupPresenter = new SignupPresenter(signupViewModel);
        SignupInputBoundary signupInteractor = new SignupInteractor(signupUserDataAccessObject, signupPresenter);
        return new SignupController(signupInteractor);
    }
}