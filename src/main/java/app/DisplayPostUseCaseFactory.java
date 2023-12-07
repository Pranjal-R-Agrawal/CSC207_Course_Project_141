package app;

import data_access.CollabRequestDataAccessInterface;
import data_access.DisplayCommentDataAccessInterface;
import use_case.collab_request.interface_adapter.CollabRequestController;
import use_case.display_post.application_business_rules.DisplayPostInteractor;
import use_case.display_post.application_business_rules.DisplayPostOutputBoundary;
import use_case.display_post.interface_adapter.DisplayPostController;
import use_case.display_post.interface_adapter.DisplayPostPresenter;
import use_case.view_user_info.interface_adapter.ViewUserInfoController;
import view.ViewManagerModel;
import view.PostAndCommentsView;
import view.PostAndCommentsViewModel;

public class DisplayPostUseCaseFactory {
    private DisplayPostUseCaseFactory() {}

    /**
     * Creates and returns a PostAndCommentsView object
     * @param displayCommentViewModel the display comment view model
     * @param displayCommentDataAccessObject the display comment data access object
     * @param createCommentUseCaseBuilder  the create comment use case builder
     * @return a PostAndCommentsView object
     */
    public static PostAndCommentsView create(PostAndCommentsViewModel displayCommentViewModel, ViewManagerModel viewManagerModel, DisplayCommentDataAccessInterface displayCommentDataAccessObject, CreateCommentUseCaseBuilder createCommentUseCaseBuilder, ViewUserInfoController viewUserInfoController, CollabRequestController collabRequestController, CollabRequestDataAccessInterface collabRequestDataAccessObject) {
        DisplayPostController displayPostController = createDisplayCommentUseCase(displayCommentViewModel, displayCommentDataAccessObject);
        return new PostAndCommentsView(displayCommentViewModel, viewManagerModel, displayPostController, createCommentUseCaseBuilder, viewUserInfoController, collabRequestController);
    }

    private static DisplayPostController createDisplayCommentUseCase(PostAndCommentsViewModel displayCommentViewModel, DisplayCommentDataAccessInterface displayCommentDataAccessObject) {
        DisplayPostOutputBoundary displayCommentPresenter = new DisplayPostPresenter(displayCommentViewModel);
        DisplayPostInteractor displayPostInteractor = new DisplayPostInteractor(displayCommentDataAccessObject, displayCommentPresenter);
        return new DisplayPostController(displayPostInteractor);
    }
}
