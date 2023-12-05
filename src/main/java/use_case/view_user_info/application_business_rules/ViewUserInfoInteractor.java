package use_case.view_user_info.application_business_rules;

import data_access.ViewUserInfoDataAccessInterface;
import entity.User;
import org.bson.types.ObjectId;

public class ViewUserInfoInteractor implements ViewUserInfoInputBoundary{
    final ViewUserInfoDataAccessInterface infoDataAccessObject;
    final ViewUserInfoOutputBoundary infoPresenter;

    public ViewUserInfoInteractor(ViewUserInfoDataAccessInterface infoDataAccessObject, ViewUserInfoOutputBoundary infoPresenter) {
        this.infoDataAccessObject = infoDataAccessObject;
        this.infoPresenter = infoPresenter;
    }

    public void execute(ViewUserInfoInputData viewUserInfoInputData) {

        ObjectId userId = viewUserInfoInputData.getUserId();

        User user = infoDataAccessObject.getUserById(userId);

        if(user != null) {

            // name
            String name = user.getName();

            // field of expertise
            String fieldOfExpertise = user.getFieldOfExpertise();


            // city
            String city = user.getCity();

            // email
            String email = user.getEmail();

            // phone number
            String phoneNumber = user.getPhoneNumber();

            if (viewUserInfoInputData.getIsCollaborator()) {
                ViewUserInfoOutputData userData = new ViewUserInfoOutputData(name, fieldOfExpertise, city, email, phoneNumber);
                infoPresenter.prepareCollabView(userData);
            }

            else {
                ViewUserInfoOutputData userData = new ViewUserInfoOutputData(name, fieldOfExpertise, "", "", "");
                infoPresenter.prepareGeneralView(userData);
            }
        }
        else {
            infoPresenter.prepareFailView("Failed to retreive User");
        }
    }
}
