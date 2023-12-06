package view;

import app.CreateCommentUseCaseBuilder;
import org.bson.types.ObjectId;
import use_case.display_post.interface_adapter.DisplayPostController;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class is responsible for displaying a post and its comments.
 * It contains a PostView and a list of CommentViews.
 */
public class PostAndCommentsView extends JPanel implements PropertyChangeListener, Scrollable {
    public final String viewName;
    private ObjectId postId;
    private final PostAndCommentsViewModel postAndCommentsViewModel;
    private final ViewManagerModel viewManagerModel;
    private final DisplayPostController displayPostController;
    private final CreateCommentUseCaseBuilder createCommentUseCaseBuilder;
    private final Map<ObjectId, view.CommentView> comments = new HashMap<>();
    boolean postAdded = false;
    public String title;

    /**
     * Constructor for PostAndCommentsView.
     * @param postAndCommentsViewModel The view model for this view
     * @param viewManagerModel The view manager model
     * @param displayPostController The controller for displaying posts
     * @param createCommentUseCaseBuilder The use case builder for creating comments
     */
    public PostAndCommentsView(PostAndCommentsViewModel postAndCommentsViewModel, ViewManagerModel viewManagerModel, DisplayPostController displayPostController, CreateCommentUseCaseBuilder createCommentUseCaseBuilder) {
        setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));
        setName("display_posts");

        this.displayPostController = displayPostController;

        this.viewManagerModel = viewManagerModel;

        this.postAndCommentsViewModel = postAndCommentsViewModel;
        postAndCommentsViewModel.addPropertyChangeListener(this);

        this.createCommentUseCaseBuilder = createCommentUseCaseBuilder;

        this.viewName = postAndCommentsViewModel.getViewName();
        setName(viewName);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("retrieved")) {
            postId = postAndCommentsViewModel.getPostId();
            Map<ObjectId, Map<String, Object>> newComments = postAndCommentsViewModel.getState().getComments();
            Set<ObjectId> newCommentIds = newComments.keySet();

            for (ObjectId id : newCommentIds) {
                comments.put(id, new CommentView(newComments.get(id), postAndCommentsViewModel));
            }

            if (!postAdded) {
                this.add(new PostView(postAndCommentsViewModel.getState().getPost(), postAndCommentsViewModel));
                postAdded = true;
            }

            for (ObjectId id : newCommentIds) {
                if (postId.equals(newComments.get(id).get("parentId"))) {
                    this.add(comments.get(id).getComment());
                } else {
                    comments.get(newComments.get(id).get("parentId")).addChild(comments.get(id));
                }
            }

            title = (String) postAndCommentsViewModel.getState().getPost().get("title");
            postAndCommentsViewModel.getState().setComments(new HashMap<>());
            postAndCommentsViewModel.getState().setPost(new HashMap<>());

        } else if (evt.getPropertyName().equals("display_error")) {
            this.add(new JLabel(postAndCommentsViewModel.getState().getErrorMessage()));
        } else if (evt.getPropertyName().equals("display_post")) {
            ObjectId postId = postAndCommentsViewModel.getPostId();
            displayPostController.execute(postId, "post");
        } else if (evt.getPropertyName().equals("display_single_comment")) {
            if (!postAdded) {
                postAndCommentsViewModel.getState().setErrorMessage("Post not found");
                postAndCommentsViewModel.firePropertyChanged("display_error");
            }
            ObjectId commentId = postAndCommentsViewModel.getState().getCommentId();
            displayPostController.execute(commentId, "comment");
        } else if (evt.getPropertyName().equals("reply_to_comment")) {
            CreateCommentView replyPanel = createCommentUseCaseBuilder.build(postAndCommentsViewModel.getState().getReplyParentPostId(), postAndCommentsViewModel.getState().getReplyParentId());
            viewManagerModel.displayCreateComment(replyPanel);
        } else if (evt.getPropertyName().equals("close_reply_frame")) {
            viewManagerModel.closeCreateComment();
        } else if(evt.getPropertyName().equals("reset_view")) {
            postAdded = false;
            this.removeAll();
            comments.clear();
        } else if (evt.getPropertyName().equals("resize_reply_frame")) {
            viewManagerModel.resize("create_comment");
        }
    }

    // Functions for making this view scrollable
    public Dimension getPreferredScrollableViewportSize() {return getPreferredSize();}
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {return 1;}
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {return ((orientation == SwingConstants.VERTICAL) ? visibleRect.height : visibleRect.width) - 10;}
    public boolean getScrollableTracksViewportWidth() {return true;}
    public boolean getScrollableTracksViewportHeight() {return false;}
}
