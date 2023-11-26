package use_case.display_post.interface_adapter;

import org.bson.types.ObjectId;

import java.util.Map;

public class DisplayPostState {
    private  Map<ObjectId, Map<String, Object>> comments;
    private Map<String, Object> post;
    private ObjectId commentId;
    private ObjectId replyParentId;
    private ObjectId replyParentPostId;
    private String errorMessage;

    public Map<ObjectId, Map<String, Object>> getComments() {
        return comments;
    }

    public DisplayPostState setComments(Map<ObjectId, Map<String, Object>> comments) {
        this.comments = comments;
        return this;
    }

    public Map<String, Object> getPost() {
        return post;
    }

    public DisplayPostState setPost(Map<String, Object> post) {
        this.post = post;
        return this;
    }

    public ObjectId getCommentId() {
        return commentId;
    }

    public ObjectId getReplyParentId() {
        return replyParentId;
    }

    public DisplayPostState setReplyParentId(ObjectId replyParentId) {
        this.replyParentId = replyParentId;
        return this;
    }

    public ObjectId getReplyParentPostId() {
        return replyParentPostId;
    }

    public DisplayPostState setReplyParentPostId(ObjectId replyParentPostId) {
        this.replyParentPostId = replyParentPostId;
        return this;
    }

    public DisplayPostState setCommentId(ObjectId commentId) {
        this.commentId = commentId;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public DisplayPostState setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}
