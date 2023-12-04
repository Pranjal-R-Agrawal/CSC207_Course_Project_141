package use_case.signup;

import data_access.MongoDBDataAccessObjectBuilder;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import entity.User;

import data_access.MongoDBDataAccessObject;

import use_case.signup.application_business_rules.SignupInteractor;

import use_case.signup.application_business_rules.SignupOutputBoundary;
import use_case.signup.interface_adapter.SignupController;
import use_case.signup.interface_adapter.SignupPresenter;

import view.LoginViewModel;
import view.SignupViewModel;
import view.ViewManagerModel;

public class SignupUseCaseTest {
    SignupViewModel signupViewModel;
    SignupController signupController;
    MongoDBDataAccessObject mongoDBDataAccessObject;

    @Test
    public void testAllFieldsEmpty() {
        signupController.execute(null, null, null, null, null, null, null, null);
        assertEquals(signupViewModel.getState().getErrorMessage(), "Please enter a username.");
    }

    @Test
    public void testUsernameEmpty() {
        signupController.execute(null, "password", "password", "name", "email", "phone", "city", "field");
        assert signupViewModel.getState().getErrorMessage().equals("Please enter a username.");
        signupController.execute("", "password", "password", "name", "email", "phone", "city", "field");
        assert signupViewModel.getState().getErrorMessage().equals("Please enter a username.");
    }

    @Test
    public void testPasswordEmpty() {
        signupController.execute("username", null, "password", "name", "email", "phone", "city", "field");
        assertEquals(signupViewModel.getState().getErrorMessage(), "Please enter a password.");
        signupController.execute("username", "", "password", "name", "email", "phone", "city", "field");
        assertEquals(signupViewModel.getState().getErrorMessage(), "Please enter a password.");

    }

    @Test
    public void testRepeatPasswordEmpty() {
        signupController.execute("username", "password", null, "name", "email", "phone","city", "field");
        assertEquals(signupViewModel.getState().getErrorMessage(), "Please enter repeat password.");
        signupController.execute("username", "password", "", "name", "email", "phone","city", "field");
        assertEquals(signupViewModel.getState().getErrorMessage(), "Please enter repeat password.");
    }

    @Test
    public void testNameEmpty() {
        signupController.execute("username", "password", "password", null, "email", "phone", "city", "field");
        assertEquals(signupViewModel.getState().getErrorMessage(), "Please enter your name.");
        signupController.execute("username", "password", "password", "", "email", "phone", "city", "field");
        assertEquals(signupViewModel.getState().getErrorMessage(), "Please enter your name.");
    }

    @Test
    public void testEmailEmpty() {
        signupController.execute("username", "password", "password", "name", null, "phone", "city", "field");
        assertEquals(signupViewModel.getState().getErrorMessage(), "Please enter your email-id.");
        signupController.execute("username", "password", "password", "name", "", "phone", "city", "field");
        assertEquals(signupViewModel.getState().getErrorMessage(), "Please enter your email-id.");
    }

    @Test
    public void testPhoneNumberEmpty() {
        signupController.execute("username", "password", "password", "name", "email", null, "city", "field");
        assertNull(signupViewModel.getState().getErrorMessage());
        signupController.execute("username", "password", "password", "name", "email", "", "city", "field");
        assertNull(signupViewModel.getState().getErrorMessage());
    }

    @Test
    public void testCityEmpty() {
        signupController.execute("username", "password", "password", "name", "email", "phone", null, "field");
        assertEquals(signupViewModel.getState().getErrorMessage(), "Please enter your city.");
        signupController.execute("username", "password", "password", "name", "email", "phone", "", "field");
        assertEquals(signupViewModel.getState().getErrorMessage(), "Please enter your city.");
    }

    @Test
    public void testFieldOfExpertiseEmpty() {
        signupController.execute("username", "password", "password", "name", "email", "phone", "city", null);
        assertEquals(signupViewModel.getState().getErrorMessage(), "Please enter your field of expertise.");
        signupController.execute("username", "password", "password", "name", "email", "phone", "city", "");
        assertEquals(signupViewModel.getState().getErrorMessage(), "Please enter your field of expertise.");
    }

    @Test
    public void testUsernameUsedNoUsers() {
        signupController.execute("username", "password", "password", "name", "email", "phone", "city", "field");
        assertNull(signupViewModel.getState().getErrorMessage());
    }

    @Test
    public void testUsernameUsedAddUser() {
        mongoDBDataAccessObject.addUser(new User("username", "password", "name", "email", "phone", "city", "field"));
        signupController.execute("username", "password", "password", "name", "email", "phone", "city", "field");
        assertEquals(signupViewModel.getState().getErrorMessage(), "Username already used.");
    }

    @Test
    public void testPasswordsDontMatch() {
        signupController.execute("username", "password1", "password2", "name", "email", "phone", "city", "field");
        assertEquals(signupViewModel.getState().getErrorMessage(), "Passwords don't match.");
    }

    @Before
    public void setUpTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        signupViewModel = new SignupViewModel();
        try {
            mongoDBDataAccessObject = new MongoDBDataAccessObjectBuilder().setTestParameters().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        SignupOutputBoundary signupPresenter = new SignupPresenter(
                viewManagerModel, signupViewModel, new LoginViewModel()
        );
        signupController = new SignupController(
                new SignupInteractor(mongoDBDataAccessObject, signupPresenter), signupPresenter
        );

        mongoDBDataAccessObject.resetDatabase();
    }
}
