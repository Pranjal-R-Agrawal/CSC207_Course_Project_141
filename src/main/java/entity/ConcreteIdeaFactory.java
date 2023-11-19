package entity;
public class ConcreteIdeaFactory implements IdeaFactory{
    /**
     * @param idea
     * @return Idea
     */
    @Override
    public Idea create(String idea) {
        return new ConcreteIdea(idea);
    }
}
