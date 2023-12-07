package view;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.security.PublicKey;

/**
 * The Observable containing state which HomePageView will observe.
 * @author Tanmay Shinde
 */
public class HomePageViewModel extends ViewModel {

    public static final String GENERATE_IDEA_BUTTON_LABEL = "Generate Idea";

    public static final String POST_BUTTON_LABEL = "Create Post";

    public static final String PROFILE_BUTTON_LABEL = "View Profile";

    public static final String LOGOUT_BUTTON_LABEL = "Logout";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Initializes HomePageViewModel setting the viewName for its view
     */
    public HomePageViewModel() {
        super("home page");
    }

    /**
     * Instructs the Views listening to HomePageViewModel to react based on the property change.
     * @param propertyName the programmatic name of the property that was changed
     */
    public void firePropertyChanged(String propertyName) {
        support.firePropertyChange(propertyName, null, "view");
    }

    /**
     * Configure an observer (View) of the HomePageViewModel
     * @param listener the View willing to observe the changes in state in the HomePageViewModel
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
