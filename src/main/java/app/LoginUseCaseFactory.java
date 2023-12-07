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
import view.ViewManagerModel;

import javax.swing.*;

/**
 * A static factory for the Login use case
 * @author Tanmay Shinde
 */
public class LoginUseCaseFactory {
    
    private LoginUseCaseFactory() {}

    /**
     * Creates a LoginView object
     * @param viewManagerModel the view manager model for use case
     * @param loginViewModel the login view model for the use case
     * @param loginUserDataAccessObject the data access object for the use case
     * @param homePageViewModel the home page view model object for the use case
     */
    public static LoginView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, LoginUserDataAccessInterface loginUserDataAccessObject, HomePageViewModel homePageViewModel) {
        LoginController loginController = createUserLoginUseCase(viewManagerModel, loginViewModel, loginUserDataAccessObject, homePageViewModel);
        return new LoginView(loginViewModel, loginController);
    }

    private static LoginController createUserLoginUseCase(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, LoginUserDataAccessInterface loginUserDataAccessObject, HomePageViewModel homePageViewModel) {
        LoginOutputBoundary loginPresenter = new LoginPresenter(viewManagerModel, loginViewModel, homePageViewModel);
        LoginInputBoundary loginInteractor = new LoginInteractor(loginUserDataAccessObject, loginPresenter);
        return new LoginController(loginInteractor);
    }
}
