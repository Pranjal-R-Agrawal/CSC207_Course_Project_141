package use_case.view_profile.interface_adapter;



import use_case.view_profile.interface_adapter.ViewProfileDialogState;
import view.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Observable which is observed by the ViewUserInfoView class which stores the current state and handles property changes
 * @author Anbuselvan Ragunathan
 */
public class ViewProfileDialogViewModel extends ViewModel {

    private ViewProfileDialogState state = new ViewProfileDialogState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Initializes the view model using the parent constructor
     */
    public ViewProfileDialogViewModel() {
        super("view_profile_dialog");
    }

    /**
     * Getter for state
     * @return Returns a ViewUserInfoState object which has the current state (message) that needs to be displayed
     */
    public ViewProfileDialogState getState() {
        return state;
    }

    /**
     * Setter for state
     * @param state the state value to be assigned to the ViewModel's state attribute
     */
    public void setState(ViewProfileDialogState state) {
        this.state = state;
    }

    /**
     * Instructs the Views listening to react based on the property change.
     * @param propertyName the programmatic name of the property that was changed
     */
    @Override
    public void firePropertyChanged(String propertyName) {
        support.firePropertyChange(propertyName, null, this.state);
    }

    /**
     * Configure an observer (View) of the ViewUserInfoViewModel
     * @param listener the View willing to observe the changes in state in the ViewUserInfoViewModel
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}