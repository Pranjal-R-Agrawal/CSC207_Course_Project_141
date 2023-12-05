package view;

import app.SignupUseCaseFactory;
import data_access.MongoDBDataAccessObjectBuilder;
import data_access.MongoDBDataAccessObject;
import entity.User;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SignupViewTest {
    private static boolean popUpDiscovered = false;
    private static String message = "";
    private MongoDBDataAccessObject mongoDBDataAccessObject;
    private SignupViewModel signupViewModel;
    private JButton signupButton;

    @Test
    public void testAllFieldsEmpty() {
        createCloseTimer().start();
        signupButton.doClick();
        assert message.contains("Please enter a username.");
    }

    @Test
    public void testUsernameEmpty() {
        createCloseTimer().start();
        signupButton.doClick();
        assert message.contains("Please enter a username.");
    }

    @Test
    public void testPasswordEmpty() {
        signupViewModel.getState().setUsername("username");
        signupViewModel.firePropertyChanged("update_fields");
        createCloseTimer().start();
        signupButton.doClick();
        assert message.contains("Please enter a password.");
    }

    @Test
    public void testRepeatPasswordEmpty() {
        signupViewModel.getState().setUsername("username").setPassword("password");
        signupViewModel.firePropertyChanged("update_fields");
        createCloseTimer().start();
        signupButton.doClick();
        assert message.contains("Please repeat the password.");
    }

    @Test
    public void testNameEmpty() {
        signupViewModel.getState().setUsername("username").setPassword("password")
                .setRepeatPassword("password");
        signupViewModel.firePropertyChanged("update_fields");
        createCloseTimer().start();
        signupButton.doClick();
        assert message.contains("Please enter your name.");
    }

    @Test
    public void testEmailEmpty() {
        signupViewModel.getState().setUsername("username").setPassword("password")
                .setRepeatPassword("password").setName("name");
        signupViewModel.firePropertyChanged("update_fields");
        createCloseTimer().start();
        signupButton.doClick();
        assert message.contains("Please enter your email-id.");
    }

    @Test
    public void testPhoneNumberEmpty() {
        signupViewModel.getState().setUsername("username").setPassword("password")
                .setRepeatPassword("password").setName("name").setEmail("email").setCity("city").setFieldOfExpertise("field");
        signupViewModel.firePropertyChanged("update_fields");
        createCloseTimer().start();
        signupButton.doClick();
        assert !popUpDiscovered;
    }

    @Test
    public void testUsernameUsedNoUsers() {
        signupViewModel.getState().setUsername("username").setPassword("password")
                .setRepeatPassword("password").setName("name").setEmail("email").setCity("city").setFieldOfExpertise("field");
        signupViewModel.firePropertyChanged("update_fields");
        createCloseTimer().start();
        signupButton.doClick();
        assert !popUpDiscovered;
    }

    @Test
    public void testUsernameUsedAddUser() {
        mongoDBDataAccessObject.addUser(
                new User("username", "password", "name", "email", "phone", "", "")
        );
        signupViewModel.getState().setUsername("username").setPassword("password")
                .setRepeatPassword("password").setName("name").setEmail("email").setCity("city").setFieldOfExpertise("field");
        signupViewModel.firePropertyChanged("update_fields");
        createCloseTimer().start();
        signupButton.doClick();
        assert message.contains("Username already used.");
    }

    @Test
    public void testPasswordsDontMatch() {
        signupViewModel.getState().setUsername("username").setPassword("password1")
                .setRepeatPassword("password2").setName("name").setEmail("email").setCity("city").setFieldOfExpertise("field");
        signupViewModel.firePropertyChanged("update_fields");
        createCloseTimer().start();
        signupButton.doClick();
        assert message.contains("Passwords don't match.");
    }

    private Timer createCloseTimer() {
        ActionListener close = e -> {
            Window[] windows = Window.getWindows();
            for (Window window : windows) {
                if (window instanceof JDialog dialog) {
                    if (dialog.isVisible()) {
                        String s = ((JOptionPane) ((BorderLayout) dialog.getRootPane()
                                .getContentPane().getLayout()).getLayoutComponent(BorderLayout.CENTER)).getMessage().toString();

                        System.out.println("message = " + s);

                        SignupViewTest.message = s;
                        SignupViewTest.popUpDiscovered = true;
                    }
                }
            }
            for (Window window : windows) {
                window.dispose();
            }
        };

        Timer t = new Timer(1000, close);
        t.setRepeats(false);
        return t;
    }

    @Before
    public void setUpTest() {
        try {
            mongoDBDataAccessObject = new MongoDBDataAccessObjectBuilder().setTestParameters().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        signupViewModel = new SignupViewModel();
        SignupView signupView = SignupUseCaseFactory.create(new ViewManagerModel(), signupViewModel, new LoginViewModel(), mongoDBDataAccessObject);
        signupButton = signupView.getSignupButton();

        mongoDBDataAccessObject.resetDatabase();

        message = "";
        popUpDiscovered = false;

        JFrame application = new JFrame("Startup Generator");
        application.add(signupView);
        application.pack();
        application.setVisible(true);
    }
}
