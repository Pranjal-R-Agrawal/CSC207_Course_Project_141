package view;
import use_case.view_profile.interface_adapter.ViewProfileState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
public class ViewProfileViewModel extends ViewModel {
    public static final String USERNAME_LABEL = "";
    public static final String NAME_LABEL = "";
    public static final String EMAIL_LABEL = "";
    public static final double RATINGS_LABEL = 0.0d;
    public static final String LOGOUT_BUTTON_LABEL = "Log out";
    public static final String BACK_BUTTON_LABEL = "Back";
    private ViewProfileState state = new ViewProfileState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public ViewProfileState getState() {
        return state;
    }

    public void setState(ViewProfileState state) {
        this.state = state;
    }

    public ViewProfileViewModel() {
        super("View Model");
    }

    public void firePropertyChanged(String propertyName) {
        support.firePropertyChange(propertyName, null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
