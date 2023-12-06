package use_case.create_comment.interface_adapter;

import org.bson.types.ObjectId;
import use_case.create_comment.application_business_rules.CreateCommentInputBoundary;
import use_case.create_comment.application_business_rules.CreateCommentInputData;

/**
 * Routes the inputs from the view to the application logic for the use case to run
 * @author Yathusan Koneswararajah
 */
public class CreateCommentController {
    final CreateCommentInputBoundary commentInteractor;

    /**
     * Initializes the controller which contains the InputBoundary to execute
     * @param commentInteractor InputBoundary which runs the application logic
     */
    public CreateCommentController(CreateCommentInputBoundary commentInteractor){
        this.commentInteractor = commentInteractor;
    }

    /**
     * Passes the control to the InputBoundary to run the application logic
     * @param parentId ID of parent comment
     * @param body  Input for the body of the post
     * @param qualifications Input for qualifications
     */
    public void execute(ObjectId parentId, ObjectId parentPostId, String body, String qualifications){
        CreateCommentInputData inputData = new CreateCommentInputData(parentId, parentPostId, body, qualifications);
        commentInteractor.execute(inputData);
    }
}
