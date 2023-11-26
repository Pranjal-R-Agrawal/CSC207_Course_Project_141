package app;

import data_access.DisplayCommentDataAccessInterface;
import use_case.display_post.application_business_rules.DisplayPostInteractor;
import use_case.display_post.application_business_rules.DisplayPostOutputBoundary;
import use_case.display_post.interface_adapter.DisplayPostController;
import use_case.display_post.interface_adapter.DisplayPostPresenter;
import view.PostAndCommentsView;
import view.PostAndCommentsViewModel;

public class DisplayPostUseCaseFactory {
    private DisplayPostUseCaseFactory() {}

    public static PostAndCommentsView create(PostAndCommentsViewModel displayCommentViewModel, DisplayCommentDataAccessInterface displayCommentDataAccessObject, CreateCommentUseCaseFactory createCommentUseCaseFactory) {
        DisplayPostController displayPostController = createDisplayCommentUseCase(displayCommentViewModel, displayCommentDataAccessObject);
        return new PostAndCommentsView(displayCommentViewModel, displayPostController, createCommentUseCaseFactory);
    }

    private static DisplayPostController createDisplayCommentUseCase(PostAndCommentsViewModel displayCommentViewModel, DisplayCommentDataAccessInterface displayCommentDataAccessObject) {
        DisplayPostOutputBoundary displayCommentPresenter = new DisplayPostPresenter(displayCommentViewModel);
        DisplayPostInteractor displayPostInteractor = new DisplayPostInteractor(displayCommentDataAccessObject, displayCommentPresenter);
        return new DisplayPostController(displayPostInteractor);
    }
}
