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
import view.ViewManagerModel;

public class CreateCommentUseCaseFactory {

    private CreateCommentUseCaseFactory() {}

    public static CreateCommentView create(ViewManagerModel viewManagerModel, CreateCommentViewModel createCommentViewModel,
                                           CreateCommentDataAccessInterface createCommentDataAccessObject){
        // TODO: Add in PostViewModel when implemented since its needed for a Presenter Method
        CreateCommentController createCommentController = createCreateCommentUseCase(viewManagerModel,
                createCommentViewModel, createCommentDataAccessObject);
        return new CreateCommentView(createCommentViewModel, createCommentController);
    }

    public static CreateCommentController createCreateCommentUseCase(ViewManagerModel viewManagerModel,
                                                                     CreateCommentViewModel createCommentViewModel,
                                                                     CreateCommentDataAccessInterface createCommentDataAccessObject ){
        // TODO: Add in PostViewModel when implemented since its needed for a Presenter Method
        CreateCommentOutputBoundary createCommentPresenter = new CreateCommentPresenter(viewManagerModel, createCommentViewModel);
        CreateCommentInputBoundary createCommentInteractor = new CreateCommentInteractor(createCommentPresenter, createCommentDataAccessObject, new CommentFactory());
        return new CreateCommentController(createCommentInteractor);
    }
}
