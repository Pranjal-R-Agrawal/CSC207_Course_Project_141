package data_access;

import entity.Idea;
/**
 * Represents a Data Access Object to access data from the database for the Generate Idea Use Case
 * @author Sidharth Sawhney
 */
public interface GenerateIdeaDataAccessInterface {
    /**
     * Reads random idea from the database.
     * @return an idea containing its prompt only (no business model)
     */
    Idea generateRandomIdea();
}
