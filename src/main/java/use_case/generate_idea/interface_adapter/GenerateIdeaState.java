package use_case.generate_idea.interface_adapter;

/**
 * Stores the state of the GenerateIdeaViewModel
 * @author Sidharth Sawhney
 */
public class GenerateIdeaState {
    private String idea;
    private String businessModel = "";

    /**
     * Sets the idea prompt
     * @param idea the idea prompt
     * @return Returns updated state containing updated prompt
     */
    public GenerateIdeaState setIdea(String idea) {
        this.idea = idea;
        return this;
    }

    /**
     * @return Returns the current state's business model
     */
    public String getBusinessModel() {
        return businessModel;
    }

    /**
     * Sets the business model of the idea
     * @param businessModel the business model of the idea.
     * @return Returns updated state containing updated business model
     */
    public GenerateIdeaState setBusinessModel(String businessModel) {
        this.businessModel= businessModel;
        return this;
    }}
