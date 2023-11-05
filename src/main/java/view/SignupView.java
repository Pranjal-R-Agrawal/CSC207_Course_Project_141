package view;

import use_case.signup.interface_adapter.SignupController;
import use_case.signup.interface_adapter.SignupState;

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
    private final JPasswordField nameInputField = new JPasswordField(20);
    private final JPasswordField phoneNumberInputField = new JPasswordField(20);
    private final JPasswordField emailInputField = new JPasswordField(20);
    private final SignupController signupController;
    private final JButton signupButton = new JButton();

    public SignupView(SignupViewModel signupViewModel, SignupController signupController) {
        this.signupViewModel = signupViewModel;
        this.signupController = signupController;

        viewName = signupViewModel.getViewName();
        signupButton.setText(SignupViewModel.SIGNUP_BUTTON_LABEL);

        signupViewModel.addPropertyChangeListener(this);

        JPanel usernamePanel = createEntry(SignupViewModel.USERNAME_LABEL, usernameInputField);
        JPanel passwordPanel = createEntry(SignupViewModel.PASSWORD_LABEL, passwordInputField);
        JPanel repeatPasswordPanel = createEntry(SignupViewModel.REPEAT_PASSWORD_LABEL, repeatPasswordInputField);
        JPanel namePanel = createEntry(SignupViewModel.NAME_LABEL, nameInputField);
        JPanel phoneNumberPanel = createEntry(SignupViewModel.PHONE_NUMBER_LABEL, phoneNumberInputField);
        JPanel emailPanel = createEntry(SignupViewModel.EMAIL_LABEL, emailInputField);
        JPanel optionalPanel = new JPanel();
        optionalPanel.add(new JLabel(SignupViewModel.OPTIONAL_LABEL));
    }

    private JPanel createEntry(String name, JTextField textField) {
        JPanel panel = new JPanel();
        panel.add(new JLabel(name));
        panel.add(textField);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
