package use_case.create_post.interface_adapter;

import use_case.create_post.application_business_rules.CreatePostInputBoundary;
import use_case.create_post.application_business_rules.CreatePostInputData;

/**
 * Routes the inputs from the view to the application logic for the use case to run
 * @author Yathusan Koneswararajah
 */
public class CreatePostController {
    CreatePostInputBoundary createPostInteractor;

    /**
     * Initializes the controller which contains the InputBoundary to execute
     * @param createPostInteractor InputBoundary which runs the application logic
     */
    public CreatePostController(CreatePostInputBoundary createPostInteractor){
        this.createPostInteractor = createPostInteractor;
    }

    /**
     * Passes the control to the InputBoundary to run the application logic
     * @param title Input for the title of the post
     * @param body  Input for the body of the post
     * @param suggestedCollaboratorQualifications Input for the suggested qualifications
     */
    public void execute(String title, String body, String suggestedCollaboratorQualifications){
        CreatePostInputData inputData = new CreatePostInputData(title, body, suggestedCollaboratorQualifications);
        createPostInteractor.execute(inputData);
    }
}
