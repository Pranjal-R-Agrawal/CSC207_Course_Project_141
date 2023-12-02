package entity;

/**
 * Represents an Idea
 * @author Sidharth Sawhney
 */
public interface Idea {

    /**
     * @return Retrieves the Idea's prompt
     */
    String getIdea();


    /**
     * @return Retrieves the Idea's business model
     */
    String getBusinessModel();


    /**
     * Updates the Idea object to have a business model
     * @param businessModel the business model generated for the idea prompt
     * @return the Idea object mutated with the business model
     */
    Idea setBusinessModel(String businessModel);
}
