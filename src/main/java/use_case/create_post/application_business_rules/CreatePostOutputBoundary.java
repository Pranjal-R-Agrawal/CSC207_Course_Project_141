package use_case.create_post.application_business_rules;

import org.bson.types.ObjectId;

/**
 * Responsible for facilitating the display of errors or the post that is created
 * @author Yathusan Koneswararajah
 */
public interface CreatePostOutputBoundary {
    /**
     * Closes the current window for creating post and displays the created post
     * @param postId ID of the post that was created
     */
    void prepareSuccessView(ObjectId postId);

    /**
     * Displays an error
     * @param error Error message to be displayed
     */
    void prepareFailureView(String error);
}
