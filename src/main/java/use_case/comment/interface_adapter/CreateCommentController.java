package use_case.comment.interface_adapter;

import org.bson.types.ObjectId;
import use_case.comment.application_business_rules.CreateCommentInputBoundary;
import use_case.comment.application_business_rules.CreateCommentInputData;

public class CreateCommentController {
    final CreateCommentInputBoundary commentInteractor;

    public CreateCommentController(CreateCommentInputBoundary commentInteractor){
        this.commentInteractor = commentInteractor;
    }

    public void execute(ObjectId parentId, ObjectId parentPostId, String body, String qualifications){
        CreateCommentInputData inputData = new CreateCommentInputData(parentId, parentPostId, body, qualifications);
        commentInteractor.execute(inputData);
    }
}
