package use_case.comment.interface_adapter;

import use_case.comment.application_business_rules.CreateCommentOutputBoundary;
import view.CreateCommentViewModel;
import view.ViewManagerModel;

public class CreateCommentPresenter implements CreateCommentOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final CreateCommentViewModel createCommentViewModel;
    // TODO: Add PostViewModel instance variable
    public CreateCommentPresenter(ViewManagerModel viewManagerModel, CreateCommentViewModel createCommentViewModel){
        this.viewManagerModel = viewManagerModel;
        this.createCommentViewModel = createCommentViewModel;
    }

    @Override
    public void prepareSuccessView() {
        createCommentViewModel.setState(new CreateCommentState());
        // TODO: Switch to PostView and clear all fields

    }

    @Override
    public void prepareFailureView(String error) {
        createCommentViewModel.getState().setErrorMessage(error);
        createCommentViewModel.firePropertyChanged("comment_error");
    }
}
