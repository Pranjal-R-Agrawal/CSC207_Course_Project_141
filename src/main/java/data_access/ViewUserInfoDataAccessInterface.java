package data_access;

import entity.User;
import org.bson.types.ObjectId;

/**
 * Represents a Data Access Object to access data from the database for the View User Info Use Case
 * @author Tanmay Shinde
 */
public interface ViewUserInfoDataAccessInterface {

    /**
     * Retreives a User object from the database given a userId
     * @param userId the id of the user that is to be retreived from the database
     * @return a User object corresponding to the given userId
     */
    User getUserById(ObjectId userId);

    void drop();
}
