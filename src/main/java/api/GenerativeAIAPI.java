package api;

import entity.Idea;

/**
 * Represents a text generation AI (one or more API endpoint(s) representing the AI model(s))
 * @author Sidharth Sawhney
 */
public interface GenerativeAIAPI {
    /**
     * Method responsible for generating business model for a given idea prompt.
     * @param idea the idea for which the business model must be generated
     * @param testForException a flag variable useful for testing purposes (set to false for normal execution)
     * @return the business model for the idea prompt
     * @throws Exception
     */
    String generateBusinessModel(Idea idea, boolean testForException) throws Exception;
}
