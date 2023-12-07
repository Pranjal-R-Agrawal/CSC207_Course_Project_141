package use_case.collab_request.interface_adapter;

import use_case.collab_request.application_business_rules.CollabRequestOutputBoundary;
import view.PostAndCommentsViewModel;
import view.ViewManagerModel;

import javax.swing.*;
/**
 * Represents a CollabRequestPresenter
 */
public class CollabRequestPresenter implements CollabRequestOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final PostAndCommentsViewModel postAndCommentsViewModel;

    /**
     * Constructor for CollabRequestPresenter
     * @param viewManagerModel
     */
    public CollabRequestPresenter(ViewManagerModel viewManagerModel, PostAndCommentsViewModel postAndCommentsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.postAndCommentsViewModel = postAndCommentsViewModel;
    }
    /**
     * Prepares the success view
     */
    @Override
    public void prepareSuccessView() {
        String message = "Made Collaborator!";
        JOptionPane.showMessageDialog(null, message);
        postAndCommentsViewModel.firePropertyChanged("reset_view");
        postAndCommentsViewModel.firePropertyChanged("display_post");
    }
}
