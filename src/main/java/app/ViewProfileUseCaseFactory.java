package app;

import data_access.ViewProfileDataAccessInterface;
import use_case.view_profile.application_business_rules.*;
import use_case.view_profile.interface_adapter.ViewProfileController;
import use_case.view_profile.interface_adapter.ViewProfilePresenter;
import view.ViewProfileView;
import view.ViewProfileViewModel;
import view.ViewManagerModel;

import javax.swing.*;
public class ViewProfileUseCaseFactory {

    private ViewProfileUseCaseFactory() {}

    public static ViewProfileView create(ViewManagerModel viewManagerModel, ViewProfileViewModel viewProfileViewModel, ViewProfileDataAccessInterface viewProfileDataAccessObject) {
        ViewProfileController viewProfileController = createViewProfileUseCase(viewManagerModel, viewProfileViewModel, viewProfileDataAccessObject);
        return new ViewProfileView(viewProfileViewModel, viewProfileController);
    }

    private static ViewProfileController createViewProfileUseCase(ViewManagerModel viewManagerModel, ViewProfileViewModel viewProfileViewModel, ViewProfileDataAccessInterface viewProfileDataAccessObject) {
        ViewProfileOutputBoundary viewProfilePresenter = new ViewProfilePresenter(viewManagerModel, viewProfileViewModel);
        ViewProfileInputBoundary viewProfileInteractor = new ViewProfileInteractor(viewProfileDataAccessObject, viewProfilePresenter);
        return new ViewProfileController(viewProfileInteractor);
    }
}
