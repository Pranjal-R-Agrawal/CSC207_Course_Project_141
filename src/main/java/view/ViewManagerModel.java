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
    }

    public void switchView(String activeView) {
        this.activeViewName = activeView;
        support.firePropertyChange("view", null, this.activeViewName);
    }

    public void displayPost(ObjectId id) {
        support.firePropertyChange("display_post", null, id);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
