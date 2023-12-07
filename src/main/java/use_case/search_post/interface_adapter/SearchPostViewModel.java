package use_case.search_post.interface_adapter;

import org.bson.types.ObjectId;
import use_case.search_post.interface_adapter.SearchPostState;
import view.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Observable containing the state which SearchPostView will observe
 * @author Tanmay Shinde
 */
public class SearchPostViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private SearchPostState state = new SearchPostState();
    private ObjectId selectedPostId;

    /**
     * Initializes SearchPostViewModel setting its viewName using the super constructor
     */
    public SearchPostViewModel() {
        super("search_post");
    }

    /**
     * @return Returns the state attribute of SearchPostViewModel
     */
    public SearchPostState getState() {
        return state;
    }

    /**
     * Sets the state attribute of SearchPostViewModel
     * @param state the new state that needs to be assigned to the state attribute of SearchPostViewModel
     */
    public void setState(SearchPostState state) {
        this.state = state;
    }

    /**
     * @return Returns the selectedPostId attribute of SearchPostViewModel
     */
    public ObjectId getSelectedPostId() {
        return selectedPostId;
    }

    /**
     * Sets the state attribute of SearchPostViewModel
     * @param selectedPostId the new id that needs to be assigned to the selectedPostId attribute of SearchPostViewModel
     */
    public void setSelectedPostId(ObjectId selectedPostId) {
        this.selectedPostId = selectedPostId;
    }

    /**
     * Instructs the Views listening to SearchPostViewModel to react based on the property change.
     * @param propertyName the programmatic name of the property that was changed
     */
    @Override
    public void firePropertyChanged(String propertyName) {
        support.firePropertyChange(propertyName, null, this.state);
    }

    /**
     * Configure an observer (View) of the SearchPostViewModel
     * @param listener the View willing to observe the changes in state in the SearchPostViewModel
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
