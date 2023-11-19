package use_case.login.application_business_rules;

import data_access.LoginUserDataAccessInterface;

public class LoginInteractor implements LoginInputBoundary {
    final LoginUserDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary userPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessObject, LoginOutputBoundary userPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
    }

    public void execute(LoginInputData loginInputData) {
        // TODO: Validate input data better.
        if (loginInputData.getUsername() == null) {
            userPresenter.prepareFailView("Please enter a username.");
        } else if (loginInputData.getPassword() == null) {
            userPresenter.prepareFailView("Please enter a password.");
        } else if (userDataAccessObject.isValid(loginInputData.getUsername(), loginInputData.getPassword())) {
            userDataAccessObject.setLoggedInUserID(
                    userDataAccessObject.getUserByUsername(loginInputData.getUsername()).getId()
            );
            userPresenter.prepareSuccessView();
        } else {
            userPresenter.prepareFailView("Invalid username or password.");
        }
    }
}
