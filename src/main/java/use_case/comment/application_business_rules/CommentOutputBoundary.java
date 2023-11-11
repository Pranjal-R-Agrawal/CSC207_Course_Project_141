package use_case.comment.application_business_rules;

public interface CommentOutputBoundary {
    void prepareSuccessView();
    void prepareFailureView(String error);
}
