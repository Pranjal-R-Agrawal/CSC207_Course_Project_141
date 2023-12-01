package view.display_post;

import app.CreateCommentUseCaseBuilder;
import org.bson.types.ObjectId;
import use_case.display_post.interface_adapter.DisplayPostController;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class PostAndCommentsView extends JPanel implements PropertyChangeListener, Scrollable {
    public final String viewName;
    private ObjectId postId;
    private final PostAndCommentsViewModel postAndCommentsViewModel;
    private final DisplayPostController displayPostController;
    private final CreateCommentUseCaseBuilder createCommentUseCaseBuilder;
    private final Map<ObjectId, CommentView> comments = new HashMap<>();
    private JFrame replyFrame;
    private JPanel replyPanel;
    boolean postAdded = false;
    public String title;

    public PostAndCommentsView(PostAndCommentsViewModel postAndCommentsViewModel, DisplayPostController displayPostController, CreateCommentUseCaseBuilder createCommentUseCaseBuilder) {
        setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));
        setName("display_posts");

        this.displayPostController = displayPostController;

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
            replyFrame = new JFrame("Reply to comment");
            replyPanel = createCommentUseCaseBuilder.build(postAndCommentsViewModel.getState().getReplyParentPostId(), postAndCommentsViewModel.getState().getReplyParentId());
            replyFrame.add(replyPanel);
            replyFrame.pack();
            replyFrame.setLocationRelativeTo(this.getParent());
            replyFrame.setVisible(true);
        } else if (evt.getPropertyName().equals("close_reply_frame")) {
            replyFrame.setVisible(false);
            replyFrame.dispose();
            replyPanel = null;
        } else if(evt.getPropertyName().equals("reset_view")) {
            postAdded = false;
            this.removeAll();
            comments.clear();
        } else if (evt.getPropertyName().equals("resize_reply_frame")) {
            replyFrame.pack();
        }
    }

    public Dimension getPreferredScrollableViewportSize() {return getPreferredSize();}
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {return 1;}
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {return ((orientation == SwingConstants.VERTICAL) ? visibleRect.height : visibleRect.width) - 10;}
    public boolean getScrollableTracksViewportWidth() {return true;}
    public boolean getScrollableTracksViewportHeight() {return false;}
}
