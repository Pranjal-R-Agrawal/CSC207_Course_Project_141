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
    private NewWindow newWindow;

    public ViewManager(JPanel views, CardLayout cardLayout, ViewManagerModel viewManagerModel) {
        this.views = views;
        this.cardLayout = cardLayout;
        viewManagerModel.addPropertyChangeListener(this);
    }

    public void setupDisplayComments(PostAndCommentsViewModel postAndCommentsViewModel, PostAndCommentsView postAndCommentsView, NewWindow newWindow) {
        this.postAndCommentsViewModel = postAndCommentsViewModel;
        this.postAndCommentsView = postAndCommentsView;
        this.newWindow = newWindow;
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
            newWindow.setView(postAndCommentsView);
            newWindow.createWindow();
//            JFrame postFrame = new JFrame();
//            postFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//            postFrame.getContentPane().setLayout(new BoxLayout(postFrame.getContentPane(), BoxLayout.Y_AXIS));
//            JScrollPane scrollPane = new JScrollPane(postAndCommentsView);
//            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//            postFrame.getContentPane().add(scrollPane);
//            postFrame.pack();
//            postFrame.setTitle(postAndCommentsView.title);
//            postFrame.setVisible(true);
        }
    }
}
