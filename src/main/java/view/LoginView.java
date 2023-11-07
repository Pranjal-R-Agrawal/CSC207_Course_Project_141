package view;

import use_case.login.interface_adapter.LoginController;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends JPanel implements PropertyChangeListener {
    public final String viewName;
    private final LoginViewModel loginViewModel;
    private final JTextField usernameInputField = new JTextField(20);
    private final JPasswordField passwordInputField = new JPasswordField(20);
    private final LoginController loginController;
    private final JButton loginButton = new JButton();

    public LoginView(LoginViewModel loginViewModel, LoginController loginController) {
        this.loginViewModel = loginViewModel;
        this.loginController = loginController;

        viewName = loginViewModel.getViewName();
        setName(viewName);
        loginButton.setText(LoginViewModel.LOGIN_BUTTON_LABEL);

        loginViewModel.addPropertyChangeListener(this);
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

    }
}
