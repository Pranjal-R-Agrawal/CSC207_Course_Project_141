package view;

import org.bson.types.ObjectId;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewManager implements PropertyChangeListener {
    private final CardLayout cardLayout;
    private final JPanel views;
    private DisplayCommentsViewModel displayCommentsViewModel;
    private DisplayCommentsView displayCommentsView;

    public ViewManager(JPanel views, CardLayout cardLayout, ViewManagerModel viewManagerModel) {
        this.views = views;
        this.cardLayout = cardLayout;
        viewManagerModel.addPropertyChangeListener(this);
    }

    public void setupDisplayComments(DisplayCommentsViewModel displayCommentsViewModel, DisplayCommentsView displayCommentsView) {
        this.displayCommentsViewModel = displayCommentsViewModel;
        this.displayCommentsView = displayCommentsView;
    }

    public DisplayCommentsView getDisplayCommentsView(ObjectId id) {
        displayCommentsViewModel.setPostId(id);
        displayCommentsViewModel.firePropertyChanged("reset_view");
        displayCommentsViewModel.firePropertyChanged("display_post_comments");
        return displayCommentsView;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("view")) {
            String viewModelName = (String) evt.getNewValue();
            for (Component component : views.getComponents()) {
                component.setVisible(component.getName().equals(viewModelName));
            }
            cardLayout.show(views, viewModelName);
        }
    }
}
