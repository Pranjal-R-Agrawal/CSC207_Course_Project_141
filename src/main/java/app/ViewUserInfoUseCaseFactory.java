package app;

import data_access.ViewUserInfoDataAccessInterface;
import use_case.view_user_info.application_business_rules.ViewUserInfoInputBoundary;
import use_case.view_user_info.application_business_rules.ViewUserInfoInteractor;
import use_case.view_user_info.application_business_rules.ViewUserInfoOutputBoundary;
import use_case.view_user_info.interface_adapter.ViewUserInfoController;
import use_case.view_user_info.interface_adapter.ViewUserInfoPresenter;
import view.ViewUserInfoView;
import use_case.view_user_info.interface_adapter.ViewUserInfoViewModel;

/**
 * Static factory for assembling components for the ViewUserInfo use case
 * @author Tanmay Shinde
 */
public class ViewUserInfoUseCaseFactory {
    /**
     * Creates a View for ViewUserInfo use case
     * @param viewUserInfoViewModel View Model for ViewUserInfo use case
     * @return the View for ViewUserInfo use case
     */
    public static ViewUserInfoView create(ViewUserInfoViewModel viewUserInfoViewModel) {
        return new ViewUserInfoView(viewUserInfoViewModel);
    }

    /**
     * Creates a Controller for ViewUserInfo use case
     * @param viewUserInfoViewModel View Model for ViewUserInfo use case
     * @param viewUserInfoDataAccessObject data access object for ViewUserInfo use case
     * @return the Controller for ViewUserInfo use case
     */
    public static ViewUserInfoController create(ViewUserInfoViewModel viewUserInfoViewModel, ViewUserInfoDataAccessInterface viewUserInfoDataAccessObject) {
        ViewUserInfoOutputBoundary presenter = new ViewUserInfoPresenter(viewUserInfoViewModel);
        ViewUserInfoInputBoundary interactor = new ViewUserInfoInteractor(viewUserInfoDataAccessObject, presenter);
        return new ViewUserInfoController(interactor);
    }
}
