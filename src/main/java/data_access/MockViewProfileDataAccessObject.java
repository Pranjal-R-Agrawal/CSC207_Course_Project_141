package data_access;

import entity.CollabRequest;
import entity.Post;
import entity.User;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
/**
 * Concrete implementation of the ViewProfileDataAccessInterface for testing purposes.
 * @author Anbuselvan Ragunathan
 */
public class MockViewProfileDataAccessObject implements ViewProfileDataAccessInterface{
    @Override
    /**
     * Statically create a user
     */
    public User getLoggedInUser() {
        User testUser = new User("testuser", "password123", "test", "test@email.com",
                "123-456-7890", "testCity", "coding");
        return testUser;
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
    public void addCollabRequest(CollabRequest collabRequest) {

    }

    @Override
    /**
     * not used
     */
    public User getUserByUsername(String username) {
        return null;
    }
}
