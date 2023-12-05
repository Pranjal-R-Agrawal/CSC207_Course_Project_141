package use_case.display_post.interface_adapter;

import org.bson.types.ObjectId;
import use_case.display_post.application_business_rules.DisplayPostInputBoundary;
import use_case.display_post.application_business_rules.DisplayPostInputData;

public class DisplayPostController {
    final DisplayPostInputBoundary displayCommentUseCaseInteractor;

    public DisplayPostController(DisplayPostInputBoundary displayCommentUseCaseInteractor) {
        this.displayCommentUseCaseInteractor = displayCommentUseCaseInteractor;
    }

    public void execute(ObjectId parentPostId, String config) {
        if (config.equals("comment")) {
            displayCommentUseCaseInteractor.execute(new DisplayPostInputData(parentPostId, 0));
        } else if (config.equals("post")) {
            displayCommentUseCaseInteractor.execute(new DisplayPostInputData(parentPostId, 1));
        }
    }
}
