package use_case.collab_request.interface_adapter;

import org.bson.types.ObjectId;
import use_case.collab_request.application_business_rules.CollabRequestInputBoundary;
import use_case.collab_request.application_business_rules.CollabRequestInputData;

public class CollabRequestController {
    final CollabRequestInputBoundary collabRequestInteractor;

    public CollabRequestController(CollabRequestInputBoundary collabRequestInteractor) {
        this.collabRequestInteractor = collabRequestInteractor;
    }

    public void execute(ObjectId postId, ObjectId userId) {
        collabRequestInteractor.execute(postId, userId,null);
    }
}
