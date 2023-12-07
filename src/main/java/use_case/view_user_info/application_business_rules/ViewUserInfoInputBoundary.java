package use_case.view_user_info.application_business_rules;

/**
 * Interface responsible for implementation of view user info use case
 * @author Tanmay Shinde
 */
public interface ViewUserInfoInputBoundary {

    /**
     * Executes the logic for displaying information of the required user
     * @param viewUserInfoInputData the input data object containing the user's userId
     */
    void execute(ViewUserInfoInputData viewUserInfoInputData);
}
