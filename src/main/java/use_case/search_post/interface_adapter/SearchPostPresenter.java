package use_case.search_post.interface_adapter;

import use_case.search_post.application_business_rules.SearchPostOutputBoundary;
import use_case.search_post.application_business_rules.SearchPostOutputData;
import view.SearchPostViewModel;

/**
 * Concrete implementation of the SearchPostOutputBoundary.
 * Presenter updates state of the SearchPostViewModel and triggers te SearchPostView to observe and display updated SearchPostViewModel.
 * @author Tanmay Shinde
 */
public class SearchPostPresenter implements SearchPostOutputBoundary {
    private final SearchPostViewModel searchPostViewModel;

    /**
     * Initializes the Presenter configured with the SearchPostViewModel
     * @param searchPostViewModel Observable that stores the state useful to the SearchPostView.
     */
    public SearchPostPresenter(SearchPostViewModel searchPostViewModel) {
        this.searchPostViewModel = searchPostViewModel;
    }

    /**
     * Prepares the view to be displayed in case a search error occurs by updating the
     * ViewModel's state and firing a property change
     * @param errorMessage - the relevant error message to be displayed
     */
    @Override
    public void prepareFailView(String errorMessage) {
        searchPostViewModel.getState().setErrorMessage(errorMessage);
        searchPostViewModel.firePropertyChanged("search_error");
    }

    /**
     * The view to be displayed in the case the search is successful and the results are obtained
     * by updating the ViewModel's state and firing a property change
     * @param searchPostOutputData - Output data to be displayed
     */
    @Override
    public void prepareSuccessView(SearchPostOutputData searchPostOutputData) {
        searchPostViewModel.getState().setResults(searchPostOutputData.getResults());
        searchPostViewModel.firePropertyChanged("retrieved");
    }
}
