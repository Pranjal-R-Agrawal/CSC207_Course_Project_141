package use_case.create_comment.application_business_rules;

import org.bson.types.ObjectId;

/**
 * Responsible for facilitating the display of errors or the coment that is created
 * @author Yathusan Koneswararajah
 */
public interface CreateCommentOutputBoundary {
    /**
     * Closes the current window for creating comment and displays the created comment
     * @param commentId ID of the post that was created
     */
    void prepareSuccessView(ObjectId commentId);
    /**
     * Displays an error
     * @param error Error message to be displayed
     */
    void prepareFailureView(String error);
}
