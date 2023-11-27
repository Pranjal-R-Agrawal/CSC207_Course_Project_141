package view;

import use_case.login.interface_adapter.LoginController;
import use_case.login.interface_adapter.LoginState;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HomePageViewModel extends ViewModel {

    public static final String GENERATE_IDEA_BUTTON_LABEL = "Generate Idea";

    public static final String POST_BUTTON_LABEL = "Post";

    public static final String PROFILE_BUTTON_LABEL = "View Profile";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public HomePageViewModel() {
        super("home page");
    }

    public void firePropertyChanged(String propertyName) {
        support.firePropertyChange(propertyName, null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}