package use_case.search_post.application_business_rules;

/**
 * Interface responsible for core application logic
 * @author Tanmay Shinde
 */
public interface SearchPostInputBoundary {

    /**
     * Executes the application logic.
     * @param searchPostInputData input data object for the search post use case
     */
    void execute(SearchPostInputData searchPostInputData);
}
