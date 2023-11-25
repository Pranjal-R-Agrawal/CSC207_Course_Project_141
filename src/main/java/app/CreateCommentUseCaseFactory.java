package app;

import data_access.CreateCommentDataAccessInterface;
import entity.CommentFactory;
import org.bson.types.ObjectId;
import use_case.create_comment.application_business_rules.CreateCommentInputBoundary;
import use_case.create_comment.application_business_rules.CreateCommentInteractor;
import use_case.create_comment.application_business_rules.CreateCommentOutputBoundary;
import use_case.create_comment.interface_adapter.CreateCommentController;
import use_case.create_comment.interface_adapter.CreateCommentPresenter;
import view.CreateCommentView;
import view.CreateCommentViewModel;
import view.DisplayCommentsViewModel;

public class CreateCommentUseCaseFactory {
    private DisplayCommentsViewModel displayCommentsViewModel;
    private CreateCommentDataAccessInterface createCommentDataAccessObject;

    private CreateCommentUseCaseFactory() {}

    public CreateCommentUseCaseFactory(DisplayCommentsViewModel displayCommentsViewModel, CreateCommentDataAccessInterface createCommentDataAccessObject) {
        this.displayCommentsViewModel = displayCommentsViewModel;
        this.createCommentDataAccessObject = createCommentDataAccessObject;
    }

    public CreateCommentView create(ObjectId parentPostId, ObjectId parentID) {
        CreateCommentViewModel createCommentViewModel = new CreateCommentViewModel();
        createCommentViewModel.getState().setParentPostId(parentPostId).setParentId(parentID);
        return CreateCommentUseCaseFactory.create(displayCommentsViewModel, createCommentViewModel, createCommentDataAccessObject);
    }

    public static CreateCommentView create(DisplayCommentsViewModel displayCommentsViewModel, CreateCommentViewModel createCommentViewModel,
                                           CreateCommentDataAccessInterface createCommentDataAccessObject){
        CreateCommentController createCommentController = createCreateCommentUseCase(displayCommentsViewModel,
                createCommentViewModel, createCommentDataAccessObject);
        return new CreateCommentView(createCommentViewModel, displayCommentsViewModel, createCommentController);
    }

    public static CreateCommentController createCreateCommentUseCase(DisplayCommentsViewModel displayCommentsViewModel,
                                                                     CreateCommentViewModel createCommentViewModel,
                                                                     CreateCommentDataAccessInterface createCommentDataAccessObject ){
        CreateCommentOutputBoundary createCommentPresenter = new CreateCommentPresenter(displayCommentsViewModel, createCommentViewModel);
        CreateCommentInputBoundary createCommentInteractor = new CreateCommentInteractor(createCommentPresenter, createCommentDataAccessObject, new CommentFactory());
        return new CreateCommentController(createCommentInteractor);
    }
}
