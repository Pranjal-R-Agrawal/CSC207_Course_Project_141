package app;

import data_access.ViewProfileDataAccessInterface;
import use_case.view_profile.application_business_rules.*;
import use_case.view_profile.interface_adapter.ViewProfileController;
import use_case.view_profile.interface_adapter.ViewProfilePresenter;
import view.ViewProfileView;
import view.ViewProfileViewModel;
import view.ViewManagerModel;

import javax.swing.*;
/**
 * Static Factory class for assembling the required components for the View Profile Use Case
 * @author Anbuselvan Ragunathan
 */
public class ViewProfileUseCaseFactory {
    /** Prevents instantiation */
    private ViewProfileUseCaseFactory() {}

    public static ViewProfileView create(ViewManagerModel viewManagerModel, ViewProfileViewModel viewProfileViewModel, ViewProfileDataAccessInterface viewProfileDataAccessObject) {
        ViewProfileController viewProfileController = createViewProfileUseCase(viewManagerModel, viewProfileViewModel, viewProfileDataAccessObject);
        return new ViewProfileView(viewProfileViewModel, viewProfileController, viewManagerModel,viewProfileDataAccessObject);
    }
    /**
     * Creates the View for the View Profile Use Case
     * @param viewManagerModel Manages which view is displayed
     * @param viewProfileViewModel Observable that stores the state useful to the View Profile View.
     * @param viewProfileDataAccessObject Helps us interact appropriately with the Database
     * @return the View for the View Profile Use Case
     */
    private static ViewProfileController createViewProfileUseCase(ViewManagerModel viewManagerModel, ViewProfileViewModel viewProfileViewModel, ViewProfileDataAccessInterface viewProfileDataAccessObject) {
        ViewProfileOutputBoundary viewProfilePresenter = new ViewProfilePresenter(viewManagerModel, viewProfileViewModel);
        ViewProfileInputBoundary viewProfileInteractor = new ViewProfileInteractor(viewProfileDataAccessObject, viewProfilePresenter);
        return new ViewProfileController(viewProfileInteractor);
    }
}
