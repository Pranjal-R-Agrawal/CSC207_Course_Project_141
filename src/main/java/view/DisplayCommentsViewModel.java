package view;

import org.bson.types.ObjectId;
import use_case.display_comment.interface_adapter.DisplayCommentState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DisplayCommentsViewModel extends ViewModel {
    private DisplayCommentState state = new DisplayCommentState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private ObjectId postId;
    public DisplayCommentsViewModel() {
        super("display_comments");
    }

    public DisplayCommentState getState() {
        return state;
    }

    public void setState(DisplayCommentState state) {
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
