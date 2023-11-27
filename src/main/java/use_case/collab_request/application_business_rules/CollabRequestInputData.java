package use_case.collab_request.application_business_rules;

import org.bson.types.ObjectId;

public class CollabRequestInputData {
    private final ObjectId commentId;

    public CollabRequestInputData(ObjectId commentId) {
        this.commentId = commentId;
    }

    public ObjectId getCommentId() {
        return commentId;
    }

}
