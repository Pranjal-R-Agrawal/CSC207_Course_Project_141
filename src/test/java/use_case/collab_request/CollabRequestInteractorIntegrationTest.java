package use_case.collab_request;

import com.mongodb.client.MongoCollection;
import data_access.MockCollabRequestDataAccessObject;
import data_access.MongoDBDataAccessObject;
import data_access.MongoDBDataAccessObjectBuilder;
import entity.*;
import org.bson.types.ObjectId;
import org.junit.Test;
import use_case.collab_request.application_business_rules.CollabRequestInputBoundary;
import use_case.collab_request.application_business_rules.CollabRequestInputData;
import use_case.collab_request.application_business_rules.CollabRequestInteractor;
import use_case.collab_request.application_business_rules.CollabRequestOutputBoundary;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CollabRequestInteractorIntegrationTest {
    @Test
    public void successTest() {
//        ObjectId authorid = new ObjectId();
//        ObjectId postid = new ObjectId();

        MongoDBDataAccessObject mongoDBDataAccessObject;

        try {

            mongoDBDataAccessObject = new MongoDBDataAccessObjectBuilder().setTestParameters().build();
            CollabRequest collabRequest = new ConcreteCollabRequest();
            mongoDBDataAccessObject.addCollabRequest(collabRequest);
            mongoDBDataAccessObject.resetDatabase();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        UserFactory userFactory = new UserFactory();
        User author = (User) userFactory.create("testuser1", "testpassword1", "testname1", "test1@email.com", "124-456-7890", "Test city1", "coding1");

        mongoDBDataAccessObject.addUser(author);
        mongoDBDataAccessObject.setLoggedInUserID(author.getId());


        User testAuthor = mongoDBDataAccessObject.getUserByUsername("testuser1");
        ConcretePostFactory concretePostFactory = new ConcretePostFactory();
        Post post = concretePostFactory.create(testAuthor.getId(),"Test Post", "Test Body", new ArrayList<String>());
        mongoDBDataAccessObject.addPost(post);
        Post testPost  = mongoDBDataAccessObject.getPostByPostId(post.getId());

        User commenter = (User) userFactory.create("testuser", "testpassword", "testname", "test@email.com", "123-456-7890", "Test city", "coding");
        mongoDBDataAccessObject.addUser(commenter); // assume ids have been created
        User testCommenter = mongoDBDataAccessObject.getUserByUsername("testuser");
        CollabRequestFactory collabRequestFactory = new ConcreteCollabRequestFactory();

        CollabRequestOutputBoundary successPresenter = new CollabRequestOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                assertEquals(testPost.getId(), testCommenter.getCollaborationRequestIDs().get(0));
            }
        };
        CollabRequestInputData collabRequestInputData = new CollabRequestInputData(testPost.getId(),testCommenter.getId());
        CollabRequestInputBoundary interactor = new CollabRequestInteractor(mongoDBDataAccessObject, successPresenter, collabRequestFactory);
        interactor.execute(collabRequestInputData, testCommenter);

    }
}
