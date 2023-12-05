package use_case.create_post.interface_adapter;

import org.bson.types.ObjectId;
import use_case.create_post.application_business_rules.CreatePostOutputBoundary;
import view.CreatePostViewModel;
import view.ViewManagerModel;

/**
 * Concrete implementation of the CreatePostOutputBoundary which updates the CreatePostViewModel
 * and triggers the CreatePostView to display these changes
 * @author Yathusan Koneswararajah
 */
public class CreatePostPresenter implements CreatePostOutputBoundary {
    CreatePostViewModel createPostViewModel;
    ViewManagerModel viewManagerModel;

    /**
     * Initializes the Presenter with the ViewManagerModel and ViewModel
     * @param createPostViewModel Observable that stores the state for CreatePost use case
     * @param viewManagerModel Manages the views that are displalyed
     */
    public CreatePostPresenter(CreatePostViewModel createPostViewModel, ViewManagerModel viewManagerModel){
        this.createPostViewModel = createPostViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Resets the View and state of this use case,
     * closes the window of this view and displays the created post
     * @param postId ID of the post that was created
     */
    @Override
    public void prepareSuccessView(ObjectId postId) {
        createPostViewModel.setState(new CreatePostState());
        createPostViewModel.firePropertyChanged("reset_input_fields");
        viewManagerModel.closeCreatePost();
        viewManagerModel.displayPost(postId);
    }

    /**
     * Displays the error message
     * @param error Error message to be displayed
     */
    @Override
    public void prepareFailureView(String error) {
        createPostViewModel.getState().setErrorMessage(error);
        createPostViewModel.firePropertyChanged("post_error");
    }
}
