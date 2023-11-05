package view;

import use_case.signup.interface_adapter.SignupController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName;
    private final SignupViewModel signupViewModel;
    private final JTextField usernameInputField = new JTextField(20);
    private final JPasswordField passwordInputField = new JPasswordField(20);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(20);
    private final SignupController signupController;
    private final JButton signupButton = new JButton();

    public SignupView(SignupViewModel signupViewModel, SignupController signupController) {
        this.signupViewModel = signupViewModel;
        this.signupController = signupController;

        viewName = signupViewModel.getViewName();
        signupButton.setText(SignupViewModel.SIGNUP_BUTTON_LABEL);

        signupViewModel.addPropertyChangeListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
