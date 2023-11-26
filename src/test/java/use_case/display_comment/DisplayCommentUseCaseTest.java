package use_case.display_comment;

import entity.Comment;
import entity.Post;
import entity.User;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import use_case.display_comment.application_business_rules.DisplayCommentInteractor;
import use_case.display_comment.application_business_rules.DisplayCommentOutputBoundary;
import use_case.display_comment.interface_adapter.DisplayCommentController;
import use_case.display_comment.interface_adapter.DisplayCommentPresenter;
import view.DisplayCommentsViewModel;
import data_access.MongoDBDataAccessObject;
import data_access.MongoDBDataAccessObjectBuilder;

import java.util.ArrayList;
import java.util.Arrays;

public class DisplayCommentUseCaseTest {
    DisplayCommentsViewModel displayCommentsViewModel;
    MongoDBDataAccessObject mongoDBDataAccessObject;
    DisplayCommentController displayCommentController;

    @Test
    public void testInvalidCommentID() {
        ObjectId commentID = new ObjectId();
        displayCommentController.execute(commentID, "comment");
        assert displayCommentsViewModel.getState().getErrorMessage().equals("No comments found");
    }

    @Test
    public void testInvalidPostID() {
        ObjectId postID = new ObjectId();
        displayCommentController.execute(postID, "post");
        assert displayCommentsViewModel.getState().getErrorMessage().equals("No comments found");
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

        displayCommentController.execute(commentID, "comment");

        assert displayCommentsViewModel.getState().getComments().size() == 1;
    }

    @Test
    public void testValidPostID_1_comment() {
        User user = new User("username", "password", "name", "email", "phone", "city", "field");
        mongoDBDataAccessObject.addUser(user);

        Post post = new Post(user.getId(), "title", "body", new ArrayList<String>(Arrays.asList("qual1", "qual2")));
        mongoDBDataAccessObject.addPost(post);

        Comment comment = new Comment(post.getId(), post.getId(), user.getId(), "body", new ArrayList<String>(Arrays.asList("qual1", "qual2")));
        mongoDBDataAccessObject.addComment(comment);

        ObjectId postID = post.getId();

        displayCommentController.execute(postID, "post");

        assert displayCommentsViewModel.getState().getComments().size() == 1;
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

        displayCommentController.execute(postID, "post");

        assert displayCommentsViewModel.getState().getComments().size() == 3;
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

        Comment comment3 = new Comment(comment2.getId(), post.getId(), user.getId(), "body", new ArrayList<String>(Arrays.asList("qual1", "qual2")));
        mongoDBDataAccessObject.addComment(comment3);

        ObjectId postID = post.getId();

        displayCommentController.execute(postID, "post");

        assert displayCommentsViewModel.getState().getComments().size() == 3;
    }

    @Test
    public void testProcessedCommentData() {
        User user = new User("username", "password", "name", "email", "phone", "city", "field");
        mongoDBDataAccessObject.addUser(user);

        Post post = new Post(user.getId(), "title", "body", new ArrayList<String>(Arrays.asList("qual1", "qual2")));
        mongoDBDataAccessObject.addPost(post);

        Comment comment = new Comment(post.getId(), post.getId(), user.getId(), "body", new ArrayList<String>(Arrays.asList("qual1", "qual2")));
        mongoDBDataAccessObject.addComment(comment);

        mongoDBDataAccessObject.setLoggedInUserID(user.getId());

        ObjectId postId = post.getId();
        ObjectId commentID = comment.getId();

        displayCommentController.execute(commentID, "comment");

        assert displayCommentsViewModel.getState().getComments().get(commentID).get("id").equals(commentID);
        assert displayCommentsViewModel.getState().getComments().get(commentID).get("parentPostId").equals(postId);
        assert displayCommentsViewModel.getState().getComments().get(commentID).get("parentId").equals(postId);
        assert displayCommentsViewModel.getState().getComments().get(commentID).get("authorId").equals(user.getId());
        assert displayCommentsViewModel.getState().getComments().get(commentID).get("username").equals(user.getUsername());
        assert displayCommentsViewModel.getState().getComments().get(commentID).get("body").equals("body");
        assert displayCommentsViewModel.getState().getComments().get(commentID).get("qualifications").equals(new ArrayList<String>(Arrays.asList("qual1", "qual2")));
        assert displayCommentsViewModel.getState().getComments().get(commentID).get("comment_author_is_post_author").equals(true);
        assert displayCommentsViewModel.getState().getComments().get(commentID).get("logged_in_user_is_comment_author").equals(true);
        assert displayCommentsViewModel.getState().getComments().get(commentID).get("logged_in_user_is_post_author").equals(true);
        assert displayCommentsViewModel.getState().getComments().get(commentID).get("show_more_info_button").equals(true);
    }

    @Before
    public void setUpTest() {
        try {
            mongoDBDataAccessObject = new MongoDBDataAccessObjectBuilder().setTestParameters().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        displayCommentsViewModel = new DisplayCommentsViewModel();
        DisplayCommentOutputBoundary displayCommentPresenter = new DisplayCommentPresenter(displayCommentsViewModel);
        DisplayCommentInteractor displayCommentInteractor = new DisplayCommentInteractor(mongoDBDataAccessObject, displayCommentPresenter);
        displayCommentController = new DisplayCommentController(displayCommentInteractor);
        mongoDBDataAccessObject.resetDatabase();
    }
}
