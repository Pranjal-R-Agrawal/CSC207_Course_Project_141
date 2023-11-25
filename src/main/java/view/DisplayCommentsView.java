package view;

import app.CreateCommentUseCaseFactory;
import org.bson.types.ObjectId;
import use_case.display_comment.interface_adapter.DisplayCommentController;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class DisplayCommentsView extends JPanel implements PropertyChangeListener {
    public final String viewName;
    private ObjectId postId;
    private final DisplayCommentsViewModel displayCommentsViewModel;
    private final DisplayCommentController displayCommentController;
    private final CreateCommentUseCaseFactory createCommentUseCaseFactory;
    private final Map<ObjectId, CommentView> comments = new HashMap<>();
    private JFrame replyFrame;
    private JPanel replyPanel;

    public DisplayCommentsView(DisplayCommentsViewModel displayCommentsViewModel, DisplayCommentController displayCommentController, CreateCommentUseCaseFactory createCommentUseCaseFactory) {
        setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));

        this.displayCommentController = displayCommentController;

        this.displayCommentsViewModel = displayCommentsViewModel;
        displayCommentsViewModel.addPropertyChangeListener(this);

        this.createCommentUseCaseFactory = createCommentUseCaseFactory;

        this.viewName = displayCommentsViewModel.getViewName();
        setName(viewName);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("comments_retrieved")) {
            postId = displayCommentsViewModel.getPostId();
            Map<ObjectId, Map<String, Object>> newComments = displayCommentsViewModel.getState().getComments();
            Set<ObjectId> newCommentIds = newComments.keySet();

            for (ObjectId id : newCommentIds) {
                comments.put(id, new CommentView(newComments.get(id), displayCommentsViewModel));
            }

            for (ObjectId id : newCommentIds) {
                if (postId.equals(newComments.get(id).get("parentId"))) {
                    this.add(comments.get(id).getComment());
                } else {
                    comments.get(newComments.get(id).get("parentId")).addChild(comments.get(id));
                }
            }

            displayCommentsViewModel.getState().setComments(new HashMap<>());

        } else if (evt.getPropertyName().equals("display_comment_error")) {
            JOptionPane.showMessageDialog(this, "Error displaying comments\n" + displayCommentsViewModel.getState().getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } else if (evt.getPropertyName().equals("display_post_comments")) {
            ObjectId postId = displayCommentsViewModel.getPostId();
            displayCommentController.execute(postId, "post");
        } else if (evt.getPropertyName().equals("display_single_comment")) {
            ObjectId commentId = displayCommentsViewModel.getState().getCommentId();
            displayCommentController.execute(commentId, "comment");
        } else if (evt.getPropertyName().equals("reply_to_comment")) {
            replyFrame = new JFrame("Reply to comment");
            replyPanel = createCommentUseCaseFactory.create(displayCommentsViewModel.getState().getReplyParentPostId(), displayCommentsViewModel.getState().getReplyParentId());
            replyFrame.add(replyPanel);
            replyFrame.pack();
            replyFrame.setVisible(true);
        } else if (evt.getPropertyName().equals("close_reply_frame")) {
            replyFrame.setVisible(false);
            replyFrame.dispose();
            replyPanel = null;
        }
    }
}
