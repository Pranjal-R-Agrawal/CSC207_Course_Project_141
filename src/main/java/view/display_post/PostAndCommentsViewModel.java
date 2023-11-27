package view.display_post;

import org.bson.types.ObjectId;
import use_case.display_post.interface_adapter.DisplayPostState;
import view.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PostAndCommentsViewModel extends ViewModel {
    private DisplayPostState state = new DisplayPostState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private ObjectId postId;
    public PostAndCommentsViewModel() {
        super("display_comments");
    }

    public DisplayPostState getState() {
        return state;
    }

    public void setState(DisplayPostState state) {
        this.state = state;
    }

    public ObjectId getPostId() {
        return postId;
    }

    public void setPostId(ObjectId postId) {
        this.postId = postId;
    }

    @Override
    public void firePropertyChanged(String propertyName) {
        support.firePropertyChange(propertyName, null, this);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
