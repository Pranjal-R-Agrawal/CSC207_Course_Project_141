package use_case.generate_idea.interface_adapter;

public class GenerateIdeaState {
    private String idea;
    private String businessModel = "";

//    public String getIdea() {
//        return idea;
//    }

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
    }}
