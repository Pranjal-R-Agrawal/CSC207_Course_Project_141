package data_access;

import org.bson.types.ObjectId;

public interface ViewUserInfoDataAccessInterface {
    String getUsernameByUserId(ObjectId userId);

    double getUserRating(ObjectId userId);

    String getUserEmail(ObjectId userId);

    String getPhoneNumber(ObjectId userId);
}
