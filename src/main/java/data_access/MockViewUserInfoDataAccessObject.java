package data_access;

import entity.User;
import entity.UserFactory;
import entity.UserInterface;
import org.bson.types.ObjectId;

import java.util.HashMap;

public class MockViewUserInfoDataAccessObject implements ViewUserInfoDataAccessInterface{

    HashMap<Integer, User> users;

    public MockViewUserInfoDataAccessObject() {
        users = new HashMap<>();
        User user = (User) new UserFactory().create("testusername", "testpwd", "testname", "test@email.com", "0000000000", "testcity", "testexpertise");
        users.put(0, user);
    }

    @Override
    public User getUserById(ObjectId userId) {

        if (users.isEmpty()) {
            return null;
        }

        else {
            return users.get(0);
        }

    }

    public void drop(){
        users.clear();
        users = new HashMap<>();
    }
}
