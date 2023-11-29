package use_case.view_user_info.application_business_rules;

import org.bson.types.ObjectId;

public class ViewUserInfoOutputData {

    private final ObjectId userId;

    public ViewUserInfoOutputData(ObjectId userId) {
        this.userId = userId;
    }
}
