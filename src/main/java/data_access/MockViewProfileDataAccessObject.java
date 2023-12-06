package data_access;

import entity.CollabRequest;
import entity.Post;
import entity.User;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class MockViewProfileDataAccessObject implements ViewProfileDataAccessInterface{
    @Override
    public User getLoggedInUser() {
        User testUser = new User("testuser", "password123", "test", "test@email.com",
                "123-456-7890", "testCity", "coding");
        return testUser;
    }

    @Override
    public Post getPostByPostID(ObjectId id) {
        return null;
    }

    @Override
    public CollabRequest getCollabRequestById(ObjectId id) {
        return null;
    }

    @Override
    public void addCollabRequest(CollabRequest collabRequest) {

    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }
}
