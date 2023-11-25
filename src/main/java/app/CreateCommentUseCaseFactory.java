package app;

import data_access.CreateCommentDataAccessInterface;
import entity.CommentFactory;
import use_case.create_comment.application_business_rules.CreateCommentInputBoundary;
import use_case.create_comment.application_business_rules.CreateCommentInteractor;
import use_case.create_comment.application_business_rules.CreateCommentOutputBoundary;
import use_case.create_comment.interface_adapter.CreateCommentController;
import use_case.create_comment.interface_adapter.CreateCommentPresenter;
import view.CreateCommentView;
import view.CreateCommentViewModel;
import view.DisplayCommentsViewModel;

public class CreateCommentUseCaseFactory {

    private CreateCommentUseCaseFactory() {}

    public static CreateCommentView create(DisplayCommentsViewModel displayCommentsViewModel, CreateCommentViewModel createCommentViewModel,
                                           CreateCommentDataAccessInterface createCommentDataAccessObject){
        CreateCommentController createCommentController = createCreateCommentUseCase(displayCommentsViewModel,
                createCommentViewModel, createCommentDataAccessObject);
        return new CreateCommentView(createCommentViewModel, createCommentController);
    }

    public static CreateCommentController createCreateCommentUseCase(DisplayCommentsViewModel displayCommentsViewModel,
                                                                     CreateCommentViewModel createCommentViewModel,
                                                                     CreateCommentDataAccessInterface createCommentDataAccessObject ){
        CreateCommentOutputBoundary createCommentPresenter = new CreateCommentPresenter(displayCommentsViewModel, createCommentViewModel);
        CreateCommentInputBoundary createCommentInteractor = new CreateCommentInteractor(createCommentPresenter, createCommentDataAccessObject, new CommentFactory());
        return new CreateCommentController(createCommentInteractor);
    }
}
