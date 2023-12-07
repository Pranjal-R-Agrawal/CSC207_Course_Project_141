package data_access;

import entity.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Concrete implementation of the CollabRequestDccessInterface for testing purposes.
 * @author Anbuselvan Ragunathan
 */
public class MockCollabRequestDataAccessObject implements CollabRequestDataAccessInterface{
    Map<Integer, Post> posts = new HashMap<>();
    ObjectId idAuthor = new ObjectId();
//    ObjectId idPost = new ObjectId();
    ObjectId idUser = new ObjectId();
    Map<Integer, User> users = new HashMap<>();
    private PostFactory postFactory;
    /**
     * Initializes a collection of posts and users for testing purposes.
     */
    public MockCollabRequestDataAccessObject() {
        this.postFactory = new ConcretePostFactory();
        Post post = postFactory.create(idAuthor, "Test Post", "Test Body", new ArrayList<String>());
        post.setId(new ObjectId());
        posts.put(0, post);
        users.put(0, new User("testuser", "testpassword", "testname", "test@email.com", "123-456-7890", "Test city", "coding"));


    }
    /**
     * Returns a post
     * @return a post
     */
    @Override
    public Post getPostByPostId(ObjectId id) {
        return posts.get(0);
    }
    /**
     * Returns a user
     * @return a user
     */
    @Override
    public User getUserById(ObjectId id) {
        return users.get(0);
    }
    /**
     * Adds a collaboration request to a user
     * @param collabRequest the collaboration request to be added
     */
    @Override
    public void addCollabRequest(CollabRequest collabRequest) {
        users.get(0).addCollabRequest(collabRequest.getPostId());
    }
    /**
     * Returns a comment
     * @return a comment
     */
    @Override
    public Comment getCommentByCommentID(ObjectId id) {
        return null;
    }

    //    public ObjectId getIdPost()
//    {
//        return idPost;
//    }
    /**
     * Returns a user
     * @return a user
     */
    public ObjectId getIdUser()
    {
        return idUser;
    }

    /**
     * Returns a id
     * @return a id pertaining to an author
     */
    public ObjectId getIdAuthor()
    {
        return idAuthor;
    }
    /**
     * Returns a id
     * @return a id pertaining to a post
     */
    public ObjectId getIdPost()
    {
        return posts.get(0).getId();
    }
}
