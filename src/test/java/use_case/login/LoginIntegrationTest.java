package use_case.login;

import data_access.MockSignupLoginDisplayPostDataAccessObject;
import data_access.MongoDBDataAccessObject;
import data_access.MongoDBDataAccessObjectBuilder;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import use_case.login.application_business_rules.LoginInputData;
import use_case.login.application_business_rules.LoginInteractor;
import use_case.login.application_business_rules.LoginOutputBoundary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class LoginIntegrationTest {
    MongoDBDataAccessObject mongoDBDataAccessObject;
    LoginInteractor loginInteractor;

    @Test
    public void testAllFieldsEmpty() {
        LoginOutputBoundary loginOutputBoundary = new LoginOutputBoundary() {
            @Override
            public void prepareFailView(String error) {
                assertEquals(error, "Please enter a username.");
            }
            @Override
            public void prepareSuccessView() {fail();}
            @Override
            public void goToSignUp() {fail();}
        };
        loginInteractor = new LoginInteractor(mongoDBDataAccessObject, loginOutputBoundary);
        loginInteractor.execute(new LoginInputData(null, null));
    }

    @Test
    public void testUsernameEmpty() {
        LoginOutputBoundary loginOutputBoundary = new LoginOutputBoundary() {
            @Override
            public void prepareFailView(String error) {
                assertEquals(error, "Please enter a username.");
            }
            @Override
            public void prepareSuccessView() {fail();}
            @Override
            public void goToSignUp() {fail();}
        };
        loginInteractor = new LoginInteractor(mongoDBDataAccessObject, loginOutputBoundary);
        loginInteractor.execute(new LoginInputData("", "password"));
    }

    @Test
    public void testPasswordEmpty() {
        LoginOutputBoundary loginOutputBoundary = new LoginOutputBoundary() {
            @Override
            public void prepareFailView(String error) {
                assertEquals(error, "Please enter a password.");
            }
            @Override
            public void prepareSuccessView() {fail();}
            @Override
            public void goToSignUp() {fail();}
        };
        loginInteractor = new LoginInteractor(mongoDBDataAccessObject, loginOutputBoundary);
        loginInteractor.execute(new LoginInputData("username", null));
    }

    @Test
    public void testInvalidCredentials() {
        LoginOutputBoundary loginOutputBoundary = new LoginOutputBoundary() {
            @Override
            public void prepareFailView(String error) {
                assertEquals(error, "Invalid username or password.");
            }
            @Override
            public void prepareSuccessView() {fail();}
            @Override
            public void goToSignUp() {fail();}
        };
        loginInteractor = new LoginInteractor(mongoDBDataAccessObject, loginOutputBoundary);
        loginInteractor.execute(new LoginInputData("invalid", "credentials"));
    }

    @Test
    public void testValidCredentials() {
        LoginOutputBoundary loginOutputBoundary = new LoginOutputBoundary() {
            @Override
            public void prepareFailView(String error) {fail();}
            @Override
            public void prepareSuccessView() {}
            @Override
            public void goToSignUp() {fail();}
        };
        loginInteractor = new LoginInteractor(mongoDBDataAccessObject, loginOutputBoundary);
        mongoDBDataAccessObject.addUser(new User(
                "username", "password", "name", "email", "phone", "", "")
        );
        loginInteractor.execute(new LoginInputData("username", "password"));
    }

    @Before
    public void setUpTest() {
        try {
            mongoDBDataAccessObject = new MongoDBDataAccessObjectBuilder().setTestParameters().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        mongoDBDataAccessObject.resetDatabase();
    }
}
