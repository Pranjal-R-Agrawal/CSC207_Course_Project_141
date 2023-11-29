package data_access;

import org.bson.types.ObjectId;

public interface ViewUserInfoDataAccessInterface {

    boolean checkUserExists(ObjectId userId);

    String getUsernameByUserId(ObjectId userId);

    double getUserRating(ObjectId userId);

    String getUserEmail(ObjectId userId);

    String getPhoneNumber(ObjectId userId);

    String getNameByUserId(ObjectId userId);

}
