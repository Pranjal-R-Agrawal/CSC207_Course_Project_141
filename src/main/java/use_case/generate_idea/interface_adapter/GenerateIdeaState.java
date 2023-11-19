package use_case.generate_idea.interface_adapter;

public class GenerateIdeaState {
    private String idea;
    private String businessModel;
    private String errorMessage;

    public String getIdea() {
        return idea;
    }

    public GenerateIdeaState setIdea(String idea) {
        this.idea = idea;
        return this;
    }

    public String getBusinessModel() {
        return businessModel;
    }

    public GenerateIdeaState setBusinessModel(String businessModel) {
        this.businessModel= businessModel;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public GenerateIdeaState setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}
