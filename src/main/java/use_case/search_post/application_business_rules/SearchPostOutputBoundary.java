package use_case.search_post.application_business_rules;

/**
 * Responsible for facilitating display of output data.
 * @author Tanmay Shinde
 */
public interface SearchPostOutputBoundary {

    /**
     * Directs components of the Interface Adapters to display the output data when search query is empty or null
     * @param errorMessage - Output data (the Idea object) to be displayed
     */
    void prepareFailView(String errorMessage);

    /**
     * Directs components of the Interface Adapters to display the output data on successful search of posts
     * @param searchPostOutputData - Output data to be displayed
     */
    void prepareSuccessView(SearchPostOutputData searchPostOutputData);
}
