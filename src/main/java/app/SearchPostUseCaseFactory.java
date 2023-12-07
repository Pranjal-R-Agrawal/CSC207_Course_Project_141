package app;

import data_access.SearchPostsByTitleDataAccessInterface;
import use_case.search_post.application_business_rules.SearchPostInputBoundary;
import use_case.search_post.application_business_rules.SearchPostInteractor;
import use_case.search_post.application_business_rules.SearchPostOutputBoundary;
import use_case.search_post.interface_adapter.SearchPostController;
import use_case.search_post.interface_adapter.SearchPostPresenter;
import use_case.search_post.interface_adapter.SearchPostViewModel;
import view.*;

/**
 * Static Factory class for assembling the required components for the Search Post use case
 * @author Tanmay Shinde
 */
public class SearchPostUseCaseFactory {
    /**
     * Creates the view for the search post use case
     * @param viewManagerModel Manages which view is displayed
     * @param searchPostViewModel Observable that stores the state useful to the SearchPostView
     * @param homePageViewModel Observable that stores the state useful to Home Page View
     * @param searchPostDataAccessObject Data access object for interacting with the database
     * @param createPostView the UI responsible for allowing the user to format a post
     * @return a SearchPostView object configured using passed in arguments
     */
    public static SearchPostView create(ViewManagerModel viewManagerModel, SearchPostViewModel searchPostViewModel, HomePageViewModel homePageViewModel, SearchPostsByTitleDataAccessInterface searchPostDataAccessObject, CreatePostView createPostView) {
        SearchPostController searchPostController = createSearchPostUseCase(viewManagerModel, searchPostViewModel, searchPostDataAccessObject);
        return new SearchPostView(viewManagerModel, searchPostViewModel, homePageViewModel, searchPostController, createPostView);
    }
    private static SearchPostController createSearchPostUseCase(ViewManagerModel viewManagerModel, SearchPostViewModel searchPostViewModel, SearchPostsByTitleDataAccessInterface searchPostDataAccessObject) {
        SearchPostOutputBoundary searchPostPresenter = new SearchPostPresenter(searchPostViewModel);
        SearchPostInputBoundary searchPostInteractor = new SearchPostInteractor(searchPostDataAccessObject, searchPostPresenter);
        return new SearchPostController(searchPostInteractor);
    }
}
