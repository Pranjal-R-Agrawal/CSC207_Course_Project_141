package app;

import data_access.DisplayCommentDataAccessInterface;
import use_case.display_post.application_business_rules.DisplayPostInteractor;
import use_case.display_post.application_business_rules.DisplayPostOutputBoundary;
import use_case.display_post.interface_adapter.DisplayPostController;
import use_case.display_post.interface_adapter.DisplayPostPresenter;
import view.ViewManagerModel;
import view.display_post.PostAndCommentsView;
import view.display_post.PostAndCommentsViewModel;

public class DisplayPostUseCaseFactory {
    private DisplayPostUseCaseFactory() {}

    public static PostAndCommentsView create(PostAndCommentsViewModel displayCommentViewModel, ViewManagerModel viewManagerModel, DisplayCommentDataAccessInterface displayCommentDataAccessObject, CreateCommentUseCaseBuilder createCommentUseCaseBuilder) {
        DisplayPostController displayPostController = createDisplayCommentUseCase(displayCommentViewModel, displayCommentDataAccessObject);
        return new PostAndCommentsView(displayCommentViewModel, viewManagerModel, displayPostController, createCommentUseCaseBuilder);
    }

    private static DisplayPostController createDisplayCommentUseCase(PostAndCommentsViewModel displayCommentViewModel, DisplayCommentDataAccessInterface displayCommentDataAccessObject) {
        DisplayPostOutputBoundary displayCommentPresenter = new DisplayPostPresenter(displayCommentViewModel);
        DisplayPostInteractor displayPostInteractor = new DisplayPostInteractor(displayCommentDataAccessObject, displayCommentPresenter);
        return new DisplayPostController(displayPostInteractor);
    }
}
