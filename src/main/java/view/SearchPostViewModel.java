package view;

import org.bson.types.ObjectId;
import use_case.search_post.interface_adapter.SearchPostState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchPostViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private SearchPostState state = new SearchPostState();
    private ObjectId selectedPostId;
    public SearchPostViewModel() {
        super("search_post");
    }

    public SearchPostState getState() {
        return state;
    }

    public void setState(SearchPostState state) {
        this.state = state;
    }

    public ObjectId getSelectedPostId() {
        return selectedPostId;
    }

    public void setSelectedPostId(ObjectId selectedPostId) {
        this.selectedPostId = selectedPostId;
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
