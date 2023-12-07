package use_case.view_profile.interface_adapter;

import use_case.view_profile.application_business_rules.ViewProfileOutputBoundary;
import use_case.view_profile.application_business_rules.ViewProfileOutputData;

import view.ViewProfileDialogViewModel;
import view.ViewManagerModel;
/**
 * Concrete implementation of the ViewProfileOutputBoundary.
 * Presenter updates state of the ViewProfileViewModel and triggers the ViewProfileView to observe and display updated ViewProfileViewModel.
 * @author Anbuselvan Ragunathan
 */
public class ViewProfilePresenter implements ViewProfileOutputBoundary {
    private final ViewManagerModel viewManagerModel;

    private final ViewProfileDialogViewModel viewProfileDialogViewModel;

    /**
     * Initializes the Presenter configured with the manager model to display appropriate screen and View Model to update its state
     * @param viewManagerModel Manages which view is displayed
     * @param viewProfileDialogViewModel This stores the state that is useful to the ViewProfileDialogView.
     */
    public ViewProfilePresenter(ViewManagerModel viewManagerModel, ViewProfileDialogViewModel viewProfileDialogViewModel) {
        this.viewProfileDialogViewModel = viewProfileDialogViewModel;

        this.viewManagerModel = viewManagerModel;
    }
    /**
     * Updates state of the ViewProfileViewModel and instructs it to inform the ViewProfileView. Switches the display screen to ViewProfileView.
     * @param viewProfileOutputData - Output data (the ViewProfileOutputData object) to be displayed
     */
    @Override
    public void prepareSuccessView(ViewProfileOutputData viewProfileOutputData) {

        String message = "<html><p>Username: "+viewProfileOutputData.getUsername()+"</p><p>Name: "+viewProfileOutputData.getName()+"</p><p>Email: "+viewProfileOutputData.getEmail()+"</p><p>Projects: "+viewProfileOutputData.getProjects()+"</p></html>";

        viewProfileDialogViewModel.getState().setMessage(message);
        viewProfileDialogViewModel.firePropertyChanged("view_profile_display");
    }



}
