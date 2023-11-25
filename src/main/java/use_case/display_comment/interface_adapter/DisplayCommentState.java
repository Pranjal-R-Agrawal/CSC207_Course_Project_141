package use_case.display_comment.interface_adapter;

import org.bson.types.ObjectId;

import java.util.Map;

public class DisplayCommentState {
    private  Map<ObjectId, Map<String, Object>> comments;
    private ObjectId commentId;
    private String errorMessage;

    public Map<ObjectId, Map<String, Object>> getComments() {
        return comments;
    }

    public DisplayCommentState setComments(Map<ObjectId, Map<String, Object>> comments) {
        this.comments = comments;
        return this;
    }

    public ObjectId getCommentId() {
        return commentId;
    }

    public DisplayCommentState setCommentId(ObjectId commentId) {
        this.commentId = commentId;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public DisplayCommentState setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}
