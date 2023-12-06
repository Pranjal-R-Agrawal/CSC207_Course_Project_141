package use_case.search_post.application_business_rules;

/**
 * The class packaging the input data for the search post use case
 * @author Tanmay Shinde
 */
public class SearchPostInputData {
    private final String query;

    /**
     * Initializes the attributes of the input data object
     * @param keyword the keyword that needs to be searched for in post titles
     */
    public SearchPostInputData(String keyword) {
        this.query = keyword;
    }

    /**
     * @return Returns the query attribute of the SearchPostInputData object
     */
    public String getQuery() {
        return query;
    }
}
