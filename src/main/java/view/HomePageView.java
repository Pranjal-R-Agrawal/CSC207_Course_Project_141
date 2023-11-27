package view;

import use_case.login.interface_adapter.LoginController;
import use_case.login.interface_adapter.LoginState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HomePageView extends JPanel implements PropertyChangeListener {
    public final String viewName;
    private final HomePageViewModel homePageViewModel;
    private final JButton generateIdeaButton = new JButton();
    private final JButton postButton = new JButton();

    public HomePageView(HomePageViewModel homePageViewModel, LoginController loginController) {
        this.homePageViewModel = homePageViewModel;

        viewName = loginViewModel.getViewName();
        setName(viewName);
        loginButton.setText(LoginViewModel.LOGIN_BUTTON_LABEL);

        loginViewModel.addPropertyChangeListener(this);

        JPanel usernamePanel = createEntry(LoginViewModel.USERNAME_LABEL, usernameInputField);
        JPanel passwordPanel = createEntry(LoginViewModel.PASSWORD_LABEL, passwordInputField);

        loginButton.addActionListener(
                e -> {
                    if (e.getSource().equals(loginButton)) {
                        LoginState currentState = loginViewModel.getState();
                        loginController.execute(
                                currentState.getUsername(),
                                currentState.getPassword()
                        );
                    }
                }
        );

        usernameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                loginViewModel.getState().setUsername(usernameInputField.getText() + e.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        passwordInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                loginViewModel.getState().setPassword(passwordInputField.getText() + e.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        addComponent(usernamePanel, constraints, 0, 0, GridBagConstraints.HORIZONTAL);
        addComponent(passwordPanel, constraints, 0, GridBagConstraints.RELATIVE, GridBagConstraints.HORIZONTAL);
        add(loginButton, constraints);
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
        if (evt.getPropertyName().equals("log_in_error")) {
            LoginState currentState = loginViewModel.getState();
            JOptionPane.showMessageDialog(this, currentState.getErrorMessage());
        } else if (evt.getPropertyName().equals("update_username")) {
            LoginState currentState = loginViewModel.getState();
            usernameInputField.setText(currentState.getUsername());
        } else if (evt.getPropertyName().equals("update_password")) {
            LoginState currentState = loginViewModel.getState();
            passwordInputField.setText(currentState.getPassword());
        }
    }
