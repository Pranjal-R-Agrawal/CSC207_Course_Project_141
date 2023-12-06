package use_case.view_user_info.interface_adapter;

import use_case.view_user_info.application_business_rules.ViewUserInfoOutputBoundary;
import use_case.view_user_info.application_business_rules.ViewUserInfoOutputData;
import view.ViewUserInfoViewModel;

/**
 * Concrete implementation of the ViewUserInfoOutputBoundary.
 * The Presenter invokes the showUserInfo method of the ViewUserInfoView Class and passes the appropriate data
 * This presenter does not update the active view and has no property or state changes as the info is only displayed through a pop-up dialog box
 * @author Tanmay Shinde
 */
public class ViewUserInfoPresenter implements ViewUserInfoOutputBoundary {

    private final ViewUserInfoViewModel viewUserInfoViewModel;

    public ViewUserInfoPresenter(ViewUserInfoViewModel viewUserInfoViewModel) {
        this.viewUserInfoViewModel = viewUserInfoViewModel;
    }

    /**
     * Constructs the relevant output data for the case when logged-in user is a collaborator and updates the View Model accordingly
     * @param user OutputData object containing relevant user data that is to be displayed
     */
    @Override
    public void prepareCollabView(ViewUserInfoOutputData user) {
        String message = "<html>Name: </html>" + user.getName() +
                "<html><br/>Field of Expertise: </html>" + user.getFieldOfExpertise() +
                "<html><br/>City: </html>" + user.getCity() +
                "<html><br/>Email: </html>" + user.getEmail() +
                "<html><br/>Phone Number: </html>" + user.getPhoneNumber();

        viewUserInfoViewModel.getState().setMessage(message);
        viewUserInfoViewModel.firePropertyChanged("message");
    }

    /**
     * Constructs the relevant output data for the case when logged-in user is not a collaborator and updates the View Model accordingly
     * @param user OutputData object containing relevant user data that is to be displayed
     */
    @Override
    public void prepareGeneralView(ViewUserInfoOutputData user) {
        String message = "<html>Name: </html>" + user.getName() +
                "<html><br/>Field of Expertise: </html>" + user.getFieldOfExpertise();

        viewUserInfoViewModel.getState().setMessage(message);
        viewUserInfoViewModel.firePropertyChanged("message");
    }

    /**
     * Updates the View Model with an error message
     * @param error the error message conveying that the user could not be retrieved
     */
    @Override
    public void prepareFailView(String error) {
        viewUserInfoViewModel.getState().setMessage(error);
        viewUserInfoViewModel.firePropertyChanged("message");
    }
}
