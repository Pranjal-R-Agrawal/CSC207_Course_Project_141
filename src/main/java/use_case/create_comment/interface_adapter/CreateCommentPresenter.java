package use_case.create_comment.interface_adapter;

import org.bson.types.ObjectId;
import use_case.create_comment.application_business_rules.CreateCommentOutputBoundary;
import view.CreateCommentViewModel;
import view.PostAndCommentsViewModel;

/**
 * Concrete implementation of the CreateCommentOutputBoundary which updates the CreateCommentViewModel
 * and triggers the CreateCommentView to display these changes
 * @author Yathusan Koneswararajah
 */
public class CreateCommentPresenter implements CreateCommentOutputBoundary {
    private final PostAndCommentsViewModel postAndCommentsViewModel;
    private final CreateCommentViewModel createCommentViewModel;

    /**
     * Initializes the Presenter with the PostAndCommentViewModel and ViewModel
     * @param createCommentViewModel Observable that stores the state for CreateComment use case
     * @param postAndCommentsViewModel Observable that stores state of post and comment being displayed
     */
    public CreateCommentPresenter(PostAndCommentsViewModel postAndCommentsViewModel, CreateCommentViewModel createCommentViewModel){
        this.postAndCommentsViewModel = postAndCommentsViewModel;
        this.createCommentViewModel = createCommentViewModel;
    }

    /**
     * Closes the current window for creating comment and displays the created comment
     * @param commentId ID of the post that was created
     */
    @Override
    public void prepareSuccessView(ObjectId commentId) {
        createCommentViewModel.setState(new CreateCommentState());
        postAndCommentsViewModel.getState().setCommentId(commentId);
        postAndCommentsViewModel.firePropertyChanged("display_single_comment");
        postAndCommentsViewModel.firePropertyChanged("close_reply_frame");
    }

    /**
     * Displays an error
     * @param error Error message to be displayed
     */
    @Override
    public void prepareFailureView(String error) {
        createCommentViewModel.getState().setErrorMessage(error);
        createCommentViewModel.firePropertyChanged("comment_error");
    }
}
