package use_case.view_user_info.application_business_rules;

import org.bson.types.ObjectId;

public class ViewUserInfoInputData {
    final private ObjectId userId;

    final private boolean isCollaborator;

    public ViewUserInfoInputData(ObjectId userId, boolean isCollaborator) {
        this.userId = userId;
        this.isCollaborator = isCollaborator;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public boolean getIsCollaborator() {
        return isCollaborator;
    }
}
