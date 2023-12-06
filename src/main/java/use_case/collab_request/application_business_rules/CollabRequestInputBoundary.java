package use_case.collab_request.application_business_rules;

import entity.User;
import org.bson.types.ObjectId;

public interface CollabRequestInputBoundary {
    void execute(ObjectId postId, ObjectId userId, User actualCommenterForTests);
}
