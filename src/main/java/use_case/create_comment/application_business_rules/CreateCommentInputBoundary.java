package use_case.create_comment.application_business_rules;

/**
 * Interface responsible for creating a comment
 * @author Yathusan Koneswararajah
 */
public interface CreateCommentInputBoundary {

    /**
     * Executes the logic for creating a comment and placing into the database
     * @param createCommentInputData Bundled data for comment
     */
    void execute(CreateCommentInputData createCommentInputData);
}
