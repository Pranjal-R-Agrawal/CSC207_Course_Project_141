package use_case.signup;

import org.junit.Before;
import org.junit.Test;

import entity.User;

import data_access.MongoDBDataAccessObjectTest;

import use_case.signup.application_business_rules.SignupInteractor;

import use_case.signup.interface_adapter.SignupController;
import use_case.signup.interface_adapter.SignupPresenter;

import view.LoginViewModel;
import view.SignupViewModel;
import view.ViewManagerModel;

public class SignupUseCaseTest {
    SignupViewModel signupViewModel;
    SignupController signupController;
    MongoDBDataAccessObjectTest mongoDBDataAccessObject;

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
    public void testPhoneNumberEmpty() {
        signupController.execute("username", "password", "password", "name", "email", null);
        assert signupViewModel.getState().getErrorMessage() == null;
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

    @Before
    public void setUpTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        signupViewModel = new SignupViewModel();
        mongoDBDataAccessObject = new MongoDBDataAccessObjectTest();
        signupController = new SignupController(
                new SignupInteractor(
                        mongoDBDataAccessObject, new SignupPresenter(
                                viewManagerModel, signupViewModel, new LoginViewModel()
                        )
                )
        );

        mongoDBDataAccessObject.resetDatabase();
    }
}
