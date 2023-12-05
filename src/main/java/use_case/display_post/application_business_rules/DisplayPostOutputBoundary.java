package use_case.display_post.application_business_rules;

public interface DisplayPostOutputBoundary {
    void prepareFailView(String errorMessage);
    void prepareSuccessView(DisplayPostOutputData displayPostOutputData);
}
