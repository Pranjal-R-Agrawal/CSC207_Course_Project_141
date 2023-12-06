package use_case.collab_request.interface_adapter;

import org.bson.types.ObjectId;

public class CollabRequestState {
    private ObjectId postId;
    private ObjectId commenterId;

    public CollabRequestState setPostId(ObjectId postId) {
        this.postId = postId;
        return this;
    }
    public ObjectId getPostId() {

        return postId;
    }
    public CollabRequestState setCommenterId(ObjectId commenterId) {
        this.commenterId = commenterId;
        return this;
    }
    public ObjectId getCommenterId() {

        return commenterId;
    }


}
