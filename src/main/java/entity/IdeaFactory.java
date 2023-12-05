package entity;

/**
 * Represents a factory to create Idea objects
 * @author Sidharth Sawhney
 */
public interface IdeaFactory {

    /** Creates an Idea Object given an idea prompt.
     * @param idea the idea prompt
     * @return Idea- Idea Object containing only prompt and no business model
     */
    Idea create(String idea);
}
