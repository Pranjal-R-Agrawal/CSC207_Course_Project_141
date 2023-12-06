package view;

import use_case.view_user_info.interface_adapter.ViewUserInfoState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Observable which is observed by the ViewUserInfoView class which stores the current state and handles property changes
 * @author Tanmay Shinde
 */
public class ViewUserInfoViewModel extends ViewModel{

    private ViewUserInfoState state = new ViewUserInfoState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public ViewUserInfoViewModel(String viewName) {
        super("view_user_info");
    }

    public ViewUserInfoState getState() {
        return state;
    }

    public void setState(ViewUserInfoState state) {
        this.state = state;
    }

    @Override
    public void firePropertyChanged(String propertyName) {
        support.firePropertyChange(propertyName, null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
