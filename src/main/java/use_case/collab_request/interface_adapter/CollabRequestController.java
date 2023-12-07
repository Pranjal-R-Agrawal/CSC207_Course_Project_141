package use_case.collab_request.interface_adapter;

import org.bson.types.ObjectId;
import use_case.collab_request.application_business_rules.CollabRequestInputBoundary;
import use_case.collab_request.application_business_rules.CollabRequestInputData;
/**
 * Represents a CollabRequestController
 */
public class CollabRequestController {
    final CollabRequestInputBoundary collabRequestInteractor;
    /**
     * Constructor for CollabRequestController
     * @param collabRequestInteractor
     */
    public CollabRequestController(CollabRequestInputBoundary collabRequestInteractor) {
        this.collabRequestInteractor = collabRequestInteractor;
    }
    /**
     * Executes the CollabRequestController
     * @param postId
     * @param commenterId
     */
    public void execute(ObjectId postId, ObjectId commenterId) {
        CollabRequestInputData collabRequestInputData = new CollabRequestInputData(postId,commenterId);
        collabRequestInteractor.execute(collabRequestInputData,null);
    }
}
