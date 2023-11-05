package view;

import use_case.signup.interface_adapter.SignupState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SignupViewModel extends ViewModel {
    public static final String USERNAME_LABEL = "Enter Username";
    public static final String PASSWORD_LABEL = "Enter Password";
    public static final String REPEAT_PASSWORD_LABEL = "Repeat Password";
    public static final String NAME_LABEL = "Enter Name";
    public static final String PHONE_NUMBER_LABEL = "Enter Phone Number";
    public static final String EMAIL_LABEL = "Enter Email ID";
    public static final String OPTIONAL_LABEL = "Entering Phone Number is optional";
    public static final String SIGNUP_BUTTON_LABEL = "Sign Up";
    private SignupState state = new SignupState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public SignupState getState() {
        return state;
    }

    public void setState(SignupState state) {
        this.state = state;
    }

    public SignupViewModel() {
        super("signup");
    }

    public void firePropertyChanged(String propertyName) {
        support.firePropertyChange(propertyName, null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
