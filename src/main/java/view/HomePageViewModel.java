package view;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HomePageViewModel extends ViewModel {

    public static final String GENERATE_IDEA_BUTTON_LABEL = "Generate Idea";

    public static final String POST_BUTTON_LABEL = "Create Post";

    public static final String PROFILE_BUTTON_LABEL = "View Profile";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public HomePageViewModel() {
        super("home page");
    }

    public void firePropertyChanged(String propertyName) {
        support.firePropertyChange(propertyName, null, "view");
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}