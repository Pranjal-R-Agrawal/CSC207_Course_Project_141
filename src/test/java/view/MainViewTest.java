package view;

import app.MainTester;
import data_access.MongoDBDataAccessObjectTest;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

public class MainViewTest {
    private SignupViewModel signupViewModel;
    private SignupView signupView;
    private JButton signupButton;

    @Test
    public void opensSignupView() {
        assert signupView != null;
    }

    @Test
    public void opensLoginView() {
        signupViewModel.getState().setUsername("username").setPassword("password")
                .setRepeatPassword("password").setName("name").setEmail("email").setPhoneNumber("phone");
        signupViewModel.firePropertyChanged("update_fields");
        assert signupButton != null;
        signupButton.doClick();
        assert MainTester.getCurrentView() instanceof LoginView;
    }

    @Before
    public void setUpTest() {
        new MongoDBDataAccessObjectTest().resetDatabase();
        MainTester.main(null);
        signupViewModel = MainTester.getSignupViewModel();
        signupView = (MainTester.getCurrentView() instanceof SignupView)? (SignupView) MainTester.getCurrentView() : null;
        if (signupView != null) signupButton = (JButton) signupView.getComponent(7);
    }
}