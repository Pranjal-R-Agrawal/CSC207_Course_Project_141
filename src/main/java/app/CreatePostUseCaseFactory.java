package app;

import data_access.CreatePostDataAccessInterface;
import entity.ConcretePostFactory;
import use_case.create_post.application_business_rules.CreatePostInputBoundary;
import use_case.create_post.application_business_rules.CreatePostInteractor;
import use_case.create_post.application_business_rules.CreatePostOutputBoundary;
import use_case.create_post.interface_adapter.CreatePostController;
import use_case.create_post.interface_adapter.CreatePostPresenter;
import view.CreatePostView;
import use_case.create_post.interface_adapter.CreatePostViewModel;
import view.ViewManagerModel;

/**
 * Static factory for assembling components for the CreatePost use case
 * @author Yathusan Koneswararajah
 */
public class CreatePostUseCaseFactory {
    /**
     * Creates the view for the CreatePost use case
     * @param viewManagerModel Manages the views to be displayed
     * @param createPostViewModel Observable that contains what the CreatePostView will observe
     * @param createPostDataAccessObject Object that interacts with database
     * @return The view for the CreatePost use case
     */
    public static CreatePostView create(ViewManagerModel viewManagerModel, CreatePostViewModel createPostViewModel, CreatePostDataAccessInterface createPostDataAccessObject){
        CreatePostController createPostController = createCreatePostUseCase(viewManagerModel, createPostViewModel, createPostDataAccessObject);
        return new CreatePostView(createPostViewModel, viewManagerModel, createPostController);
    }
    private static CreatePostController createCreatePostUseCase(ViewManagerModel viewManagerModel, CreatePostViewModel createPostViewModel, CreatePostDataAccessInterface createPostDataAccessObject){
        CreatePostOutputBoundary createPostPresenter = new CreatePostPresenter(createPostViewModel, viewManagerModel);
        CreatePostInputBoundary createPostInteractor = new CreatePostInteractor(createPostPresenter, createPostDataAccessObject, new ConcretePostFactory());
        return new CreatePostController(createPostInteractor);
    }
}
