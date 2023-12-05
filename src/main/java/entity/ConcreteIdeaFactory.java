package entity;

/**
 * Concrete implementation of IdeaFactory interface to construct ConcreteIdea objects
 * @author Sidharth Sawhney
 */
public class ConcreteIdeaFactory implements IdeaFactory{
    /** Creates a ConcreteIdea Object given an idea prompt.
     * @param idea the idea prompt
     * @return Idea an object of type Idea - containing only prompt and no business model
     */
    @Override
    public Idea create(String idea) {
        return new ConcreteIdea(idea);
    }
}
