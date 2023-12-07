package view;

import data_access.MongoDBDataAccessObjectBuilder;
import entity.User;

import org.junit.Before;
import org.junit.Test;

import app.LoginUseCaseFactory;
import data_access.MongoDBDataAccessObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginViewTest {
    private static String message;
    private static boolean popUpDiscovered;
    private MongoDBDataAccessObject mongoDBDataAccessObject;
    private LoginViewModel loginViewModel;
    private HomePageViewModel homePageViewModel;
    private JButton loginButton;

    @Test
    public void testAllFieldsEmpty () {
        createCloseTimer().start();
        loginButton.doClick();
        assert popUpDiscovered && message.equals("Please enter a username.");
    }

    @Test
    public void testUsernameEmpty () {
        createCloseTimer().start();
        loginButton.doClick();
        assert popUpDiscovered && message.equals("Please enter a username.");
    }

    @Test
    public void testPasswordEmpty () {
        loginViewModel.getState().setUsername("username");
        loginViewModel.firePropertyChanged("update_username");
        createCloseTimer().start();
        loginButton.doClick();
        assert popUpDiscovered && message.equals("Please enter a password.");
    }

    @Test
    public void testInvalidCredentials () {
        loginViewModel.getState().setUsername("invalid").setPassword("credentials");
        loginViewModel.firePropertyChanged("update_username");
        loginViewModel.firePropertyChanged("update_password");
        createCloseTimer().start();
        loginButton.doClick();
        assert popUpDiscovered && message.equals("Invalid username or password.");
    }

    @Test
    public void testValidCredentials () {
        mongoDBDataAccessObject.addUser(
                new User("username", "password", "name", "email", "phone", "", "")
        );
        loginViewModel.getState().setUsername("username").setPassword("password");
        loginViewModel.firePropertyChanged("update_username");
        loginViewModel.firePropertyChanged("update_password");
        createCloseTimer().start();
        loginButton.doClick();
        assert !popUpDiscovered;
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

                        LoginViewTest.message = s;
                        LoginViewTest.popUpDiscovered = true;
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
        loginViewModel = new LoginViewModel();
        homePageViewModel = new HomePageViewModel();
        LoginView loginView = LoginUseCaseFactory.create(new ViewManagerModel(), new SignupViewModel(), loginViewModel, mongoDBDataAccessObject, homePageViewModel);
        loginButton = (JButton) loginView.getComponent(2);

        mongoDBDataAccessObject.resetDatabase();

        message = "";
        popUpDiscovered = false;

        JFrame application = new JFrame("Startup Generator");
        application.add(loginView);
        application.pack();
        application.setVisible(true);
    }
}
