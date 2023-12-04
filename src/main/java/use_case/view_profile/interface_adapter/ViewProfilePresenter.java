package use_case.view_profile.interface_adapter;

import use_case.view_profile.application_business_rules.ViewProfileOutputBoundary;
import use_case.view_profile.application_business_rules.ViewProfileOutputData;

import view.ViewProfileViewModel;
import view.ViewManagerModel;

public class ViewProfilePresenter implements ViewProfileOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private ViewProfileViewModel viewProfileViewModel;

    public ViewProfilePresenter(ViewManagerModel viewManagerModel, ViewProfileViewModel viewProfileViewModel) {
        this.viewProfileViewModel = viewProfileViewModel;

        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ViewProfileOutputData viewProfileOutputData) {
        viewProfileViewModel.setState(viewProfileViewModel.getState().setUsername(viewProfileOutputData.getUsername()).setName(viewProfileOutputData.getName()).setEmail(viewProfileOutputData.getEmail()).setRating(viewProfileOutputData.getRating()));
        viewProfileViewModel.firePropertyChanged("display_profile");
        viewManagerModel.setActiveView(viewProfileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }



}
