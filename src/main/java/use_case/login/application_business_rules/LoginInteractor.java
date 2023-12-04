package use_case.login.application_business_rules;

import data_access.LoginUserDataAccessInterface;

public class LoginInteractor implements LoginInputBoundary {
    final LoginUserDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary userPresenter;

    /**
     * @param userDataAccessObject the data access object to be used by the interactor
     * @param userPresenter the presenter to be used by the interactor
     */
    public LoginInteractor(LoginUserDataAccessInterface userDataAccessObject, LoginOutputBoundary userPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
    }

    /**
     * Checks if the username and password are valid and logs in the user if they are.
     * Otherwise, it sets the appropriate error message to be displayed.
     * @param loginInputData the credentials of the user attempting to log in
     */
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
