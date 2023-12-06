package use_case.search_post.interface_adapter;

import java.util.List;
import java.util.Map;

/**
 * Stores the state of the SearchPostViewModel
 * @author Tanmay Shinde
 */
public class SearchPostState {
    private String searchQuery;
    private String errorMessage;
    private List<Map<String, Object>> results;

    /**
     * @return Returns the search query corresponding to the current state of the ViewModel
     */
    public String getSearchQuery() {
        return searchQuery;
    }

    /**
     * Setter method for searchQuery attribute
     * @param searchQuery new searchQuery string that is to be assigned to the States searchQuery attribute
     * @return Returns the updated SearchPostState object
     */
    public SearchPostState setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
        return this;
    }

    /**
     * @return Returns the error message corresponding to the current state of the ViewModel
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Setter method for errorMessage attribute
     * @param errorMessage new errorMessage string that is to be assigned to the States searchQuery attribute
     * @return Returns the updated SearchPostState object
     */
    public SearchPostState setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    /**
     * @return Returns the search results corresponding to the current state of the ViewModel
     */
    public List<Map<String, Object>> getResults() {
        return results;
    }

    /**
     * Setter method for results attribute
     * @param results new results list that is to be assigned to the States searchQuery attribute
     * @return Returns the updated SearchPostState object
     */
    public SearchPostState setResults(List<Map<String, Object>> results) {
        this.results = results;
        return this;
    }
}
