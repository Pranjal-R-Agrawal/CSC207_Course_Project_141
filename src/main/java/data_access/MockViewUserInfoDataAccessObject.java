package data_access;

import entity.User;
import entity.UserFactory;
import entity.UserInterface;
import org.bson.types.ObjectId;

public class MockViewUserInfoDataAccessObject implements ViewUserInfoDataAccessInterface{

    ObjectId userId = new ObjectId();

    public MockViewUserInfoDataAccessObject() {
        UserInterface user = new UserFactory().create("testusername", "testpwd", "testname", "test@email.com", "0000000000", "testcity", "testexpertise");
    }

    @Override
    public User getUserById(ObjectId userId) {
        return null;
    }
}
