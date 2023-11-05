package view;

import use_case.signup.interface_adapter.SignupController;
import use_case.signup.interface_adapter.SignupState;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName;
    private final SignupViewModel signupViewModel;
    private final JTextField usernameInputField = new JTextField(20);
    private final JPasswordField passwordInputField = new JPasswordField(20);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(20);
    private final JTextField nameInputField = new JTextField(20);
    private final JTextField phoneNumberInputField = new JTextField(20);
    private final JTextField emailInputField = new JTextField(20);
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
                                currentState.getPhoneNumber(),
                                currentState.getEmail()
                        );
                    }
                }
        );

        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
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
                        SignupState currentState = signupViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
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
                        SignupState currentState = signupViewModel.getState();
                        currentState.setRepeatPassword(repeatPasswordInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
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
                        SignupState currentState = signupViewModel.getState();
                        currentState.setName(nameInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
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
                        SignupState currentState = signupViewModel.getState();
                        currentState.setPhoneNumber(phoneNumberInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
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
                        SignupState currentState = signupViewModel.getState();
                        currentState.setEmail(emailInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
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
        addComponent(optionalPanel, c, 0, GridBagConstraints.RELATIVE, GridBagConstraints.HORIZONTAL);
        add(signupButton, c);
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
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
