package use_case.collab_request.interface_adapter;

import org.bson.types.ObjectId;
/**
 * Represents a CollabRequestState
 */
public class CollabRequestState {
    private ObjectId postId;
    private ObjectId commenterId;
    /**
     * Sets the objectid of the post
     * @param postId the idea prompt
     * @return Returns updated state containing updated postid
     */
    public CollabRequestState setPostId(ObjectId postId) {
        this.postId = postId;
        return this;
    }
    /**
     * Return the state's postId
     * @return Returns updated state containing updated postid
     */
    public ObjectId getPostId() {

        return postId;
    }
    /**
     * Sets the commentid of the post
     * @param commenterId the idea prompt
     * @return Returns updated state containing updated commentId
     */
    public CollabRequestState setCommenterId(ObjectId commenterId) {
        this.commenterId = commenterId;
        return this;
    }
    /**
     * Return the state's commenterId
     * @return Returns updated state containing updated commenterId
     */
    public ObjectId getCommenterId() {

        return commenterId;
    }


}
