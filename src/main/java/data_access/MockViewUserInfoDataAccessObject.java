package data_access;

import entity.User;
import entity.UserFactory;
import org.bson.types.ObjectId;

public class MockViewUserInfoDataAccessObject implements ViewUserInfoDataAccessInterface{

    ObjectId userId = new ObjectId();
    private UserFactory userFactory;

    public MockViewUserInfoDataAccessObject() {

    }

    @Override
    public User getUserById(ObjectId userId) {
        return null;
    }
}
