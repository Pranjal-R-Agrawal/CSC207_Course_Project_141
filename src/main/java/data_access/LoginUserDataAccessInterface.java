package data_access;

import entity.User;
import org.bson.types.ObjectId;

public interface LoginUserDataAccessInterface {
    boolean isValid(String username, String password);
    User getUserByUsername(String username);
    void setLoggedInUserID(ObjectId id);
}
