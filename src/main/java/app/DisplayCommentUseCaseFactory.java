package app;

import data_access.DisplayCommentDataAccessInterface;
import use_case.display_comment.application_business_rules.DisplayCommentInteractor;
import use_case.display_comment.application_business_rules.DisplayCommentOutputBoundary;
import use_case.display_comment.interface_adapter.DisplayCommentController;
import use_case.display_comment.interface_adapter.DisplayCommentPresenter;
import view.DisplayCommentsView;
import view.DisplayCommentsViewModel;

public class DisplayCommentUseCaseFactory {
    private DisplayCommentUseCaseFactory() {}

    public static DisplayCommentsView create(DisplayCommentsViewModel displayCommentViewModel, DisplayCommentDataAccessInterface displayCommentDataAccessObject, CreateCommentUseCaseFactory createCommentUseCaseFactory) {
        DisplayCommentController displayCommentController = createDisplayCommentUseCase(displayCommentViewModel, displayCommentDataAccessObject);
        return new DisplayCommentsView(displayCommentViewModel, displayCommentController, createCommentUseCaseFactory);
    }

    private static DisplayCommentController createDisplayCommentUseCase(DisplayCommentsViewModel displayCommentViewModel, DisplayCommentDataAccessInterface displayCommentDataAccessObject) {
        DisplayCommentOutputBoundary displayCommentPresenter = new DisplayCommentPresenter(displayCommentViewModel);
        DisplayCommentInteractor displayCommentInteractor = new DisplayCommentInteractor(displayCommentDataAccessObject, displayCommentPresenter);
        return new DisplayCommentController(displayCommentInteractor);
    }
}
