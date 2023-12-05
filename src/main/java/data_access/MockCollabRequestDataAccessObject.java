package data_access;

import entity.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MockCollabRequestDataAccessObject implements CollabRequestDataAccessInterface{
    Map<Integer, Post> posts = new HashMap<>();
    ObjectId id = new ObjectId();
    Map<Integer, User> users = new HashMap<>();
    private PostFactory postFactory;
    public MockCollabRequestDataAccessObject() {
        this.postFactory = new ConcretePostFactory();
        posts.put(0, postFactory.create(id, "Test Post", "Test Body", new ArrayList<String>()));
        users.put(0, new User("testuser", "testpassword", "testname", "test@email.com", "123-456-7890", "Test city", "coding"));


    }
    @Override
    public Post getPostByPostId(ObjectId id) {
        return posts.get(0);
    }

    @Override
    public User getUserById(ObjectId id) {
        return users.get(0);
    }
    @Override
    public void addCollabRequest(CollabRequest collabRequest) {
        users.get(0).addCollaborationRequestId(new ObjectId());
    }


}
