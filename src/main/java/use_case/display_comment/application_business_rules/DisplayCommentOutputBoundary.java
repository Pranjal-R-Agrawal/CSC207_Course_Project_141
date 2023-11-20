package use_case.display_comment.application_business_rules;

public interface DisplayCommentOutputBoundary {
    void prepareFailView(String errorMessage);
    void prepareSuccessView(DisplayCommentOutputData displayCommentOutputData);
}
