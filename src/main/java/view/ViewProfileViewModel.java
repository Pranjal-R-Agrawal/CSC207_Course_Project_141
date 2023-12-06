package view;
import use_case.view_profile.interface_adapter.ViewProfileState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
/**
 * The Observable containing state which ViewProfileView will observe.
 * @author Anbuselvan Ragunathan
 */
public class ViewProfileViewModel extends ViewModel {
    public static final String USERNAME_LABEL = "Username:";
    public static final String NAME_LABEL = "Name:";
    public static final String EMAIL_LABEL = "Email:";
    public static final String RATINGS_LABEL = "Rating:";
    public static final String PROJECTS_LABEL = "Projects:";
    public static final String COLLAB_REQUESTS_LABEL = "Collab Requests:";
    public static final String LOGOUT_BUTTON_LABEL = "Log out";
    public static final String BACK_BUTTON_LABEL = "Back";
    public static final String ACCEPT_BUTTON_LABEL = "Accept";
    public static final String REJECT_BUTTON_LABEL = "Reject";

    public static final String VIEW_PROFILE_BUTTON_LABEL = "View Profile";
    private ViewProfileState state = new ViewProfileState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * @return the current state
     */
    public ViewProfileState getState() {
        return state;
    }
    /**
     * Updates the current state of the ViewProfileViewModel with the new state
     */
    public void setState(ViewProfileState state) {
        this.state = state;
    }
    /**
     * set name of viewName
     */
    public ViewProfileViewModel() {
        super("View Profile");
    }
    /**
     * Instructs the Views listening to ViewProfileViewModel to react based on the property change.
     * @param propertyName the programmatic name of the property that was changed
     */
    public void firePropertyChanged(String propertyName) {
        support.firePropertyChange(propertyName, null, this.state);
    }

    /**
     * Configure an observer (View) of the ViewProfileViewModel
     * @param listener the View willing to observe the changes in state in the GenerateIdeaViewModel
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
