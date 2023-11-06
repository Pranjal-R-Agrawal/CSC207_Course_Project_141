package app;

import data_access.SignupUserDataAccessInterface;
import use_case.signup.application_business_rules.*;
import use_case.signup.interface_adapter.SignupController;
import use_case.signup.interface_adapter.SignupPresenter;
import view.SignupView;
import view.SignupViewModel;
import view.ViewManagerModel;

import javax.swing.*;

public class SignupUseCaseFactory {
    private SignupUseCaseFactory() {}

    public static SignupView create(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, SignupUserDataAccessInterface signupUserDataAccessObject) {
        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, signupUserDataAccessObject);
            return new SignupView(signupViewModel, signupController);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Could not connect to the database.");
        }
        return null;
    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, SignupUserDataAccessInterface signupUserDataAccessObject) {
        SignupOutputBoundary signupPresenter = new SignupPresenter(viewManagerModel, signupViewModel);
        SignupInputBoundary signupInteractor = new SignupInteractor(signupUserDataAccessObject, signupPresenter);
        return new SignupController(signupInteractor);
    }
}