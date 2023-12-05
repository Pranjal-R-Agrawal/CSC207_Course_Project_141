package use_case.create_comment.application_business_rules;

import org.bson.types.ObjectId;

public interface CreateCommentOutputBoundary {
    void prepareSuccessView(ObjectId commentId);
    void prepareFailureView(String error);
}
