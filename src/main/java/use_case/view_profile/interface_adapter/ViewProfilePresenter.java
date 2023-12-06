package use_case.view_profile.interface_adapter;

import use_case.view_profile.application_business_rules.ViewProfileOutputBoundary;
import use_case.view_profile.application_business_rules.ViewProfileOutputData;

import view.ViewProfileViewModel;
import view.ViewManagerModel;
/**
 * Concrete implementation of the ViewProfileOutputBoundary.
 * Presenter updates state of the ViewProfileViewModel and triggers the ViewProfileView to observe and display updated ViewProfileViewModel.
 * @author Anbuselvan Ragunathan
 */
public class ViewProfilePresenter implements ViewProfileOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final ViewProfileViewModel viewProfileViewModel;

    /**
     * Initializes the Presenter configured with the manager model to display appropriate screen and View Model to update its state
     * @param viewManagerModel Manages which view is displayed
     * @param viewProfileViewModel This stores the state that is useful to the ViewProfileView.
     */
    public ViewProfilePresenter(ViewManagerModel viewManagerModel, ViewProfileViewModel viewProfileViewModel) {
        this.viewProfileViewModel = viewProfileViewModel;

        this.viewManagerModel = viewManagerModel;
    }
    /**
     * Updates state of the ViewProfileViewModel and instructs it to inform the ViewProfileView. Switches the display screen to ViewProfileView.
     * @param viewProfileOutputData - Output data (the ViewProfileOutputData object) to be displayed
     */
    @Override
    public void prepareSuccessView(ViewProfileOutputData viewProfileOutputData) {
        viewProfileViewModel.setState(viewProfileViewModel.getState().setUsername(viewProfileOutputData.getUsername()).setName(viewProfileOutputData.getName()).setEmail(viewProfileOutputData.getEmail()));
        viewProfileViewModel.firePropertyChanged("display_profile");
        viewManagerModel.setActiveView(viewProfileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }



}
