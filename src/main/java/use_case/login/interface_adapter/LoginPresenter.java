package use_case.login.interface_adapter;

import use_case.login.application_business_rules.LoginOutputBoundary;

import view.HomePageViewModel;
import view.LoginViewModel;
import view.ViewManagerModel;

/**
 * Concrete implementation of the LoginOutputBoundary.
 * Presenter updates state of the LoginViewModel and triggers te LoginView to observe and display updated LoginViewModel.
 * @author Tanmay Shinde
 */
public class LoginPresenter implements LoginOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;

    private final HomePageViewModel homePageViewModel;


    public LoginPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, HomePageViewModel homePageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.homePageViewModel = homePageViewModel;
    }

    @Override
    public void prepareSuccessView() {
        loginViewModel.setState(new LoginState());
        viewManagerModel.setActiveView(homePageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        loginViewModel.getState().setErrorMessage(error);
        loginViewModel.firePropertyChanged("log_in_error");
    }
}
