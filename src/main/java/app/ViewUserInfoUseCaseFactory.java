package app;

import data_access.ViewUserInfoDataAccessInterface;
import use_case.view_user_info.application_business_rules.ViewUserInfoInputBoundary;
import use_case.view_user_info.application_business_rules.ViewUserInfoInteractor;
import use_case.view_user_info.application_business_rules.ViewUserInfoOutputBoundary;
import use_case.view_user_info.application_business_rules.ViewUserInfoOutputData;
import use_case.view_user_info.interface_adapter.ViewUserInfoController;
import use_case.view_user_info.interface_adapter.ViewUserInfoPresenter;
import view.ViewUserInfoView;
import view.ViewUserInfoViewModel;


public class ViewUserInfoUseCaseFactory {
    public static ViewUserInfoView create(ViewUserInfoViewModel viewUserInfoViewModel) {
        return new ViewUserInfoView(viewUserInfoViewModel);
    }

    public static ViewUserInfoController create(ViewUserInfoViewModel viewUserInfoViewModel, ViewUserInfoDataAccessInterface viewUserInfoDataAccessObject) {
        ViewUserInfoOutputBoundary presenter = new ViewUserInfoPresenter(viewUserInfoViewModel);
        ViewUserInfoInputBoundary interactor = new ViewUserInfoInteractor(viewUserInfoDataAccessObject, presenter);
        return new ViewUserInfoController(interactor);
    }
}
