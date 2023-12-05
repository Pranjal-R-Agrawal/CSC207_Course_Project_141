package use_case.view_user_info.application_business_rules;

import org.bson.types.ObjectId;

/*
 * Input Data class that represents the data passed to the use case interactor
 * @author Tanmay Shinde
 */
public class ViewUserInfoInputData {
    final private ObjectId userId;

    final private boolean isCollaborator;

    /*
     * Initializes an Input Data object and provides functionality to its components
     * @param userId User Id of the user
     * @param isCollaborator boolean value denoting whether the logged-in user is a collaborator of the user whose info needs to be presented
     */
    public ViewUserInfoInputData(ObjectId userId, boolean isCollaborator) {
        this.userId = userId;
        this.isCollaborator = isCollaborator;
    }

    /*
     * Getter for userId
     * @return a ObjectId object denoting the userId of the user whose info needs to be presented
     */
    public ObjectId getUserId() {
        return userId;
    }

    /*
     * Getter for isCollaborator
     * @return a boolean value denoting whether the logged-in user is a collaborator of the user whose info needs to be presented
     */
    public boolean getIsCollaborator() {
        return isCollaborator;
    }
}
