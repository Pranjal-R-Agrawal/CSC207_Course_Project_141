package use_case.comment.interface_adapter;

import use_case.comment.application_business_rules.CommentOutputBoundary;
import view.CommentViewModel;
import view.ViewManagerModel;

import javax.swing.text.View;

public class CommentPresenter implements CommentOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final CommentViewModel commentViewModel;
    // TODO: Add PostViewModel instance variable
    public CommentPresenter(ViewManagerModel viewManagerModel, CommentViewModel commentViewModel){
        this.viewManagerModel = viewManagerModel;
        this.commentViewModel = commentViewModel;
    }

    @Override
    public void prepareSuccessView() {
        commentViewModel.setState(new CommentState());
        // TODO: Switch to PostView and clear all fields

    }

    @Override
    public void prepareFailureView(String error) {
        commentViewModel.getState().setErrorMessage(error);
        commentViewModel.firePropertyChanged("comment_error");
    }
}
