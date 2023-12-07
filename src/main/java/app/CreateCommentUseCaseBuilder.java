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
import use_case.create_comment.interface_adapter.CreateCommentViewModel;
import use_case.display_post.interface_adapter.PostAndCommentsViewModel;

public class CreateCommentUseCaseBuilder {
    private final PostAndCommentsViewModel postAndCommentsViewModel;
    private final CreateCommentDataAccessInterface createCommentDataAccessObject;

    public CreateCommentUseCaseBuilder(PostAndCommentsViewModel postAndCommentsViewModel, CreateCommentDataAccessInterface createCommentDataAccessObject) {
        this.postAndCommentsViewModel = postAndCommentsViewModel;
        this.createCommentDataAccessObject = createCommentDataAccessObject;
    }

    public CreateCommentView build(ObjectId parentPostId, ObjectId parentID) {
        CreateCommentViewModel createCommentViewModel = new CreateCommentViewModel();
        createCommentViewModel.getState().setParentPostId(parentPostId).setParentId(parentID);
        return CreateCommentUseCaseBuilder.create(postAndCommentsViewModel, createCommentViewModel, createCommentDataAccessObject);
    }

    public static CreateCommentView create(PostAndCommentsViewModel postAndCommentsViewModel, CreateCommentViewModel createCommentViewModel,
                                           CreateCommentDataAccessInterface createCommentDataAccessObject){
        CreateCommentController createCommentController = createCreateCommentUseCase(postAndCommentsViewModel,
                createCommentViewModel, createCommentDataAccessObject);
        return new CreateCommentView(createCommentViewModel, postAndCommentsViewModel, createCommentController);
    }

    public static CreateCommentController createCreateCommentUseCase(PostAndCommentsViewModel postAndCommentsViewModel,
                                                                     CreateCommentViewModel createCommentViewModel,
                                                                     CreateCommentDataAccessInterface createCommentDataAccessObject ){
        CreateCommentOutputBoundary createCommentPresenter = new CreateCommentPresenter(postAndCommentsViewModel, createCommentViewModel);
        CreateCommentInputBoundary createCommentInteractor = new CreateCommentInteractor(createCommentPresenter, createCommentDataAccessObject, new CommentFactory());
        return new CreateCommentController(createCommentInteractor);
    }
}
