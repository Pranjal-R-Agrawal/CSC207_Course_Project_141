package use_case.view_profile.application_business_rules;

import data_access.ViewProfileDataAccessInterface;
import java.util.ArrayList;
import java.util.List;

import entity.User;
import entity.Post; // To get the needed Posts for collab requests?
import org.bson.types.ObjectId;

/**
 * Concrete implementation of ViweProfileInputBoundary responsible for structuring the things that will be displayed in the view and passing it to the output boundary
 * @author Anbuselvan Raguunathan
 * */
public class ViewProfileInteractor implements ViewProfileInputBoundary {

    final ViewProfileDataAccessInterface viewProfileDataAccessObject;

    final ViewProfileOutputBoundary viewProfilePresenter;

    /**
     * Initializes the ViewProfileInteractor object to perform the view profile logic
     * @param viewProfileDataAccessObject Helps us interact appropriately with the Database
     * @param viewProfilePresenter Receives the output data from ViewProfileInteractor to facilitate with the display of the output data
     */
    public ViewProfileInteractor(ViewProfileDataAccessInterface viewProfileDataAccessObject, ViewProfileOutputBoundary viewProfilePresenter) {
        this.viewProfileDataAccessObject = viewProfileDataAccessObject;
        this.viewProfilePresenter = viewProfilePresenter;
    }
    @Override
    public void execute() {

        /**
         * Structures the things that will need to be displayed in the view
         */
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
