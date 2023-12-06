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

    /**
     * Constructor for CollabRequestPresenter
     * @param viewManagerModel
     */
    public CollabRequestPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }
    /**
     * Prepares the success view
     */
    @Override
    public void prepareSuccessView() {
        String message = "Collab request sent!";
        JOptionPane.showMessageDialog(null, message);
    }
}
