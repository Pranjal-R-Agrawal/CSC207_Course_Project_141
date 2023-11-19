package use_case.generate_idea.application_business_rules;

public class GenerateIdeaOutputData {
    private final String idea;
    private String business_model = ""; // Remains an empty string if API call fails, therefore avoids the possibility of a NullPointerException

    public GenerateIdeaOutputData(String idea, String business_model) {
        this.idea = idea;
        this.business_model = business_model;
    }

    public String getIdea() {
        return idea;
    }

    public String getBusinessModel() {
        return business_model;
    }
}
