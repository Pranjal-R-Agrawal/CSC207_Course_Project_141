package view;

import org.bson.types.ObjectId;
import view.display_post.PostAndCommentsView;
import view.display_post.PostAndCommentsViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewManager implements PropertyChangeListener {
    private final CardLayout cardLayout;
    private final JPanel views;
    private PostAndCommentsViewModel postAndCommentsViewModel;
    private PostAndCommentsView postAndCommentsView;
    public NewWindow newPostAndCommentWindow;
    public NewWindow newCreateCommentWindow;
    public NewWindow newCreatePostWindow;

    public ViewManager(JPanel views, CardLayout cardLayout, ViewManagerModel viewManagerModel) {
        this.views = views;
        this.cardLayout = cardLayout;
        viewManagerModel.addPropertyChangeListener(this);
    }

    public void setupDisplayComments(PostAndCommentsViewModel postAndCommentsViewModel, PostAndCommentsView postAndCommentsView, NewWindow newWindow) {
        this.postAndCommentsViewModel = postAndCommentsViewModel;
        this.postAndCommentsView = postAndCommentsView;
    }
    public void setupNewWindows(NewWindow newPostAndCommentWindow, NewWindow newCreateCommentWindow, NewWindow newCreatePostWindow){
        this.newPostAndCommentWindow = newPostAndCommentWindow;
        this.newCreateCommentWindow = newCreateCommentWindow;
        this.newCreatePostWindow = newCreatePostWindow;
    }

    public PostAndCommentsView getDisplayCommentsView(ObjectId id) {
        postAndCommentsViewModel.setPostId(id);
        postAndCommentsViewModel.firePropertyChanged("reset_view");
        postAndCommentsViewModel.firePropertyChanged("display_post");
        return postAndCommentsView;
    }



    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("view")) {
            String viewModelName = (String) evt.getNewValue();
            for (Component component : views.getComponents()) {
                component.setVisible(component.getName().equals(viewModelName));
            }
            cardLayout.show(views, viewModelName);
        } else if (evt.getPropertyName().equals("display_post")) {
            ObjectId id = (ObjectId) evt.getNewValue();
            PostAndCommentsView postAndCommentsView = getDisplayCommentsView(id);
            newPostAndCommentWindow.setView(postAndCommentsView);
            newPostAndCommentWindow.setViewName(postAndCommentsView.viewName);
            newPostAndCommentWindow.createWindow();
        } else if (evt.getPropertyName().equals("display_create_comment")){

        }
    }
}
