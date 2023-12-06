package use_case.view_profile.application_business_rules;

import data_access.ViewProfileDataAccessInterface;
import java.util.ArrayList;
import java.util.List;

import entity.User;
import entity.Post; // To get the needed Posts for collab requests?
import org.bson.types.ObjectId;

// TODO: Add the collab requests properly to the view profile use case
public class ViewProfileInteractor implements ViewProfileInputBoundary {

    final ViewProfileDataAccessInterface viewProfileDataAccessObject;

    final ViewProfileOutputBoundary viewProfilePresenter;

    public ViewProfileInteractor(ViewProfileDataAccessInterface viewProfileDataAccessObject, ViewProfileOutputBoundary viewProfilePresenter) {
        this.viewProfileDataAccessObject = viewProfileDataAccessObject;
        this.viewProfilePresenter = viewProfilePresenter;
    }

    public void execute() {
        // TODO: Maybe change the rating to a string for displaying purposes?

        String username = viewProfileDataAccessObject.getLoggedInUser().getUsername();
        String name = viewProfileDataAccessObject.getLoggedInUser().getName();
        String email = viewProfileDataAccessObject.getLoggedInUser().getEmail();

        String projects = "";
        List<ObjectId> projectIds = viewProfileDataAccessObject.getLoggedInUser().getPostIDs();
        for (ObjectId postId : projectIds) {
            String post = viewProfileDataAccessObject.getPostByPostID(postId).getTitle();
            projects += post + " ";
        }
        List<ObjectId> collabRequestIds = viewProfileDataAccessObject.getLoggedInUser().getCollaborationRequestIDs();
        ArrayList<String> collabRequests = new ArrayList<>();
        for (ObjectId collabRequestId : collabRequestIds) {
            String collabRequest = viewProfileDataAccessObject.getCollabRequestById(collabRequestId).getTitle();

            collabRequests.add(collabRequest);
        }

        ViewProfileOutputData viewProfileOutputData = new ViewProfileOutputData(username, name, email, projects,
                collabRequests);
        viewProfilePresenter.prepareSuccessView(viewProfileOutputData);

        }

}
