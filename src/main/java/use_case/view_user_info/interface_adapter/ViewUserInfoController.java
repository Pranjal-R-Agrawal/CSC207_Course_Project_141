package use_case.view_user_info.interface_adapter;

import org.bson.types.ObjectId;
import use_case.view_user_info.application_business_rules.ViewUserInfoInputBoundary;
import use_case.view_user_info.application_business_rules.ViewUserInfoInputData;
import use_case.view_user_info.application_business_rules.ViewUserInfoInteractor;

public class ViewUserInfoController {

    final ViewUserInfoInputBoundary viewUserInfoInteractor;

    public ViewUserInfoController(ViewUserInfoInputBoundary viewUserInfoInteractor) {
        this.viewUserInfoInteractor = viewUserInfoInteractor;
    }

    public void execute(ObjectId userId, boolean isCollaborator) {
        ViewUserInfoInputData viewUserInfoInputData = new ViewUserInfoInputData(userId, isCollaborator);
        viewUserInfoInteractor.execute(viewUserInfoInputData);
    }
}
