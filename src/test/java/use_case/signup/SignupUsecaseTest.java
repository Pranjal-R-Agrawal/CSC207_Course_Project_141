package use_case.signup;

import org.junit.After;
import org.junit.Test;

import entity.User;

import data_access.MongoDBDataAccessObjectTest;

import use_case.signup.application_business_rules.SignupInputBoundary;
import use_case.signup.application_business_rules.SignupInteractor;
import use_case.signup.application_business_rules.SignupOutputBoundary;

import use_case.signup.interface_adapter.SignupController;
import use_case.signup.interface_adapter.SignupPresenter;

import view.SignupViewModel;
import view.ViewManagerModel;

public class SignupUsecaseTest {
    SignupViewModel signupViewModel;
    SignupController signupController;
    MongoDBDataAccessObjectTest mongoDBDataAccessObject;

    public SignupUsecaseTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        signupViewModel = new SignupViewModel();
        mongoDBDataAccessObject = new MongoDBDataAccessObjectTest();
        SignupOutputBoundary signupPresenter = new SignupPresenter(viewManagerModel, signupViewModel);
        SignupInputBoundary signupInteractor = new SignupInteractor(mongoDBDataAccessObject, signupPresenter);
        signupController = new SignupController(signupInteractor);
    }

    @Test
    public void testAllFieldsEmpty() {
        signupController.execute(null, null, null, null, null, null);
        assert signupViewModel.getState().getErrorMessage().equals("Please enter a username.");
    }

    @Test
    public void testUsernameEmpty() {
        signupController.execute(null, "password", "password", "name", "email", "phone");
        assert signupViewModel.getState().getErrorMessage().equals("Please enter a username.");
    }

    @Test
    public void testPasswordEmpty() {
        signupController.execute("username", null, "password", "name", "email", "phone");
        assert signupViewModel.getState().getErrorMessage().equals("Please enter a password.");
    }

    @Test
    public void testRepeatPasswordEmpty() {
        signupController.execute("username", "password", null, "name", "email", "phone");
        assert signupViewModel.getState().getErrorMessage().equals("Please enter a password.");
    }

    @Test
    public void testNameEmpty() {
        signupController.execute("username", "password", "password", null, "email", "phone");
        assert signupViewModel.getState().getErrorMessage().equals("Please enter your name.");
    }

    @Test
    public void testEmailEmpty() {
        signupController.execute("username", "password", "password", "name", null, "phone");
        assert signupViewModel.getState().getErrorMessage().equals("Please enter your email-id.");
    }

    @Test
    public void testUsernameUsedNoUsers() {
        signupController.execute("username", "password", "password", "name", "email", "phone");
        assert signupViewModel.getState().getErrorMessage() == null;
    }

    @Test
    public void testUsernameUsedAddUser() {
        mongoDBDataAccessObject.addUser(new User("username", "password", "name", "email", "phone"));
        signupController.execute("username", "password", "password", "name", "email", "phone");
        assert signupViewModel.getState().getErrorMessage().equals("Username already used.");
    }

    @Test
    public void testPasswordsDontMatch() {
        signupController.execute("username", "password1", "password2", "name", "email", "phone");
        assert signupViewModel.getState().getErrorMessage().equals("Passwords don't match.");
    }

    @After
    public void clear() {
        mongoDBDataAccessObject.resetDatabase();
    }
}
