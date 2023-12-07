package app;

import data_access.CollabRequestDataAccessInterface;
import data_access.LoginUserDataAccessInterface;
import entity.CollabRequestFactory;
import use_case.collab_request.application_business_rules.CollabRequestInputBoundary;
import use_case.collab_request.application_business_rules.CollabRequestInputData;
import use_case.collab_request.application_business_rules.CollabRequestInteractor;
import use_case.collab_request.application_business_rules.CollabRequestOutputBoundary;
import use_case.collab_request.interface_adapter.CollabRequestController;
import use_case.collab_request.interface_adapter.CollabRequestPresenter;
import use_case.login.application_business_rules.LoginInputBoundary;
import use_case.login.application_business_rules.LoginInteractor;
import use_case.login.application_business_rules.LoginOutputBoundary;
import use_case.login.interface_adapter.LoginController;
import use_case.login.interface_adapter.LoginPresenter;
import view.LoginView;
import view.LoginViewModel;
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
    public static CollabRequestController  create(ViewManagerModel viewManagerModel,CollabRequestDataAccessInterface collabRequestDataAccessObject, CollabRequestFactory collabRequestFactory) {
        CollabRequestController collabRequestController = createCollabRequestUseCase(viewManagerModel, collabRequestDataAccessObject,collabRequestFactory);
        return collabRequestController;

    }

    private static CollabRequestController createCollabRequestUseCase(ViewManagerModel viewManagerModel, CollabRequestDataAccessInterface collabRequestDataAccessObject, CollabRequestFactory collabRequestFactory) {
        CollabRequestOutputBoundary collabRequestPresenter = new CollabRequestPresenter(viewManagerModel);
        CollabRequestInputBoundary collabRequestInteractor = new CollabRequestInteractor(collabRequestDataAccessObject, collabRequestPresenter,collabRequestFactory );
        return new CollabRequestController(collabRequestInteractor);
    }
}
