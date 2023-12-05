package use_case.generate_idea.application_business_rules;

/**
 * Bundles the Idea object's data.
 * @author Sidharth Sawhney
 */
public class GenerateIdeaOutputData {
    private final String idea;
    private String business_model = ""; // Remains an empty string if API call fails, therefore avoids the possibility of a NullPointerException

    /**
     * Initializes a bundle storing the Idea's prompt and business model
     * @param idea the idea prompt
     * @param business_model the idea's business model
     */
    public GenerateIdeaOutputData(String idea, String business_model) {
        this.idea = idea;
        this.business_model = business_model;
    }

    /**
     * @return Returns the idea prompt
     */
    public String getIdea() {
        return idea;
    }

    /**
     * @return Returns the idea's business model
     */
    public String getBusinessModel() {
        return business_model;
    }
}
