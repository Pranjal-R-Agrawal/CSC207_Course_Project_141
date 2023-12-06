package use_case.collab_request.application_business_rules;

import entity.User;
import org.bson.types.ObjectId;

/**
 * Represents a CollabRequestInputBoundary
 */
public interface CollabRequestInputBoundary {
    void execute(CollabRequestInputData collabRequestInputData, User actualCommenterForTests);
}
