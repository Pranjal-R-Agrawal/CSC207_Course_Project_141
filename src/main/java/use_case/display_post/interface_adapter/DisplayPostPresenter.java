package use_case.display_post.interface_adapter;

import use_case.display_post.application_business_rules.DisplayPostOutputBoundary;
import use_case.display_post.application_business_rules.DisplayPostOutputData;
import view.PostAndCommentsViewModel;

public class DisplayPostPresenter implements DisplayPostOutputBoundary {
    private final PostAndCommentsViewModel displayCommentViewModel;

    public DisplayPostPresenter(PostAndCommentsViewModel displayCommentViewModel) {
        this.displayCommentViewModel = displayCommentViewModel;
    }

    /**
     * Sets the appropriate config value and calls the use case interactor
     */
    @Override
    public void prepareFailView(String errorMessage) {
        displayCommentViewModel.getState().setErrorMessage(errorMessage);
        displayCommentViewModel.firePropertyChanged("display_error");
    }

    /**
     * Stores the comment and post data in the view model
     */
    @Override
    public void prepareSuccessView(DisplayPostOutputData displayPostOutputData) {
        displayCommentViewModel.getState().setComments(displayPostOutputData.getComments());
        displayCommentViewModel.getState().setPost(displayPostOutputData.getPost());
        displayCommentViewModel.firePropertyChanged("retrieved");
    }
}
