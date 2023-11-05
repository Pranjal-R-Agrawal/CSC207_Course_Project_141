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
        if (userDataAccessObject.usernameUsed(signupInputData.getUsername())) {
            userPresenter.prepareFailView("Username already used");
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
