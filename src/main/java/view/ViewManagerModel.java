package view;

import org.bson.types.ObjectId;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewManagerModel {
    private String activeViewName;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public String getActiveView() {
        return activeViewName;
    }

    public void setActiveView(String activeView) {
        this.activeViewName = activeView;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("view", null, this.activeViewName);
        resize("main");
    }

    public void switchView(String activeView) {
        setActiveView(activeView);
        firePropertyChanged();
    }

    public void displayPost(ObjectId id) {
        support.firePropertyChange("display_post", null, id);
    }

    public void displayCreateComment(CreateCommentView createCommentView){support.firePropertyChange("display_create_comment", null, createCommentView);}

    public void displayCreatePost(CreatePostView createPostView){support.firePropertyChange("display_create_post", null, createPostView);}
    public void closeCreateComment(){support.firePropertyChange("close_create_comment", null, null);}
    public void closeCreatePost(){support.firePropertyChange("close_create_post", null, null);}
    public void resize(String view){support.firePropertyChange("resize", null, view);}

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
