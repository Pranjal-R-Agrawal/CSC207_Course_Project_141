package use_case.search_post.interface_adapter;

import use_case.search_post.application_business_rules.SearchPostInputBoundary;
import use_case.search_post.application_business_rules.SearchPostInputData;

/**
 * Routes the control to application logic to perform the use case
 * @author Tanmay Shinde
 */
public class SearchPostController {
    final SearchPostInputBoundary searchPostUseCaseInteractor;

    /**
     * Initializes the controller configured with the SearchPostInputBoundary to route to.
     * @param searchPostUseCaseInteractor
     */
    public SearchPostController(SearchPostInputBoundary searchPostUseCaseInteractor) {
        this.searchPostUseCaseInteractor = searchPostUseCaseInteractor;
    }

    /**
     * Passes the control to the SearchPostInputBoundary to perform application logic
     * @param keyword the keyword to look for in post titles
     */
    public void execute(String keyword) {
        searchPostUseCaseInteractor.execute(new SearchPostInputData(keyword));
    }
}
