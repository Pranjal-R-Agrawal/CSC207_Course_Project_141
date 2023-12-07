package app;

import data_access.LoginUserDataAccessInterface;
import use_case.login.application_business_rules.LoginInputBoundary;
import use_case.login.application_business_rules.LoginInteractor;
import use_case.login.application_business_rules.LoginOutputBoundary;
import use_case.login.interface_adapter.LoginController;
import use_case.login.interface_adapter.LoginPresenter;
import view.HomePageViewModel;
import view.LoginView;
import view.LoginViewModel;
import view.SignupViewModel;
import view.ViewManagerModel;

import javax.swing.*;

/**
 * A static factory for the Login use case
 * @author Tanmay Shinde
 */
public class LoginUseCaseFactory {
    
    private LoginUseCaseFactory() {}

    /**
     * Creates and returns a LoginView object
     * @param viewManagerModel the view manager model
     * @param signupViewModel the signup view model
     * @param loginViewModel the login view model
     * @param loginUserDataAccessObject the login user data access object
     * @return a LoginView object
     */
     
    public static LoginView create(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, LoginUserDataAccessInterface loginUserDataAccessObject, HomePageViewModel homePageViewModel) {
        LoginController loginController = createUserLoginUseCase(viewManagerModel, loginViewModel, signupViewModel, loginUserDataAccessObject, homePageViewModel);
        return new LoginView(loginViewModel, loginController);
    }

    private static LoginController createUserLoginUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel, LoginUserDataAccessInterface loginUserDataAccessObject, HomePageViewModel homePageViewModel) {
        LoginOutputBoundary loginPresenter = new LoginPresenter(viewManagerModel, loginViewModel, signupViewModel, homePageViewModel);
        LoginInputBoundary loginInteractor = new LoginInteractor(loginUserDataAccessObject, loginPresenter);
        return new LoginController(loginInteractor, loginPresenter);
    }
}
