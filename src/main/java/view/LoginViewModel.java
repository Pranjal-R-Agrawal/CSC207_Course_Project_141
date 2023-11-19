package view;

import use_case.login.interface_adapter.LoginState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginViewModel extends ViewModel {
    public static final String USERNAME_LABEL = "Enter Username";
    public static final String PASSWORD_LABEL = "Enter Password";
    public static final String LOGIN_BUTTON_LABEL = "Log in";
    private LoginState state = new LoginState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public LoginViewModel() {
        super("login");
    }

    public LoginState getState() {
        return state;
    }

    public void setState(LoginState state) {
        this.state = state;
    }

    public void firePropertyChanged(String propertyName) {
        support.firePropertyChange(propertyName, null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
