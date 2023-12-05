package use_case.view_user_info.interface_adapter;

import use_case.view_user_info.application_business_rules.ViewUserInfoOutputBoundary;
import use_case.view_user_info.application_business_rules.ViewUserInfoOutputData;
import view.ViewUserInfoView;


public class ViewUserInfoPresenter implements ViewUserInfoOutputBoundary {

    @Override
    public void prepareCollabView(ViewUserInfoOutputData user) {
        String message = "<html>Name: </html>" + user.getName() +
                "<html><br/>Field of Expertise: </html>" + user.getFieldOfExpertise() +
                "<html><br/>City: </html>" + user.getCity() +
                "<html><br/>Email: </html>" + user.getEmail() +
                "<html><br/>Phone Number: </html>" + user.getPhoneNumber();

        ViewUserInfoView viewUserInfoView = new ViewUserInfoView();

        viewUserInfoView.showUserInfo(message);
    }

    @Override
    public void prepareGeneralView(ViewUserInfoOutputData user) {
        String message = "<html>Name: </html>" + user.getName() +
                "<html><br/>Field of Expertise: </html>" + user.getFieldOfExpertise();

        ViewUserInfoView viewUserInfoView = new ViewUserInfoView();

        viewUserInfoView.showUserInfo(message);
    }

    @Override
    public void prepareFailView(String error) {
        ViewUserInfoView viewUserInfoView = new ViewUserInfoView();

        viewUserInfoView.showUserInfo(error);
    }
}
