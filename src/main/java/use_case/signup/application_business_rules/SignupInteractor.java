package use_case.signup.application_business_rules;

import data_access.SignupUserDataAccessInterface;
import entity.User;

public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;

    public SignupInteractor(SignupUserDataAccessInterface userDataAccessObject, SignupOutputBoundary userPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
    }

    public void execute(SignupInputData signupInputData) {
        // TODO: Validate input data better.
        if (signupInputData.getUsername() == null) {
            userPresenter.prepareFailView("Please enter a username.");
        } else if (signupInputData.getPassword() == null || signupInputData.getRepeatPassword() == null) {
            userPresenter.prepareFailView("Please enter a password.");
        } else if (signupInputData.getName() == null) {
            userPresenter.prepareFailView("Please enter your name.");
        } else if (signupInputData.getEmail() == null) {
            userPresenter.prepareFailView("Please enter your email-id.");
        }else if (userDataAccessObject.usernameUsed(signupInputData.getUsername())) {
            userPresenter.prepareFailView("Username already used.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {
            User user = new User(signupInputData.getUsername(),
                    signupInputData.getPassword(),
                    signupInputData.getName(),
                    signupInputData.getEmail(),
                    signupInputData.getPhoneNumber());
            userDataAccessObject.addUser(user);
            SignupOutputData signupOutputData = new SignupOutputData(user.getId(), user.getUsername());
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}
