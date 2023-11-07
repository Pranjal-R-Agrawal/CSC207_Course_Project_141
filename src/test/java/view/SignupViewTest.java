package view;

import app.Main;

import app.MainTester;
import data_access.MongoDBDataAccessObjectTest;
import entity.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import use_case.signup.interface_adapter.SignupState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static org.junit.Assert.assertNotNull;

public class SignupViewTest {
    private JTextField usernameInputField;
    private JPasswordField passwordInputField;
    private JPasswordField repeatPasswordInputField;
    private JTextField nameInputField;
    private JTextField phoneNumberInputField;
    private JTextField emailInputField;
    private JButton signupButton;
    private static boolean popUpDiscovered = false;
    private static String message = "";
    MongoDBDataAccessObjectTest database = new MongoDBDataAccessObjectTest();

    @Test
    public void testAllFieldsEmpty() {
        Main.main(new String[] {
                "src/main/java/data_access/database_connection.txt",
                "Tests", "Users", "Posts", "Comments"
        });
        getComponents();

        createCloseTimer().start();
        signupButton.doClick();

        assert message.contains("Please enter a username.");
    }

    @Test
    public void testUsernameEmpty() {
        Main.main(new String[] {
                "src/main/java/data_access/database_connection.txt",
                "Tests", "Users", "Posts", "Comments"
        });
        getComponents();

        createCloseTimer().start();
        signupButton.doClick();

        assert message.contains("Please enter a username.");
    }

    @Test
    public void testPasswordEmpty() {
        MainTester.main(null);
        getComponents();

        MainTester.getSignupViewModel().setState(
                new SignupState().setUsername("username")
        );

        createCloseTimer().start();
        signupButton.doClick();

        assert message.contains("Please enter a password.");
    }

    @Test
    public void testRepeatPasswordEmpty() {
        MainTester.main(null);
        getComponents();

        MainTester.getSignupViewModel().setState(
                new SignupState().setUsername("username").setPassword("password")
        );

        createCloseTimer().start();
        signupButton.doClick();

        assert message.contains("Please enter a password.");
    }

    @Test
    public void testNameEmpty() {
        MainTester.main(null);
        getComponents();

        MainTester.getSignupViewModel().setState(
                new SignupState().setUsername("username").setPassword("password").setRepeatPassword("password")
        );

        createCloseTimer().start();
        signupButton.doClick();

        assert message.contains("Please enter your name.");
    }

    @Test
    public void testEmailEmpty() {
        MainTester.main(null);
        getComponents();

        MainTester.getSignupViewModel().setState(
                new SignupState().setUsername("username").setPassword("password")
                        .setRepeatPassword("password").setName("name")
        );

        createCloseTimer().start();
        signupButton.doClick();

        assert message.contains("Please enter your email-id.");
    }

    @Test
    public void testPhoneNumberEmpty() {
        MainTester.main(null);
        getComponents();

        MainTester.getSignupViewModel().setState(
                new SignupState().setUsername("username").setPassword("password")
                        .setRepeatPassword("password").setName("name").setEmail("email")
        );

        popUpDiscovered = false;
        createCloseTimer().start();
        signupButton.doClick();
        assert !popUpDiscovered;
    }

    @Test
    public void testUsernameUsedNoUsers() {
        MainTester.main(null);
        getComponents();

        MainTester.getSignupViewModel().setState(
                new SignupState().setUsername("username").setPassword("password")
                        .setRepeatPassword("password").setName("name").setEmail("email")
        );

        popUpDiscovered = false;
        createCloseTimer().start();
        signupButton.doClick();
        assert !popUpDiscovered;
    }

    @Test
    public void testUsernameUsedAddUser() {
        database.addUser(
                new User("username", "password", "name", "email", "phone")
        );

        MainTester.main(null);
        getComponents();

        MainTester.getSignupViewModel().setState(
                new SignupState().setUsername("username").setPassword("password")
                        .setRepeatPassword("password").setName("name").setEmail("email")
        );

        createCloseTimer().start();
        signupButton.doClick();

        assert message.contains("Username already used.");
    }

    @Test
    public void testPasswordsDontMatch() {
        MainTester.main(null);
        getComponents();

        MainTester.getSignupViewModel().setState(
                new SignupState().setUsername("username").setPassword("password")
                        .setRepeatPassword("password2").setName("name").setEmail("email")
        );

        createCloseTimer().start();
        signupButton.doClick();

        assert message.contains("Passwords don't match.");
    }

    public void getComponents() {
        JFrame application = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                application = (JFrame) window;
            }
        }

        assertNotNull(application);

        JPanel root = (JPanel) ((JRootPane) application.getComponent(0)).getContentPane();
        JPanel views = (JPanel) root.getComponent(0);
        SignupView signupView = (SignupView) views.getComponent(0);

        usernameInputField = (JTextField) ((JPanel) signupView.getComponent(0)).getComponent(1);
        passwordInputField = (JPasswordField) ((JPanel) signupView.getComponent(1)).getComponent(1);
        repeatPasswordInputField = (JPasswordField) ((JPanel) signupView.getComponent(2)).getComponent(1);
        nameInputField = (JTextField) ((JPanel) signupView.getComponent(3)).getComponent(1);
        phoneNumberInputField = (JTextField) ((JPanel) signupView.getComponent(4)).getComponent(1);
        emailInputField = (JTextField) ((JPanel) signupView.getComponent(5)).getComponent(1);
        signupButton = (JButton) signupView.getComponent(7);
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
    @After
    public void resetDatabase() {
        database.resetDatabase();
    }
}
