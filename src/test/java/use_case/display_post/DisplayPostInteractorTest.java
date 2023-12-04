package use_case.display_post;

import entity.Comment;
import entity.Post;
import entity.User;
import org.bson.types.ObjectId;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import use_case.display_post.application_business_rules.DisplayPostInputData;
import use_case.display_post.application_business_rules.DisplayPostInteractor;
import use_case.display_post.application_business_rules.DisplayPostOutputBoundary;
import use_case.display_post.application_business_rules.DisplayPostOutputData;
import data_access.MongoDBDataAccessObject;
import data_access.MongoDBDataAccessObjectBuilder;

import java.util.ArrayList;
import java.util.Arrays;
public class DisplayPostInteractorTest {
    MongoDBDataAccessObject mongoDBDataAccessObject;

    @Test
    public void testInvalidCommentID() {
        ObjectId commentID = new ObjectId();
        DisplayPostOutputBoundary displayPostOutputBoundary = new DisplayPostOutputBoundary() {
            @Override
            public void prepareFailView(String error) {
                assertEquals(error, "Comment not found");
            }
            @Override
            public void prepareSuccessView(DisplayPostOutputData displayPostOutputData) {fail();}
        };
        DisplayPostInteractor displayPostInteractor = new DisplayPostInteractor(mongoDBDataAccessObject, displayPostOutputBoundary);
        displayPostInteractor.execute(new DisplayPostInputData(commentID, 0));
    }

    @Test
    public void testInvalidPostID() {
        ObjectId postID = new ObjectId();
        DisplayPostOutputBoundary displayPostOutputBoundary = new DisplayPostOutputBoundary() {
            @Override
            public void prepareFailView(String error) {
                assertEquals(error, "Post not found");
            }
            @Override
            public void prepareSuccessView(DisplayPostOutputData displayPostOutputData) {fail();}
        };
        DisplayPostInteractor displayPostInteractor = new DisplayPostInteractor(mongoDBDataAccessObject, displayPostOutputBoundary);
        displayPostInteractor.execute(new DisplayPostInputData(postID, 1));
    }

    @Test
    public void testValidCommentID() {
        User user = new User("username", "password", "name", "email", "phone", "city", "field");
        mongoDBDataAccessObject.addUser(user);

        Post post = new Post(user.getId(), "title", "body", new ArrayList<String>(Arrays.asList("qual1", "qual2")));
        mongoDBDataAccessObject.addPost(post);

        Comment comment = new Comment(post.getId(), post.getId(), user.getId(), "body", new ArrayList<String>(Arrays.asList("qual1", "qual2")));
        mongoDBDataAccessObject.addComment(comment);

        ObjectId commentID = comment.getId();

        DisplayPostOutputBoundary displayPostOutputBoundary = new DisplayPostOutputBoundary() {
            @Override
            public void prepareFailView(String error) {fail();}
            @Override
            public void prepareSuccessView(DisplayPostOutputData displayPostOutputData) {
                assertEquals(displayPostOutputData.getComments().size(), 1);
            }
        };
        DisplayPostInteractor displayPostInteractor = new DisplayPostInteractor(mongoDBDataAccessObject, displayPostOutputBoundary);
        displayPostInteractor.execute(new DisplayPostInputData(commentID, 0));
    }

    @Test
    public void testValidPostID_3_comment() {
        User user = new User("username", "password", "name", "email", "phone", "city", "field");
        mongoDBDataAccessObject.addUser(user);

        Post post = new Post(user.getId(), "title", "body", new ArrayList<String>(Arrays.asList("qual1", "qual2")));
        mongoDBDataAccessObject.addPost(post);

        Comment comment = new Comment(post.getId(), post.getId(), user.getId(), "body", new ArrayList<String>(Arrays.asList("qual1", "qual2")));
        mongoDBDataAccessObject.addComment(comment);

        Comment comment2 = new Comment(post.getId(), post.getId(), user.getId(), "body", new ArrayList<String>(Arrays.asList("qual1", "qual2")));
        mongoDBDataAccessObject.addComment(comment2);

        Comment comment3 = new Comment(post.getId(), post.getId(), user.getId(), "body", new ArrayList<String>(Arrays.asList("qual1", "qual2")));
        mongoDBDataAccessObject.addComment(comment3);

        ObjectId postID = post.getId();

        DisplayPostOutputBoundary displayPostOutputBoundary = new DisplayPostOutputBoundary() {
            @Override
            public void prepareFailView(String error) {fail();}
            @Override
            public void prepareSuccessView(DisplayPostOutputData displayPostOutputData) {
                assertEquals(displayPostOutputData.getComments().size(), 3);
            }
        };
        DisplayPostInteractor displayPostInteractor = new DisplayPostInteractor(mongoDBDataAccessObject, displayPostOutputBoundary);
        displayPostInteractor.execute(new DisplayPostInputData(postID, 1));
    }
    
    @Test
    public void testValidPostID_nested() {
        User user = new User("username", "password", "name", "email", "phone", "city", "field");
        mongoDBDataAccessObject.addUser(user);

        Post post = new Post(user.getId(), "title", "body", new ArrayList<String>(Arrays.asList("qual1", "qual2")));
        mongoDBDataAccessObject.addPost(post);

        Comment comment = new Comment(post.getId(), post.getId(), user.getId(), "body", new ArrayList<String>(Arrays.asList("qual1", "qual2")));
        mongoDBDataAccessObject.addComment(comment);

        Comment comment2 = new Comment(comment.getId(), post.getId(), user.getId(), "body", new ArrayList<String>(Arrays.asList("qual1", "qual2")));
        mongoDBDataAccessObject.addComment(comment2);

        ObjectId postID = post.getId();

        DisplayPostOutputBoundary displayPostOutputBoundary = new DisplayPostOutputBoundary() {
            @Override
            public void prepareFailView(String error) {fail();}
            @Override
            public void prepareSuccessView(DisplayPostOutputData displayPostOutputData) {
                assertEquals(displayPostOutputData.getComments().size(), 2);
            }
        };
        DisplayPostInteractor displayPostInteractor = new DisplayPostInteractor(mongoDBDataAccessObject, displayPostOutputBoundary);
        displayPostInteractor.execute(new DisplayPostInputData(postID, 1));
    }

    @Test
    public void testProcessedCommentData() {
        User user = new User("username", "password", "name", "email", "phone", "city", "field");
        mongoDBDataAccessObject.addUser(user);

        Post post = new Post(user.getId(), "title", "body", new ArrayList<String>(Arrays.asList("qual1", "qual2")));
        mongoDBDataAccessObject.addPost(post);

        Comment comment = new Comment(post.getId(), post.getId(), user.getId(), "body", new ArrayList<String>(Arrays.asList("qual1", "qual2")));
        mongoDBDataAccessObject.addComment(comment);

        ObjectId postId = post.getId();
        ObjectId commentID = comment.getId();

        DisplayPostOutputBoundary displayPostOutputBoundary = new DisplayPostOutputBoundary() {
            @Override
            public void prepareFailView(String error) {fail();}
            @Override
            public void prepareSuccessView(DisplayPostOutputData displayPostOutputData) {
                assertEquals(displayPostOutputData.getComments().get(commentID).get("id"), commentID);
                assertEquals(displayPostOutputData.getComments().get(commentID).get("parentPostId"), postId);
                assertEquals(displayPostOutputData.getComments().get(commentID).get("parentId"), postId);
                assertEquals(displayPostOutputData.getComments().get(commentID).get("authorId"), user.getId());
                assertEquals(displayPostOutputData.getComments().get(commentID).get("username"), user.getUsername());
                assertEquals(displayPostOutputData.getComments().get(commentID).get("body"), "body");
                assertEquals(displayPostOutputData.getComments().get(commentID).get("qualifications"), new ArrayList<String>(Arrays.asList("qual1", "qual2")));
            }
        };
        DisplayPostInteractor displayPostInteractor = new DisplayPostInteractor(mongoDBDataAccessObject, displayPostOutputBoundary);
        displayPostInteractor.execute(new DisplayPostInputData(commentID, 0));
    }

    @Before
    public void setUpTest() {
        try {
            mongoDBDataAccessObject = new MongoDBDataAccessObjectBuilder().setTestParameters().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        mongoDBDataAccessObject.resetDatabase();
    }
}
