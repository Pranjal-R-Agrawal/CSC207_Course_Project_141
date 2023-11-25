package use_case.display_comment.interface_adapter;

import use_case.display_comment.application_business_rules.DisplayCommentOutputBoundary;
import use_case.display_comment.application_business_rules.DisplayCommentOutputData;
import view.DisplayCommentsViewModel;

public class DisplayCommentPresenter implements DisplayCommentOutputBoundary {
    private final DisplayCommentsViewModel displayCommentViewModel;

    public DisplayCommentPresenter(DisplayCommentsViewModel displayCommentViewModel) {
        this.displayCommentViewModel = displayCommentViewModel;
    }

    @Override
    public void prepareFailView(String errorMessage) {
        displayCommentViewModel.getState().setErrorMessage(errorMessage);
        displayCommentViewModel.firePropertyChanged("display_comment_error");
    }

    @Override
    public void prepareSuccessView(DisplayCommentOutputData displayCommentOutputData) {
        displayCommentViewModel.getState().setComments(displayCommentOutputData.getComments());
        displayCommentViewModel.firePropertyChanged("comments_retrieved");
    }
}
