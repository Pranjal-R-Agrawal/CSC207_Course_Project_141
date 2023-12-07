package app;

import data_access.CollabRequestDataAccessInterface;
import entity.CollabRequestFactory;
import use_case.collab_request.application_business_rules.CollabRequestInputBoundary;
import use_case.collab_request.application_business_rules.CollabRequestInteractor;
import use_case.collab_request.application_business_rules.CollabRequestOutputBoundary;
import use_case.collab_request.interface_adapter.CollabRequestController;
import use_case.collab_request.interface_adapter.CollabRequestPresenter;
import view.PostAndCommentsViewModel;
import view.ViewManagerModel;

/**
 * This class is responsible for creating a CollabRequestController object.
 */
public class CollabRequestUseCaseFactory {
    /**
     * Creates and returns a CollabRequestController object.
     * @param viewManagerModel the view manager model
     * @param collabRequestDataAccessObject the collab request data access object
     * @param collabRequestFactory the collab request factory
     * @return a CollabRequestController object
     */
    public static CollabRequestController  create(ViewManagerModel viewManagerModel,CollabRequestDataAccessInterface collabRequestDataAccessObject, CollabRequestFactory collabRequestFactory, PostAndCommentsViewModel postAndCommentsViewModel) {
        CollabRequestController collabRequestController = createCollabRequestUseCase(viewManagerModel, collabRequestDataAccessObject,collabRequestFactory, postAndCommentsViewModel);
        return collabRequestController;

    }

    private static CollabRequestController createCollabRequestUseCase(ViewManagerModel viewManagerModel, CollabRequestDataAccessInterface collabRequestDataAccessObject, CollabRequestFactory collabRequestFactory, PostAndCommentsViewModel postAndCommentsViewModel) {
        CollabRequestOutputBoundary collabRequestPresenter = new CollabRequestPresenter(viewManagerModel, postAndCommentsViewModel);
        CollabRequestInputBoundary collabRequestInteractor = new CollabRequestInteractor(collabRequestDataAccessObject, collabRequestPresenter,collabRequestFactory );
        return new CollabRequestController(collabRequestInteractor);
    }
}
