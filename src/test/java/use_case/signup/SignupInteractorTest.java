package use_case.signup;

import data_access.MockSignupLoginDisplayPostDataAccessObject;
import data_access.MongoDBDataAccessObject;
import data_access.MongoDBDataAccessObjectBuilder;
import static org.junit.Assert.*;

import entity.User;
import org.junit.Before;
import org.junit.Test;
import use_case.signup.application_business_rules.*;
import view.SignupViewModel;

public class SignupInteractorTest {
    SignupInputBoundary signupInteractor;
    MockSignupLoginDisplayPostDataAccessObject mongoDBDataAccessObject;

    @Test
    public void testAllFieldsEmpty() {
        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {fail();}
            @Override
            public void prepareFailView(String error) {
                assertEquals(error, "Please enter a username.");
            }
            @Override
            public void goToLogin() {fail();}
        };
        signupInteractor = new SignupInteractor(mongoDBDataAccessObject, signupPresenter);
        signupInteractor.execute(new SignupInputData("", "", "", "", "", "", "", ""));
    }

    @Test
    public void testUsernameEmpty() {
        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {fail();}
            @Override
            public void prepareFailView(String error) {
                assertEquals(error, "Please enter a username.");
            }
            @Override
            public void goToLogin() {fail();}
        };
        signupInteractor = new SignupInteractor(mongoDBDataAccessObject, signupPresenter);
        signupInteractor.execute(new SignupInputData("", "password", "password", "name", "email", "phone", "city", "field"));
        signupInteractor.execute(new SignupInputData(null, "password", "password", "name", "email", "phone", "city", "field"));
    }

    @Test
    public void testPasswordEmpty() {
        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {fail();}
            @Override
            public void prepareFailView(String error) {
                assertEquals(error, "Please enter a password.");
            }
            @Override
            public void goToLogin() {fail();}
        };
        signupInteractor = new SignupInteractor(mongoDBDataAccessObject, signupPresenter);
        signupInteractor.execute(new SignupInputData("username", null, "password", "name", "email", "phone", "city", "field"));
        signupInteractor.execute(new SignupInputData("username", "", "password", "name", "email", "phone", "city", "field"));
    }

    @Test
    public void testRepeatPasswordEmpty() {
        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {fail();}
            @Override
            public void prepareFailView(String error) {
                assertEquals(error, "Please repeat the password.");
            }
            @Override
            public void goToLogin() {fail();}
        };
        signupInteractor = new SignupInteractor(mongoDBDataAccessObject, signupPresenter);
        signupInteractor.execute(new SignupInputData("username", "password", null, "name", "email", "phone", "city", "field"));
        signupInteractor.execute(new SignupInputData("username", "password", "", "name", "email", "phone", "city", "field"));
    }

    @Test
    public void testNameEmpty() {
        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {fail();}
            @Override
            public void prepareFailView(String error) {
                assertEquals(error, "Please enter your name.");
            }
            @Override
            public void goToLogin() {fail();}
        };
        signupInteractor = new SignupInteractor(mongoDBDataAccessObject, signupPresenter);
        signupInteractor.execute(new SignupInputData("username", "password", "password", null, "email", "phone", "city", "field"));
        signupInteractor.execute(new SignupInputData("username", "password", "password", "", "email", "phone", "city", "field"));
    }

    @Test
    public void testEmailEmpty() {
        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {fail();}
            @Override
            public void prepareFailView(String error) {
                assertEquals(error, "Please enter your email-id.");
            }
            @Override
            public void goToLogin() {fail();}
        };
        signupInteractor = new SignupInteractor(mongoDBDataAccessObject, signupPresenter);
        signupInteractor.execute(new SignupInputData("username", "password", "password", "name", null, "phone", "city", "field"));
        signupInteractor.execute(new SignupInputData("username", "password", "password", "name", "", "phone", "city", "field"));
    }

    @Test
    public void testPhoneNumberEmpty() {
        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {}
            @Override
            public void prepareFailView(String error) {fail();}
            @Override
            public void goToLogin() {fail();}
        };
        signupInteractor = new SignupInteractor(mongoDBDataAccessObject, signupPresenter);
        signupInteractor.execute(new SignupInputData("username", "password", "password", "name", "email", null, "city", "field"));
    }

    @Test
    public void testCityEmpty() {
        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {fail();}
            @Override
            public void prepareFailView(String error) {
                assertEquals(error, "Please enter your city.");
            }
            @Override
            public void goToLogin() {fail();}
        };
        signupInteractor = new SignupInteractor(mongoDBDataAccessObject, signupPresenter);
        signupInteractor.execute(new SignupInputData("username", "password", "password", "name", "email", "phone", null, "field"));
        signupInteractor.execute(new SignupInputData("username", "password", "password", "name", "email", "phone", "", "field"));
    }

    @Test
    public void testFieldOfExpertiseEmpty() {
        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {fail();}
            @Override
            public void prepareFailView(String error) {
                assertEquals(error, "Please enter your field of expertise.");
            }
            @Override
            public void goToLogin() {fail();}
        };
        signupInteractor = new SignupInteractor(mongoDBDataAccessObject, signupPresenter);
        signupInteractor.execute(new SignupInputData("username", "password", "password", "name", "email", "phone", "city", null));
        signupInteractor.execute(new SignupInputData("username", "password", "password", "name", "email", "phone", "city", ""));
    }

    @Test
    public void testPasswordsDontMatch() {
        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {fail();}
            @Override
            public void prepareFailView(String error) {
                assertEquals(error, "Passwords don't match.");
            }
            @Override
            public void goToLogin() {fail();}
        };
        signupInteractor = new SignupInteractor(mongoDBDataAccessObject, signupPresenter);
        SignupInputData signupInputData = new SignupInputData("username", "password", "password1", "name", "email", "phone", "city", "field");
        signupInteractor.execute(signupInputData);
    }

    @Test
    public void testUsernameAlreadyUsed() {
        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {fail();}
            @Override
            public void prepareFailView(String error) {
                assertEquals(error, "Username already used.");
            }
            @Override
            public void goToLogin() {fail();}
        };
        signupInteractor = new SignupInteractor(mongoDBDataAccessObject, signupPresenter);
        mongoDBDataAccessObject.addUser(new User("username", "password", "name", "email", "phone", "city", "field"));
        SignupInputData signupInputData = new SignupInputData("username", "password", "password", "name", "email", "phone", "city", "field");
        signupInteractor.execute(signupInputData);
    }

    @Test
    public void testSignupSuccess() {
        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {}
            @Override
            public void prepareFailView(String error) {fail();}
            @Override
            public void goToLogin() {}
        };
        signupInteractor = new SignupInteractor(mongoDBDataAccessObject, signupPresenter);
        SignupInputData signupInputData = new SignupInputData("username", "password", "password", "name", "email", "phone", "city", "field");
        signupInteractor.execute(signupInputData);
    }

    @Before
    public void setUpTest() {
        mongoDBDataAccessObject = new MockSignupLoginDisplayPostDataAccessObject();
    }
}
