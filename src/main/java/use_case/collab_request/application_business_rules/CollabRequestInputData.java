package use_case.collab_request.application_business_rules;

import org.bson.types.ObjectId;

public class CollabRequestInputData {

    private final ObjectId postId;
    private final ObjectId commenterId;


    public CollabRequestInputData(ObjectId postId, ObjectId commenterId){
      this.postId = postId;
      this.commenterId = commenterId;
    }
    public ObjectId getPostId(){return postId;}
    public ObjectId getCommenterId() {return commenterId;}




}
