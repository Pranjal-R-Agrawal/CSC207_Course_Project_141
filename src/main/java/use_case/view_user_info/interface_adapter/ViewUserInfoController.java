package use_case.view_user_info.interface_adapter;

import org.bson.types.ObjectId;
import use_case.view_user_info.application_business_rules.ViewUserInfoInputBoundary;
import use_case.view_user_info.application_business_rules.ViewUserInfoInputData;
import use_case.view_user_info.application_business_rules.ViewUserInfoInteractor;

/**
 * Routes the control to application logic to perform the use case
 * @author Tanmay Shinde
 */
public class ViewUserInfoController {

    final ViewUserInfoInputBoundary viewUserInfoInteractor;

    /**
     * Initializes the controller configured with the ViewUserInfoInputBoundary to route to.
     * @param viewUserInfoInteractor the input boundary object
     */
    public ViewUserInfoController(ViewUserInfoInputBoundary viewUserInfoInteractor) {
        this.viewUserInfoInteractor = viewUserInfoInteractor;
    }

    /**
     * Passes the control to the ViewUserInfoInputBoundary to perform use case logic
     * @param userId userId of the user whose info is to be displayed
     * @param isCollaborator boolean value denoting whether the logged-in user is a collaborator of the user whose info is to be displayed
     */
    public void execute(ObjectId userId, boolean isCollaborator) {
        ViewUserInfoInputData viewUserInfoInputData = new ViewUserInfoInputData(userId, isCollaborator);
        viewUserInfoInteractor.execute(viewUserInfoInputData);
    }
}
