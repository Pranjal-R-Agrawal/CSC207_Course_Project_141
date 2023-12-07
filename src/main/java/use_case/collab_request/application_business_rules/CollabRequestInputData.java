package use_case.collab_request.application_business_rules;

import org.bson.types.ObjectId;
/**
 * Represents a CollabRequestInputData
 */
public class CollabRequestInputData {

    private final ObjectId postId;
    private final ObjectId commenterId;

    /**
     * Constructor for CollabRequestInputData
     * @param postId
     * @param commenterId
     */
    public CollabRequestInputData(ObjectId postId, ObjectId commenterId){
      this.postId = postId;
      this.commenterId = commenterId;
    }
    /**
     * Returns the id of the Post.
     * @return the id of the Post
     */
    public ObjectId getPostId(){return postId;}
    /**
     * Returns the commenterId
     * @return the commenterId
     */
    public ObjectId getCommenterId() {return commenterId;}




}
