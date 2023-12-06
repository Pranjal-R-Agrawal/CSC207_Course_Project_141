package use_case.search_post.application_business_rules;

import data_access.SearchPostsByTitleDataAccessInterface;
import entity.PostSearchResultsInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Concrete implementation of SearchPostInputBoundary responsible for searching relevant posts
 * in the database and passing it to the SearchPostOutputBoundary to display it
 * @author Tanmay Shinde
 */
public class SearchPostInteractor implements SearchPostInputBoundary {
    final SearchPostsByTitleDataAccessInterface dataAccessObject;
    final SearchPostOutputBoundary presenter;

    /**
     * Initializes the SearchPostInteractor object to perform application logic
     * @param dataAccessObject object of the SearchPostsByTitleDataAccessInterface used for accessing the database
     * @param presenter Receives the output data from SearchPostInteractor to facilitate with the display of the output data
     */
    public SearchPostInteractor(SearchPostsByTitleDataAccessInterface dataAccessObject, SearchPostOutputBoundary presenter) {
        this.dataAccessObject = dataAccessObject;
        this.presenter = presenter;
    }

    /**
     * Searches for relevant posts based on their title using the search query obtained from the input data
     * @param searchPostInputData input data object for the search post use case
     */
    @Override
    public void execute(SearchPostInputData searchPostInputData) {
        if (searchPostInputData.getQuery() == null || searchPostInputData.getQuery().trim().isEmpty()) {
            presenter.prepareFailView("Search query cannot be empty");
            return;
        }

        List<PostSearchResultsInterface> results = dataAccessObject.getPostsByTitle(searchPostInputData.getQuery());

        if (results.isEmpty()) {
            presenter.prepareFailView("No results found");
        } else {
            SearchPostOutputData outputData = new SearchPostOutputData(new ArrayList<>());
            populateOutputData(outputData, results);
            presenter.prepareSuccessView(outputData);
        }
    }

    private void populateOutputData(SearchPostOutputData outputData, List<PostSearchResultsInterface> results) {
        for (PostSearchResultsInterface result : results) {
            Map<String, Object> resultData = Map.of(
                    "id", result.getId(),
                    "title", result.getTitle(),
                    "score", result.getScore()
            );
            outputData.getResults().add(resultData);
        }
    }
}
