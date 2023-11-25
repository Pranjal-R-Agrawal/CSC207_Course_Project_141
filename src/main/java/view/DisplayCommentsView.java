package view;

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
    private final Map<ObjectId, CommentView> comments = new HashMap<>();

    public DisplayCommentsView(DisplayCommentsViewModel displayCommentsViewModel, DisplayCommentController displayCommentController) {
        setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));

        this.displayCommentController = displayCommentController;

        this.displayCommentsViewModel = displayCommentsViewModel;
        displayCommentsViewModel.addPropertyChangeListener(this);

        this.viewName = displayCommentsViewModel.getViewName();
        setName(viewName);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("comments_retrieved")) {
            postId = displayCommentsViewModel.getPostId();
            Map<ObjectId, Map<String, Object>> newComments = displayCommentsViewModel.getState().getComments();
            Set<ObjectId> newCommentIds = newComments.keySet();

            for (ObjectId id : newCommentIds) {
                comments.put(id, new CommentView(newComments.get(id)));
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

        } else if (evt.getPropertyName().equals("display_post_comments")) {
            ObjectId postId = displayCommentsViewModel.getPostId();
            displayCommentController.execute(postId, "post");
        } else if (evt.getPropertyName().equals("display_single_comment")) {
            ObjectId commentId = displayCommentsViewModel.getState().getCommentId();
            displayCommentController.execute(commentId, "comment");
        }
    }
}
