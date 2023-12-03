package data_access;

import entity.Post;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.Map;

public class MockMongoDBDataAccessObject implements CreatePostDataAccessInterface{
    private Map<ObjectId, Post> postsMap = new HashMap<>();
    private ObjectId loggedInUserID;
    @Override
    public void addPost(Post post) {
        postsMap.put(post.getId(),post);
    }

    @Override
    public ObjectId getLoggedInUserId() {
        return loggedInUserID;
    }

    @Override
    public void setLoggedInUserId(ObjectId id) {
        loggedInUserID = id;
    }

    @Override
    public Post getPostByPostID(ObjectId id) {
        return postsMap.get(id);
    }
}
