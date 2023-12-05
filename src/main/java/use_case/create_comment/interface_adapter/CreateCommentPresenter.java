package use_case.create_comment.interface_adapter;

import org.bson.types.ObjectId;
import use_case.create_comment.application_business_rules.CreateCommentOutputBoundary;
import view.CreateCommentViewModel;
import view.PostAndCommentsViewModel;

public class CreateCommentPresenter implements CreateCommentOutputBoundary {
    private final PostAndCommentsViewModel postAndCommentsViewModel;
    private final CreateCommentViewModel createCommentViewModel;

    public CreateCommentPresenter(PostAndCommentsViewModel postAndCommentsViewModel, CreateCommentViewModel createCommentViewModel){
        this.postAndCommentsViewModel = postAndCommentsViewModel;
        this.createCommentViewModel = createCommentViewModel;
    }

    @Override
    public void prepareSuccessView(ObjectId commentId) {
        createCommentViewModel.setState(new CreateCommentState());
        postAndCommentsViewModel.getState().setCommentId(commentId);
        postAndCommentsViewModel.firePropertyChanged("display_single_comment");
        postAndCommentsViewModel.firePropertyChanged("close_reply_frame");
    }

    @Override
    public void prepareFailureView(String error) {
        createCommentViewModel.getState().setErrorMessage(error);
        createCommentViewModel.firePropertyChanged("comment_error");
    }
}
