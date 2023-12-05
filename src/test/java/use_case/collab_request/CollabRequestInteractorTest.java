//package use_case.collab_request;
//
//import data_access.CollabRequestDataAccessInterface;
//import data_access.MockCollabRequestDataAccessObject;
//import entity.CollabRequestFactory;
//import org.bson.types.ObjectId;
//import org.junit.Before;
//import org.junit.Test;
//import use_case.collab_request.application_business_rules.CollabRequestInputBoundary;
//import use_case.collab_request.application_business_rules.CollabRequestInteractor;
//import use_case.collab_request.application_business_rules.CollabRequestOutputBoundary;
//
//import java.util.Objects;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//public class CollabRequestInteractorTest {
//    public CollabRequestInteractorTest() {
//
//    }
//
//    @Test
//    public void successTest() {
//        CollabRequestDataAccessInterface collabRequestDataAccessObject = new MockCollabRequestDataAccessObject();
//        CollabRequestFactory collabRequestFactory = new CollabRequestFactory();
//        ObjectId objectId1 = new ObjectId();
//        ObjectId objectId2 = new ObjectId();
//        CollabRequestOutputBoundary successPresenter = new CollabRequestOutputBoundary() {
//            @Override
//            public void prepareSuccessView() {
//                assertTrue(collabRequestDataAccessObject.getUserById(objectId2).getCollaborationRequestIDs().size() == 1);
//            }
//        };
//
//        CollabRequestInputBoundary interactor = new CollabRequestInteractor(collabRequestDataAccessObject, successPresenter, collabRequestFactory);
//        interactor.execute(objectId1, objectId2);
//
//    }
//
//
//
//}
