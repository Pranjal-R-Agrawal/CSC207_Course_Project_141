package app;

import data_access.ViewProfileDataAccessInterface;
import use_case.view_profile.application_business_rules.ViewProfileInputBoundary;
import use_case.view_profile.application_business_rules.ViewProfileInteractor;
import use_case.view_profile.application_business_rules.ViewProfileOutputBoundary;
import use_case.view_profile.interface_adapter.ViewProfileController;
import use_case.view_profile.interface_adapter.ViewProfileDialogViewModel;
import use_case.view_profile.interface_adapter.ViewProfilePresenter;
import view.*;


public class ViewProfileDialogUseCaseFactory {
    private ViewProfileDialogUseCaseFactory() {
    }

    public static ViewProfileDialogView createView(ViewProfileDialogViewModel viewProfileDialogViewModel) {
        return new ViewProfileDialogView(viewProfileDialogViewModel);
    }

    public static ViewProfileController createController(ViewManagerModel viewManagerModel, ViewProfileDialogViewModel viewProfileDialogViewModel, ViewProfileDataAccessInterface viewProfileDataAccessObject) {
        ViewProfileOutputBoundary viewProfilePresenter = new ViewProfilePresenter(viewManagerModel, viewProfileDialogViewModel);
        ViewProfileInputBoundary viewProfileInteractor = new ViewProfileInteractor(viewProfileDataAccessObject, viewProfilePresenter);
        return new ViewProfileController(viewProfileInteractor);

    }
}
