package use_case.create_post.application_business_rules;

import org.bson.types.ObjectId;

public interface CreatePostOutputBoundary {
    void prepareSuccessView(ObjectId postId);
    void prepareFailureView(String error);
}
