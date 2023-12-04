package data_access;

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
}
