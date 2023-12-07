package view;

import use_case.signup.interface_adapter.SignupController;
import use_case.signup.interface_adapter.SignupState;
import use_case.signup.interface_adapter.SignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Displays the signup form, sends the input to the controller, creates  popup window if there is an error
 */
public class SignupView extends JPanel implements PropertyChangeListener {
    public final String viewName;
    private final SignupViewModel signupViewModel;
    private final JTextField usernameInputField = new JTextField(20);
    private final JPasswordField passwordInputField = new JPasswordField(20);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(20);
    private final JTextField nameInputField = new JTextField(20);
    private final JTextField phoneNumberInputField = new JTextField(20);
    private final JTextField emailInputField = new JTextField(20);
    private final JTextField cityInputField = new JTextField(20);
    private final JTextField fieldOfExpertiseInputField = new JTextField(20);
    private final SignupController signupController;
    private final JButton signupButton = new JButton();
    private final JButton loginButton = new JButton();

    /**
     * Constructor for SignupView
     * Initialises the view and adds listeners to the buttons and input fields
     * @param signupViewModel SignupViewModel
     * @param signupController SignupController
     */
    public SignupView(SignupViewModel signupViewModel, SignupController signupController) {
        this.signupViewModel = signupViewModel;
        this.signupController = signupController;

        viewName = signupViewModel.getViewName();
        setName(viewName);
        signupButton.setText(SignupViewModel.SIGNUP_BUTTON_LABEL);
        loginButton.setText(SignupViewModel.LOGIN_BUTTON_LABEL);

        signupViewModel.addPropertyChangeListener(this);

        JPanel usernamePanel = createEntry(SignupViewModel.USERNAME_LABEL, usernameInputField);
        JPanel passwordPanel = createEntry(SignupViewModel.PASSWORD_LABEL, passwordInputField);
        JPanel repeatPasswordPanel = createEntry(SignupViewModel.REPEAT_PASSWORD_LABEL, repeatPasswordInputField);
        JPanel namePanel = createEntry(SignupViewModel.NAME_LABEL, nameInputField);
        JPanel phoneNumberPanel = createEntry(SignupViewModel.PHONE_NUMBER_LABEL, phoneNumberInputField);
        JPanel emailPanel = createEntry(SignupViewModel.EMAIL_LABEL, emailInputField);
        JPanel cityPanel = createEntry(SignupViewModel.CITY_LABEL, cityInputField);
        JPanel fieldOfExpertisePanel = createEntry(SignupViewModel.FIELD_OF_EXPERTISE_LABEL, fieldOfExpertiseInputField);
        JPanel optionalPanel = new JPanel();
        JLabel optionalLabel = new JLabel(SignupViewModel.OPTIONAL_LABEL);
        optionalLabel.setForeground(Color.RED);
        optionalPanel.add(optionalLabel);

        signupButton.addActionListener(
                e -> {
                    if (e.getSource().equals(signupButton)) {
                        SignupState currentState = signupViewModel.getState();
                        signupController.execute(
                                currentState.getUsername(),
                                currentState.getPassword(),
                                currentState.getRepeatPassword(),
                                currentState.getName(),
                                currentState.getEmail(),
                                currentState.getPhoneNumber(),
                                currentState.getCity(),
                                currentState.getFieldOfExpertise()
                        );
                    }
                }
        );

        loginButton.addActionListener(
                e -> {
                    if (e.getSource().equals(loginButton)) {
                        signupViewModel.setState(new SignupState());
                        signupViewModel.firePropertyChanged("reset_input_fields");
                        signupController.goToLogin();
                    }
                }
        );

        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        signupViewModel.getState().setUsername(usernameInputField.getText() + e.getKeyChar());
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {}
                    @Override
                    public void keyReleased(KeyEvent e) {}
                }
        );

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        signupViewModel.getState().setPassword(passwordInputField.getText() + e.getKeyChar());
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {}
                    @Override
                    public void keyReleased(KeyEvent e) {}
                }
        );

        repeatPasswordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        signupViewModel.getState().setRepeatPassword(repeatPasswordInputField.getText() + e.getKeyChar());
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {}
                    @Override
                    public void keyReleased(KeyEvent e) {}
                }
        );

        nameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        signupViewModel.getState().setName(nameInputField.getText() + e.getKeyChar());
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {}
                    @Override
                    public void keyReleased(KeyEvent e) {}
                }
        );

        phoneNumberInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        signupViewModel.getState().setPhoneNumber(phoneNumberInputField.getText() + e.getKeyChar());
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {}
                    @Override
                    public void keyReleased(KeyEvent e) {}
                }
        );

        emailInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        signupViewModel.getState().setEmail(emailInputField.getText() + e.getKeyChar());
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {}
                    @Override
                    public void keyReleased(KeyEvent e) {}
                }
        );

        cityInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        signupViewModel.getState().setCity(cityInputField.getText() + e.getKeyChar());
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {}
                    @Override
                    public void keyReleased(KeyEvent e) {}
                }
        );

        fieldOfExpertiseInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        signupViewModel.getState().setFieldOfExpertise(fieldOfExpertiseInputField.getText() + e.getKeyChar());
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {}
                    @Override
                    public void keyReleased(KeyEvent e) {}
                }
        );

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        addComponent(usernamePanel, c, 0, 0, GridBagConstraints.HORIZONTAL);
        addComponent(passwordPanel, c, 0, GridBagConstraints.RELATIVE, GridBagConstraints.HORIZONTAL);
        addComponent(repeatPasswordPanel, c, 0, GridBagConstraints.RELATIVE, GridBagConstraints.HORIZONTAL);
        addComponent(namePanel, c, 0, GridBagConstraints.RELATIVE, GridBagConstraints.HORIZONTAL);
        addComponent(phoneNumberPanel, c, 0, GridBagConstraints.RELATIVE, GridBagConstraints.HORIZONTAL);
        addComponent(emailPanel, c, 0, GridBagConstraints.RELATIVE, GridBagConstraints.HORIZONTAL);
        addComponent(cityPanel, c, 0, GridBagConstraints.RELATIVE, GridBagConstraints.HORIZONTAL);
        addComponent(fieldOfExpertisePanel, c, 0, GridBagConstraints.RELATIVE, GridBagConstraints.HORIZONTAL);
        addComponent(optionalPanel, c, 0, GridBagConstraints.RELATIVE, GridBagConstraints.HORIZONTAL);
        add(signupButton, c);
        add(loginButton, c);
    }

    private void addComponent(JPanel panel, GridBagConstraints c, int gridx, int gridy, int fill) {
        c.gridx = gridx;
        c.gridy = gridy;
        c.fill = fill;
        add(panel, c);
    }

    private JPanel createEntry(String name, JTextField textField) {
        JPanel panel = new JPanel();
        panel.add(new JLabel(name));
        panel.add(textField);
        return panel;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("sign_up_error")) {
            SignupState currentState = signupViewModel.getState();
            JOptionPane.showMessageDialog(this, currentState.getErrorMessage());
        } else if (evt.getPropertyName().equals("reset_input_fields")) {
            usernameInputField.setText("");
            passwordInputField.setText("");
            repeatPasswordInputField.setText("");
            nameInputField.setText("");
            phoneNumberInputField.setText("");
            emailInputField.setText("");
            cityInputField.setText("");
            fieldOfExpertiseInputField.setText("");
        } else if (evt.getPropertyName().equals("update_fields")) {
            usernameInputField.setText(signupViewModel.getState().getUsername());
            passwordInputField.setText(signupViewModel.getState().getPassword());
            repeatPasswordInputField.setText(signupViewModel.getState().getRepeatPassword());
            nameInputField.setText(signupViewModel.getState().getName());
            phoneNumberInputField.setText(signupViewModel.getState().getPhoneNumber());
            emailInputField.setText(signupViewModel.getState().getEmail());
            cityInputField.setText(signupViewModel.getState().getCity());
            fieldOfExpertiseInputField.setText(signupViewModel.getState().getFieldOfExpertise());
        }
    }

    public JButton getSignupButton() {
        return signupButton;
    }
}
