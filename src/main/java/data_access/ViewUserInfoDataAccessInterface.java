package data_access;

import entity.User;
import org.bson.types.ObjectId;

public interface ViewUserInfoDataAccessInterface {

    User getUserById(ObjectId userId);

}
