package use_case.create_post.application_business_rules;

/**
 * Interface responsible for creating a post
 * @author Yathusan Koneswararajah
 */
public interface CreatePostInputBoundary {
    /**
     * Executes the logic for creating a post and placing into the database
     * @param createPostInputData
     */
    void execute(CreatePostInputData createPostInputData);
}
