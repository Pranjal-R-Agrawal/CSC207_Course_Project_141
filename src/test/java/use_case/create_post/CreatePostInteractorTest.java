package use_case.create_post;

import data_access.CreatePostDataAccessInterface;
import data_access.MockMongoDBDataAccessObject;
import entity.PostFactory;
import org.bson.types.ObjectId;
import org.junit.Test;
import use_case.create_post.application_business_rules.CreatePostInputBoundary;
import use_case.create_post.application_business_rules.CreatePostInputData;
import use_case.create_post.application_business_rules.CreatePostInteractor;
import use_case.create_post.application_business_rules.CreatePostOutputBoundary;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class CreatePostInteractorTest {

    @Test
    public void testTitleEmpty(){
        CreatePostInputData inputData = new CreatePostInputData("", "test body", "test;qualifications");
        CreatePostDataAccessInterface dataAccessObject = new MockMongoDBDataAccessObject();

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
        CreatePostInputBoundary interactor = new CreatePostInteractor(createPostPresenter, dataAccessObject, new PostFactory());
        interactor.execute(inputData);
    }
    @Test
    public void testBodyEmpty(){
        CreatePostInputData inputData = new CreatePostInputData("test title", "", "test;qualifications");
        CreatePostDataAccessInterface dataAccessObject = new MockMongoDBDataAccessObject();

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
        CreatePostInputBoundary interactor = new CreatePostInteractor(createPostPresenter, dataAccessObject, new PostFactory());
        interactor.execute(inputData);
    }
    @Test
    public void testColabEmpty(){
        CreatePostInputData inputData = new CreatePostInputData("test title", "test body", "");
        CreatePostDataAccessInterface dataAccessObject = new MockMongoDBDataAccessObject();

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
        CreatePostInputBoundary interactor = new CreatePostInteractor(createPostPresenter, dataAccessObject, new PostFactory());
        interactor.execute(inputData);
    }
    @Test
    public void testValidInput(){
        CreatePostInputData inputData = new CreatePostInputData("test title", "test body", "test;qualifications");
        CreatePostDataAccessInterface dataAccessObject = new MockMongoDBDataAccessObject();

        CreatePostOutputBoundary createPostPresenter = new CreatePostOutputBoundary() {
            @Override
            public void prepareSuccessView(ObjectId postId) {
                assertNotEquals(null, dataAccessObject.getPostByPostID(postId));
                assertEquals("test title", dataAccessObject.getPostByPostID(postId).getTitle());
                assertEquals("test body", dataAccessObject.getPostByPostID(postId).getBody());
                assertEquals(Arrays.asList("test", "qualifications"), dataAccessObject.getPostByPostID(postId).getSuggestedCollaboratorQualifications());
            }

            @Override
            public void prepareFailureView(String error) {
                fail("Use case failure is unexpected.");
            }
        };
        CreatePostInputBoundary interactor = new CreatePostInteractor(createPostPresenter, dataAccessObject, new PostFactory());
        interactor.execute(inputData);
    }
}
