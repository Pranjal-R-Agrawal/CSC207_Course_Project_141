package data_access;

import entity.User;
import entity.UserFactory;
import entity.UserInterface;
import org.bson.types.ObjectId;

import java.util.HashMap;

/**
 * Mock DAO for view_user_info interactor test
 * @author Tanmay Shinde
 */
public class MockViewUserInfoDataAccessObject implements ViewUserInfoDataAccessInterface{

    HashMap<Integer, User> users;

    /**
     * Initializes a new Hashmap which represents a mock database for testing
     */
    public MockViewUserInfoDataAccessObject() {

        users = new HashMap<>();
        populateHashMap();

    }

    /**
     * Populates the HashMap with a test user
     */
    public void populateHashMap() {

        User user = (User) new UserFactory().create("testusername", "testpwd", "testname", "test@email.com", "0000000000", "testcity", "testexpertise");
        users.put(0, user);

    }

    /**
     * Concrete implementation of the getUserById function from the interface
     * @param userId the id of the user that is to be retreived from the database
     * @return the corresponding User object if it exists and null otherwise
     */
    @Override
    public User getUserById(ObjectId userId) {

        if (users.isEmpty()) {
            return null;
        }

        else {
            return users.get(0);
        }

    }

    /**
     * Clears the HashMap and resets it to represent a new empty HashMap
     */
    public void drop(){
        users.clear();
        users = new HashMap<>();
    }
}
