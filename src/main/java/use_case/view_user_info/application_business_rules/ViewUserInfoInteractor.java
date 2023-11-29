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
            // get rating
            // get contact info

            // create output data object

            infoPresenter.prepareCollabView(new ViewUserInfoOutputData(viewUserInfoInputData.getUserId()));
        } if (!viewUserInfoInputData.getIsCollaborator()) {
            // get name
            // get rating

            // create output data object

            infoPresenter.prepareGeneralView(new ViewUserInfoOutputData(viewUserInfoInputData.getUserId()));
        } else {
            infoPresenter.prepareFailView("Failed to retreive User");
        }
    }
}
