package use_case.create_comment.interface_adapter;

import org.bson.types.ObjectId;
import use_case.create_comment.application_business_rules.CreateCommentOutputBoundary;
import view.CreateCommentViewModel;
import view.DisplayCommentsViewModel;

public class CreateCommentPresenter implements CreateCommentOutputBoundary {
    private final DisplayCommentsViewModel displayCommentsViewModel;
    private final CreateCommentViewModel createCommentViewModel;

    public CreateCommentPresenter(DisplayCommentsViewModel displayCommentsViewModel, CreateCommentViewModel createCommentViewModel){
        this.displayCommentsViewModel = displayCommentsViewModel;
        this.createCommentViewModel = createCommentViewModel;
    }

    @Override
    public void prepareSuccessView(ObjectId commentId) {
        createCommentViewModel.setState(new CreateCommentState());
        displayCommentsViewModel.getState().setCommentId(commentId);
        displayCommentsViewModel.firePropertyChanged("display_single_comment");
    }

    @Override
    public void prepareFailureView(String error) {
        createCommentViewModel.getState().setErrorMessage(error);
        createCommentViewModel.firePropertyChanged("comment_error");
    }
}
