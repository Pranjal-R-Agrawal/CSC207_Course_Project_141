package use_case.login;

import data_access.MongoDBDataAccessObject;

import data_access.MongoDBDataAccessObjectBuilder;
import entity.User;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import use_case.login.application_business_rules.LoginInteractor;

import use_case.login.application_business_rules.LoginOutputBoundary;
import use_case.login.interface_adapter.LoginController;
import use_case.login.interface_adapter.LoginPresenter;

import view.HomePageViewModel;
import use_case.login.interface_adapter.LoginViewModel;
import use_case.signup.interface_adapter.SignupViewModel;
import view.ViewManagerModel;

public class LoginUseCaseTest {
    LoginViewModel loginViewModel;

    HomePageViewModel homePageViewModel;
    MongoDBDataAccessObject mongoDBDataAccessObject;
    LoginController loginController;

    @Test
    public void testAllFieldsEmpty() {
        loginController.execute(null, null);
        assertEquals(loginViewModel.getState().getErrorMessage(), "Please enter a username.");
        loginController.execute("", "");
        assertEquals(loginViewModel.getState().getErrorMessage(), "Please enter a username.");
    }

    @Test
    public void testUsernameEmpty() {
        loginController.execute(null, "password");
        assertEquals(loginViewModel.getState().getErrorMessage(), "Please enter a username.");
        loginController.execute("", "password");
        assertEquals(loginViewModel.getState().getErrorMessage(), "Please enter a username.");
    }

    @Test
    public void testPasswordEmpty() {
        loginController.execute("username", null);
        assertEquals(loginViewModel.getState().getErrorMessage(), "Please enter a password.");
        loginController.execute("username", "");
        assertEquals(loginViewModel.getState().getErrorMessage(), "Please enter a password.");
    }

    @Test
    public void testInvalidCredentials() {
        loginController.execute("invalid", "credentials");
        assertEquals(loginViewModel.getState().getErrorMessage(), "Invalid username or password.");
    }

    @Test
    public void testValidCredentials() {
        mongoDBDataAccessObject.addUser(new User(
                "username", "password", "name", "email", "phone", "", "")
        );
        loginController.execute("username", "password");
        assertNull(loginViewModel.getState().getErrorMessage());
    }

    @Before
    public void setUpTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        loginViewModel = new LoginViewModel();
        homePageViewModel = new HomePageViewModel();
        try {
            mongoDBDataAccessObject = new MongoDBDataAccessObjectBuilder().setTestParameters().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        LoginOutputBoundary loginPresenter = new LoginPresenter(viewManagerModel, loginViewModel, new SignupViewModel(), homePageViewModel);
        loginController = new LoginController(new LoginInteractor(mongoDBDataAccessObject, loginPresenter), loginPresenter);

        mongoDBDataAccessObject.resetDatabase();
    }
}
