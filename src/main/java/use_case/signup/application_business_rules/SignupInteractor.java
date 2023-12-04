package use_case.signup.application_business_rules;

import data_access.SignupUserDataAccessInterface;
import entity.User;
import entity.UserFactory;
import entity.UserFactoryInterface;

public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;

    public SignupInteractor(SignupUserDataAccessInterface userDataAccessObject, SignupOutputBoundary userPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
    }

    public void execute(SignupInputData signupInputData) {
        if (signupInputData.getUsername() == null ||signupInputData.getUsername().trim().isEmpty()) {
            userPresenter.prepareFailView("Please enter a username.");
        } else if (signupInputData.getPassword() == null || signupInputData.getPassword().trim().isEmpty()) {
            userPresenter.prepareFailView("Please enter a password.");
        } else if (signupInputData.getRepeatPassword() == null || signupInputData.getRepeatPassword().trim().isEmpty()) {
            userPresenter.prepareFailView("Please repeat repeat password.");
        } else if (signupInputData.getName() == null || signupInputData.getName().trim().isEmpty()) {
            userPresenter.prepareFailView("Please enter your name.");
        } else if (signupInputData.getEmail() == null || signupInputData.getEmail().trim().isEmpty()) {
            userPresenter.prepareFailView("Please enter your email-id.");
        } else if (signupInputData.getCity() == null || signupInputData.getCity().trim().isEmpty()) {
            userPresenter.prepareFailView("Please enter your city.");
        } else if (signupInputData.getFieldOfExpertise() == null || signupInputData.getFieldOfExpertise().trim().isEmpty()) {
            userPresenter.prepareFailView("Please enter your field of expertise.");
        } else if (userDataAccessObject.usernameUsed(signupInputData.getUsername())) {
            userPresenter.prepareFailView("Username already used.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {
            UserFactoryInterface userFactoryInterface = new UserFactory();
            User user = (User) userFactoryInterface.create(
                    signupInputData.getUsername().trim(),
                    signupInputData.getPassword().trim(),
                    signupInputData.getName().trim(),
                    signupInputData.getEmail().trim(),
                    signupInputData.getPhoneNumber().trim(),
                    signupInputData.getCity().trim(),
                    signupInputData.getFieldOfExpertise().trim()
            );
            userDataAccessObject.addUser(user);
            SignupOutputData signupOutputData = new SignupOutputData(user.getUsername());
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}
