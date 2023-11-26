package use_case.display_comment.interface_adapter;

import org.bson.types.ObjectId;
import use_case.display_comment.application_business_rules.DisplayCommentInputBoundary;
import use_case.display_comment.application_business_rules.DisplayCommentInputData;

public class DisplayCommentController {
    final DisplayCommentInputBoundary displayCommentUseCaseInteractor;

    public DisplayCommentController(DisplayCommentInputBoundary displayCommentUseCaseInteractor) {
        this.displayCommentUseCaseInteractor = displayCommentUseCaseInteractor;
    }

    public void execute(ObjectId parentPostId, String config) {
        if (config.equals("comment")) {
            displayCommentUseCaseInteractor.execute(new DisplayCommentInputData(parentPostId, 0));
        } else if (config.equals("post")) {
            displayCommentUseCaseInteractor.execute(new DisplayCommentInputData(parentPostId, 1));
        }
    }
}
