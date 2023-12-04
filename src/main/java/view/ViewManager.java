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
    private NewWindow newPostAndCommentWindow;
    private NewWindow newCreateCommentWindow;
    private NewWindow newCreatePostWindow;

    public ViewManager(JPanel views, CardLayout cardLayout, ViewManagerModel viewManagerModel) {
        this.views = views;
        this.cardLayout = cardLayout;
        viewManagerModel.addPropertyChangeListener(this);
    }

    public void setupDisplayComments(PostAndCommentsViewModel postAndCommentsViewModel, PostAndCommentsView postAndCommentsView) {
        this.postAndCommentsViewModel = postAndCommentsViewModel;
        this.postAndCommentsView = postAndCommentsView;
    }

    public void setupNewWindows(NewWindow newPostAndCommentWindow, NewWindow newCreateCommentWindow, NewWindow newCreatePostWindow) {
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

    public NewWindow getNewCreateCommentWindow() {
        return newCreateCommentWindow;
    }

    public NewWindow getNewCreatePostWindow() {
        return newCreatePostWindow;
    }

    public NewWindow getNewPostAndCommentWindow() {
        return newPostAndCommentWindow;
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
        } else if (evt.getPropertyName().equals("display_create_comment")) {
            CreateCommentView createCommentView = (CreateCommentView) evt.getNewValue();
            newCreateCommentWindow.setView(createCommentView);
            newCreateCommentWindow.setViewName("Reply to Comment");
            newCreateCommentWindow.createWindow();
        } else if (evt.getPropertyName().equals("display_create_post")) {
            CreatePostView createPostView = (CreatePostView) evt.getNewValue();
            newCreatePostWindow.setView(createPostView);
            newCreatePostWindow.setViewName("Create Post");
            newCreatePostWindow.createWindow();
        } else if (evt.getPropertyName().equals("close_create_comment")) {
            newCreateCommentWindow.closeWindow();
            newPostAndCommentWindow.resize();
        } else if (evt.getPropertyName().equals("close_create_post")) {
            newCreatePostWindow.closeWindow();
        } else if (evt.getPropertyName().equals("resize")){
            String view = (String) evt.getNewValue();
            if (view.equals("create_comment")){
                newCreateCommentWindow.resize();
            } else if (view.equals("create_post")) {
                newCreatePostWindow.resize();
            } else if (view.equals("post")) {
                newPostAndCommentWindow.resize();
            }
        }
    }
}
