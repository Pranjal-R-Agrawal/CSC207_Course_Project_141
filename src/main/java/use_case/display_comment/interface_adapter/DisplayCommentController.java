package use_case.display_comment.interface_adapter;

import org.bson.types.ObjectId;
import use_case.display_comment.application_business_rules.DisplayCommentInputBoundary;

public class DisplayCommentController {
    final DisplayCommentInputBoundary displayCommentUseCaseInteractor;

    public DisplayCommentController(DisplayCommentInputBoundary displayCommentUseCaseInteractor) {
        this.displayCommentUseCaseInteractor = displayCommentUseCaseInteractor;
    }

    public void execute(ObjectId parentPostId) {
        displayCommentUseCaseInteractor.execute(parentPostId);
    }
}
