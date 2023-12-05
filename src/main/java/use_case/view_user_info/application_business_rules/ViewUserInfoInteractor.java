package use_case.view_user_info.application_business_rules;

import data_access.ViewUserInfoDataAccessInterface;
import entity.User;
import org.bson.types.ObjectId;

/**
 * Concrete implementation of ViewUserInfoInputBoundary responsible for implementing the logic to display information of a user
 * @author Tanmay Shinde
 * */
public class ViewUserInfoInteractor implements ViewUserInfoInputBoundary{
    final ViewUserInfoDataAccessInterface infoDataAccessObject;
    final ViewUserInfoOutputBoundary infoPresenter;

    /**
     * Initializes the ViewUserInfoInteractor Object
     * @param infoDataAccessObject the data access interface implementation to be used for accessing data from the database
     * @param infoPresenter Receives the output data from VieUserInfoInteractor to facilitate with the display of the relevant user data
     */
    public ViewUserInfoInteractor(ViewUserInfoDataAccessInterface infoDataAccessObject, ViewUserInfoOutputBoundary infoPresenter) {
        this.infoDataAccessObject = infoDataAccessObject;
        this.infoPresenter = infoPresenter;
    }

    /**
     * Obtains user data from the database and passes the relevant data to the Presenter if the user exists
     * @param viewUserInfoInputData the input data object containing the user's userId
     */
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
