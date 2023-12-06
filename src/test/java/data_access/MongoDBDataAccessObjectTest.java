package data_access;

import entity.*;

import static org.junit.Assert.*;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

public class MongoDBDataAccessObjectTest {
    MongoDBDataAccessObject dataAccessObject;

    @Test
    public void testUsernameUsedEmptyCollection() {
        assert !dataAccessObject.usernameUsed("username");
    }

    @Test
    public void testAddUserOne() {
        User user = new User("username", "password", "", "", "", "", "");
        dataAccessObject.addUser(user);
        assertTrue(dataAccessObject.usernameUsed("username"));
    }

    @Test
    public void testUsernameUsedOneUser() {
        assertFalse(dataAccessObject.usernameUsed("username"));
        User user = new User("username", "password", "", "", "", "", "");
        dataAccessObject.addUser(user);
        assertTrue(dataAccessObject.usernameUsed("username"));
    }

    @Test
    public void testIsValid() {
        assertFalse(dataAccessObject.isValid("username", "password"));
        User user = new User("username", "password", "", "", "", "", "");
        dataAccessObject.addUser(user);
        assertTrue(dataAccessObject.isValid("username", "password"));
    }

    @Test
    public void testGetUserByUsername() {
        User user = new User("username", "password", "", "", "", "", "");
        dataAccessObject.addUser(user);
        assertEquals(user.getId(), dataAccessObject.getUserByUsername("username").getId());
    }

    @Test
    public void testGetUserById() {
        User user = new User("username", "password", "", "", "", "", "");
        dataAccessObject.addUser(user);
        assertEquals(user.getId(), dataAccessObject.getUserById(user.getId()).getId());
    }

    @Test
    public void testGetCommentsByParentPostID() {
        Comment comment = (Comment) new CommentFactory().create(new ObjectId(), new ObjectId(), new ObjectId(), null, null);
        dataAccessObject.addComment(comment);
        assertEquals(comment.getId(), dataAccessObject.getCommentsByParentPostID(comment.getParentPostId()).get(0).getId());
    }

    @Test
    public void testGetCommentByCommentID() {
        Comment comment = (Comment) new CommentFactory().create(new ObjectId(), new ObjectId(), new ObjectId(), null, null);
        dataAccessObject.addComment(comment);
        assertEquals(comment.getId(), dataAccessObject.getCommentByCommentID(comment.getId()).getId());
    }

    @Test
    public void testGetPostByPostID() {
        Post post = new ConcretePostFactory().create(new ObjectId(), "", "", null);
        dataAccessObject.addPost(post);
        assertEquals(post.getId(), dataAccessObject.getPostByPostID(post.getId()).getId());
    }

    @Before
    public void setUpTest() {
        try {
            dataAccessObject = new MongoDBDataAccessObjectBuilder().setTestParameters().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        dataAccessObject.resetDatabase();
    }
}