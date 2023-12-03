package app;

import data_access.CreatePostDataAccessInterface;
import entity.PostFactory;
import use_case.create_post.application_business_rules.CreatePostInputBoundary;
import use_case.create_post.application_business_rules.CreatePostInteractor;
import use_case.create_post.application_business_rules.CreatePostOutputBoundary;
import use_case.create_post.interface_adapter.CreatePostController;
import use_case.create_post.interface_adapter.CreatePostPresenter;
import view.CreatePostView;
import view.CreatePostViewModel;
import view.ViewManagerModel;

public class CreatePostUseCaseFactory {
    public static CreatePostView create(ViewManagerModel viewManagerModel, CreatePostViewModel createPostViewModel, CreatePostDataAccessInterface createPostDataAccessObject){
        CreatePostController createPostController = createCreatePostUseCase(viewManagerModel, createPostViewModel, createPostDataAccessObject);
        return new CreatePostView(createPostViewModel, viewManagerModel, createPostController);
    }
    private static CreatePostController createCreatePostUseCase(ViewManagerModel viewManagerModel, CreatePostViewModel createPostViewModel, CreatePostDataAccessInterface createPostDataAccessObject){
        CreatePostOutputBoundary createPostPresenter = new CreatePostPresenter(createPostViewModel, viewManagerModel);
        CreatePostInputBoundary createPostInteractor = new CreatePostInteractor(createPostPresenter, createPostDataAccessObject, new PostFactory());
        return new CreatePostController(createPostInteractor);
    }
}
