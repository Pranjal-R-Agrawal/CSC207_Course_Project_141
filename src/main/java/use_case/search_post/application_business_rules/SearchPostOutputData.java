package use_case.search_post.application_business_rules;

import java.util.List;
import java.util.Map;

/**
 * Packages the output data containing all search results
 * @author Tanmay Shinde
 */
public class SearchPostOutputData {
    private List<Map<String, Object>> results;

    /**
     * Initializes the output data object
     * @param results List of Mappings of post title to Post objects which store the search results
     */
    public SearchPostOutputData(List<Map<String, Object>> results) {
        this.results = results;
    }

    /**
     * @return Returns the results attribute of SearchPostOutputData
     */
    public List<Map<String, Object>> getResults() {
        return results;
    }

    /**
     * Setter for the results attribute of the SearchPostOutputData object
     * @param results the list of mappings to be assigned to the result attribute of the given SearchPostOutputData object
     * @return the SearchPostOutputData object of which the result attribute was updated
     */
    public SearchPostOutputData setResults(List<Map<String, Object>> results) {
        this.results = results;
        return this;
    }
}
