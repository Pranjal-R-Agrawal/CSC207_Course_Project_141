package data_access;

import entity.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MockCollabRequestDataAccessObject implements CollabRequestDataAccessInterface{
    Map<Integer, Post> posts = new HashMap<>();
    ObjectId idAuthor = new ObjectId();
//    ObjectId idPost = new ObjectId();
    ObjectId idUser = new ObjectId();
    Map<Integer, User> users = new HashMap<>();
    private PostFactory postFactory;
    public MockCollabRequestDataAccessObject() {
        this.postFactory = new ConcretePostFactory();
        Post post = postFactory.create(idAuthor, "Test Post", "Test Body", new ArrayList<String>());
        post.setId(new ObjectId());
        posts.put(0, post);
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
        users.get(0).addCollabRequest(collabRequest.getPostId());
    }
//    public ObjectId getIdPost()
//    {
//        return idPost;
//    }
    public ObjectId getIdUser()
    {
        return idUser;
    }

    public ObjectId getIdAuthor()
    {
        return idAuthor;
    }
    public ObjectId getIdPost()
    {
        return posts.get(0).getId();
    }
}
