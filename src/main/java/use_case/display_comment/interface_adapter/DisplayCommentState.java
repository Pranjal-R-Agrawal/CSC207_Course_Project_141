package use_case.display_comment.interface_adapter;

import org.bson.types.ObjectId;

import java.util.Map;

public class DisplayCommentState {
    private  Map<ObjectId, Map<String, Object>> comments;
    private ObjectId commentId;
    private ObjectId replyParentId;
    private ObjectId replyParentPostId;
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

    public ObjectId getReplyParentId() {
        return replyParentId;
    }

    public DisplayCommentState setReplyParentId(ObjectId replyParentId) {
        this.replyParentId = replyParentId;
        return this;
    }

    public ObjectId getReplyParentPostId() {
        return replyParentPostId;
    }

    public DisplayCommentState setReplyParentPostId(ObjectId replyParentPostId) {
        this.replyParentPostId = replyParentPostId;
        return this;
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
