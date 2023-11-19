package use_case.view_profile.application_business_rules;

import data_access.ViewProfileDataAccessInterface;
import java.util.ArrayList;
import entity.User;
import entity.Post; // To get the needed Posts for collab requests?

// TODO: Add the collab requests properly to the view profile use case
public class ViewProfileInteractor implements ViewProfileInputBoundary {

    final ViewProfileDataAccessInterface viewProfileDataAccessObject;

    final ViewProfileOutputBoundary viewProfilePresenter;

    public ViewProfileInteractor(ViewProfileDataAccessInterface viewProfileDataAccessObject, ViewProfileOutputBoundary viewProfilePresenter) {
        this.viewProfileDataAccessObject = viewProfileDataAccessObject;
        this.viewProfilePresenter = viewProfilePresenter;
    }

    public void execute(ViewProfileInputData viewProfileInputData) {
        // TODO: Maybe change the rating to a string for displaying purposes?
        if (viewProfileDataAccessObject.getLoggedInUser() != null) {
            String username = viewProfileDataAccessObject.getLoggedInUser().getUsername();
            String name = viewProfileDataAccessObject.getLoggedInUser().getName();
            String email = viewProfileDataAccessObject.getLoggedInUser().getEmail();
            double rating = viewProfileDataAccessObject.getLoggedInUser().getRating();

            ViewProfileOutputData viewProfileOutputData = new ViewProfileOutputData(username, name, email, rating);
            viewProfilePresenter.prepareSuccessView(viewProfileOutputData);

        }

        else {
            viewProfilePresenter.prepareFailView("User not found");
        }



    }
}
