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
        if (loginInputData.getUsername() == null || loginInputData.getUsername().trim().isEmpty()) {
            userPresenter.prepareFailView("Please enter a username.");
        } else if (loginInputData.getPassword() == null || loginInputData.getPassword().trim().isEmpty()) {
            userPresenter.prepareFailView("Please enter a password.");
        } else if (userDataAccessObject.isValid(loginInputData.getUsername().trim(), loginInputData.getPassword().trim())) {
            userDataAccessObject.setLoggedInUserID(
                    userDataAccessObject.getUserByUsername(loginInputData.getUsername().trim()).getId()
            );
            userPresenter.prepareSuccessView();
        } else {
            userPresenter.prepareFailView("Invalid username or password.");
        }
    }
}
