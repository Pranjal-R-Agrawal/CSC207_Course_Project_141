package use_case.collab_request.interface_adapter;

import use_case.collab_request.application_business_rules.CollabRequestOutputBoundary;
import view.PostAndCommentsViewModel;
import view.ViewManagerModel;

import javax.swing.*;

public class CollabRequestPresenter implements CollabRequestOutputBoundary {
    private final ViewManagerModel viewManagerModel;

    // TODO: Add the Forum View or wherever the comments are being displayed since this use case wouldn't necesarily have it's own view
    public CollabRequestPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareSuccessView() {
        String message = "Collab request sent!";
        JOptionPane.showMessageDialog(null, message);
    }
}
