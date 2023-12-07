package data_access;

import entity.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Concrete implementation of the ViewProfileDataAccessInterface for testing purposes.
 * @author Anbuselvan Ragunathan
 */
public class MockViewProfileDataAccessObject implements ViewProfileDataAccessInterface{
    Map<Integer, Post> posts = new HashMap<>();
    Map<Integer, User> users = new HashMap<>();

    public MockViewProfileDataAccessObject() {
        ObjectId authorid = new ObjectId();
        ObjectId userid = new ObjectId();
        ObjectId postid = new ObjectId();
        Post post = new ConcretePostFactory().create(authorid, "testtitle", "testbody", new ArrayList<String>());
        post.setId(postid);

        User user = (User)new UserFactory().create("testuser", "password123", "test", "test@email.com",
                "123-456-7890", "testCity", "coding");
        posts.put(0,post);
        users.put(0,user);
        user.setId(userid);

    }


    @Override
    /**
     * Returns the logged in user.
     * @return the user that is currently logged in
     */
    public User getLoggedInUser() {

        return users.get(0);
    }




    @Override
    /**
     * Returns a post from the database.
     * @return an idea containing its prompt only (no business model)
     */
    public Post getPostByPostID(ObjectId id) {
        return null;
    }

    @Override
    /**
     * not used
     */
    public CollabRequest getCollabRequestById(ObjectId id) {
        return null;
    }

    @Override
    /**
     * not used
     */
    public void addCollabRequest(ConcreteCollabRequest collabRequest) {

    }

    @Override
    /**
     * not used
     */
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public List<Post> getPostByAuthorId(ObjectId id) {
        ArrayList<Post> postlist = new ArrayList<>();
        postlist.add(posts.get(0));
        return postlist;
    }

    @Override
    public List<ConcreteCollabRequest> getCollabRequestByUsername(String username) {
        return null;
    }

    public List<Post> getPostByCollaboratorId(ObjectId id) {
        return new ArrayList<>();
    }
}
