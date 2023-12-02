package entity;

/**
 * Concrete implementation of the Idea interface. Represents a ConcreteIdea.
 * @author Sidharth Sawhney
 */
public class ConcreteIdea implements Idea{

    private String idea;
    private String businessModel = "";


    /**
     * Initializes a Concrete Idea containing the idea prompt and no business model.
     * @param idea the idea prompt
     */
    public ConcreteIdea(String idea) {
        this.idea= idea;
    }

    /**
     * Updates the Concrete Idea object to have a business model
     * @param businessModel the business model generated for the idea prompt
     * @return the ConcreteIdea updated with the business model
     */
    @Override
    public Idea setBusinessModel(String businessModel) {
        this.businessModel = businessModel;
        return this;
    }

    /**
     * @return Retrieves the ConcreteIdea's prompt
     */
    @Override
    public String getIdea() {
        return idea;
    }

    /**
     * @return Retrieves the ConcreteIdea's business model
     */
    @Override
    public String getBusinessModel(){
        return businessModel;
    }
}
