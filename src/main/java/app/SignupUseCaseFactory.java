package app;

import data_access.SignupUserDataAccessInterface;
import use_case.signup.application_business_rules.*;
import use_case.signup.interface_adapter.SignupController;
import use_case.signup.interface_adapter.SignupPresenter;
import view.LoginViewModel;
import view.SignupView;
import view.SignupViewModel;
import view.ViewManagerModel;

import javax.swing.*;

public class SignupUseCaseFactory {
    private SignupUseCaseFactory() {}

    public static SignupView create(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, SignupUserDataAccessInterface signupUserDataAccessObject) {
        SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, signupUserDataAccessObject);
        return new SignupView(signupViewModel, signupController);
    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, SignupUserDataAccessInterface signupUserDataAccessObject) {
        SignupOutputBoundary signupPresenter = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);
        SignupInputBoundary signupInteractor = new SignupInteractor(signupUserDataAccessObject, signupPresenter);
        return new SignupController(signupInteractor, signupPresenter);
    }
}