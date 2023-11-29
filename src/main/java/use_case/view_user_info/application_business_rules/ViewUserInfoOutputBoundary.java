package use_case.view_user_info.application_business_rules;

public interface ViewUserInfoOutputBoundary {
    void prepareCollabView(ViewUserInfoOutputData user);

    void prepareGeneralView(ViewUserInfoOutputData user);

    void prepareFailView(String error);
}
