package use_case.create_post;

import data_access.CreatePostDataAccessInterface;
import data_access.MongoDBDataAccessObjectBuilder;
import entity.ConcretePostFactory;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import use_case.create_post.application_business_rules.CreatePostInputBoundary;
import use_case.create_post.application_business_rules.CreatePostInputData;
import use_case.create_post.application_business_rules.CreatePostInteractor;
import use_case.create_post.application_business_rules.CreatePostOutputBoundary;

import java.util.Arrays;

import static org.junit.Assert.*;

public class CreatePostIntegrationTest {
    CreatePostDataAccessInterface mongoDBDataAccessObject;

    @Before
    public void setup() {
        try {
            this.mongoDBDataAccessObject = new MongoDBDataAccessObjectBuilder().setTestParameters().build();
            mongoDBDataAccessObject.setLoggedInUserID(new ObjectId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testTitleEmpty(){
        CreatePostInputData inputData = new CreatePostInputData("", "test body", "test;qualifications");

        CreatePostOutputBoundary createPostPresenter = new CreatePostOutputBoundary() {
            @Override
            public void prepareSuccessView(ObjectId postId) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailureView(String error) {
                assertEquals(error, "Please enter a title.");
            }
        };
        CreatePostInputBoundary interactor = new CreatePostInteractor(createPostPresenter, mongoDBDataAccessObject, new ConcretePostFactory());
        interactor.execute(inputData);
    }
    @Test
    public void testBodyEmpty(){
        CreatePostInputData inputData = new CreatePostInputData("test title", "", "test;qualifications");

        CreatePostOutputBoundary createPostPresenter = new CreatePostOutputBoundary() {
            @Override
            public void prepareSuccessView(ObjectId postId) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailureView(String error) {
                assertEquals(error, "Please enter text in body.");
            }
        };
        CreatePostInputBoundary interactor = new CreatePostInteractor(createPostPresenter, mongoDBDataAccessObject, new ConcretePostFactory());
        interactor.execute(inputData);
    }
    @Test
    public void testColabEmpty(){
        CreatePostInputData inputData = new CreatePostInputData("test title", "test body", "");

        CreatePostOutputBoundary createPostPresenter = new CreatePostOutputBoundary() {
            @Override
            public void prepareSuccessView(ObjectId postId) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailureView(String error) {
                assertEquals(error, "Please at least 1 suggested collaborator qualification.");
            }
        };
        CreatePostInputBoundary interactor = new CreatePostInteractor(createPostPresenter, mongoDBDataAccessObject, new ConcretePostFactory());
        interactor.execute(inputData);
    }
    @Test
    public void testValidInput(){
        CreatePostInputData inputData = new CreatePostInputData("test title", "test body", "test;qualifications");

        CreatePostOutputBoundary createPostPresenter = new CreatePostOutputBoundary() {
            @Override
            public void prepareSuccessView(ObjectId postId) {
                assertNotEquals(null, mongoDBDataAccessObject.getPostByPostID(postId));
                assertEquals("test title", mongoDBDataAccessObject.getPostByPostID(postId).getTitle());
                assertEquals("test body", mongoDBDataAccessObject.getPostByPostID(postId).getBody());
                assertEquals(Arrays.asList("test", "qualifications"), mongoDBDataAccessObject.getPostByPostID(postId).getSuggestedCollaboratorQualifications());
            }

            @Override
            public void prepareFailureView(String error) {
                fail("Use case failure is unexpected.");
            }
        };
        CreatePostInputBoundary interactor = new CreatePostInteractor(createPostPresenter, mongoDBDataAccessObject, new ConcretePostFactory());
        interactor.execute(inputData);
    }
}
