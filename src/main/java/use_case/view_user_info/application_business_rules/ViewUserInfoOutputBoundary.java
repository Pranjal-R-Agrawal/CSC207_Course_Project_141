package use_case.view_user_info.application_business_rules;

/**
 * Responsible for facilitating display of output data.
 * @author Tanmay Shinde
 */
public interface ViewUserInfoOutputBoundary {

    /**
     * Displays the view that should be displayed if the logged-in user is a collaborator of the user whose data needs to be displayed
     * @param user OutputData object cantaining relevant user data that is to  be displayed
     */
    void prepareCollabView(ViewUserInfoOutputData user);

    /**
     * Displays the view that should be displayed if the logged-in user is not a collaborator of the user whose data needs to be displayed
     * @param user OutputData object cantaining relevant user data that is to  be displayed
     */
    void prepareGeneralView(ViewUserInfoOutputData user);

    /**
     * Displays the view that should be displayed if the user could not be found in the database
     * @param error the error message conveying that the user could not be retreived
     */
    void prepareFailView(String error);
}
