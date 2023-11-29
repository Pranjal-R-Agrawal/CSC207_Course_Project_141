package use_case.view_user_info.application_business_rules;

import data_access.ViewUserInfoDataAccessInterface;
import entity.User;

public class ViewUserInfoInteractor implements ViewUserInfoInputBoundary{
    final ViewUserInfoDataAccessInterface infoDataAccessObject;
    final ViewUserInfoOutputBoundary infoPresenter;

    public ViewUserInfoInteractor(ViewUserInfoDataAccessInterface infoDataAccessObject, ViewUserInfoOutputBoundary infoPresenter) {
        this.infoDataAccessObject = infoDataAccessObject;
        this.infoPresenter = infoPresenter;
    }

    public void execute(ViewUserInfoInputData viewUserInfoInputData) {
        if (viewUserInfoInputData.getIsCollaborator()) {
            // get name
            String username = infoDataAccessObject.getUsernameByUserId(viewUserInfoInputData.getUserId());
            // get rating
            double rating = infoDataAccessObject.getUserRating(viewUserInfoInputData.getUserId());
            // get contact info
            String email = infoDataAccessObject.getUserEmail(viewUserInfoInputData.getUserId());
            String phoneNumber = infoDataAccessObject.getPhoneNumber(viewUserInfoInputData.getUserId());

            // create output data object
            ViewUserInfoOutputData userData = new ViewUserInfoOutputData(username, rating, email, phoneNumber);
            infoPresenter.prepareCollabView(userData);
        }

        if (!viewUserInfoInputData.getIsCollaborator()) {
            // get name
            String username = infoDataAccessObject.getUsernameByUserId(viewUserInfoInputData.getUserId());
            // get rating
            double rating = infoDataAccessObject.getUserRating(viewUserInfoInputData.getUserId());

            // create output data object
            ViewUserInfoOutputData userData = new ViewUserInfoOutputData(username, rating, "", "");

            infoPresenter.prepareGeneralView(userData);
        } else {
            infoPresenter.prepareFailView("Failed to retreive User");
        }
    }
}
