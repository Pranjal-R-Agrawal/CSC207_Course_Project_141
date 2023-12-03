package use_case.create_post.interface_adapter;

import org.bson.types.ObjectId;
import use_case.create_comment.application_business_rules.CreateCommentOutputBoundary;
import view.CreatePostViewModel;
import view.ViewManagerModel;

public class CreatePostPresenter implements CreateCommentOutputBoundary {
    CreatePostViewModel createPostViewModel;
    ViewManagerModel viewManagerModel;
    public CreatePostPresenter(CreatePostViewModel createPostViewModel){
        this.createPostViewModel = createPostViewModel;
    }
    @Override
    public void prepareSuccessView(ObjectId postId) {
        createPostViewModel.setState(new CreatePostState());
        viewManagerModel.closeCreatePost();
        viewManagerModel.displayPost(postId);
    }

    @Override
    public void prepareFailureView(String error) {
        createPostViewModel.getState().setErrorMessage(error);
        createPostViewModel.firePropertyChanged("post_error");
    }
}
