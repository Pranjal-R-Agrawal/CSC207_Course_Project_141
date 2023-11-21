package use_case.comment.application_business_rules;

public interface CreateCommentOutputBoundary {
    void prepareSuccessView();
    void prepareFailureView(String error);
}
