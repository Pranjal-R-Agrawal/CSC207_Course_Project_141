package data_access;

import entity.ConcreteIdeaFactory;
import entity.Idea;
import entity.IdeaFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MockIdeaDataAccessObject implements GenerateIdeaDataAccessInterface{

    private Map<Integer, Idea> ideas = new HashMap<>();
    private IdeaFactory ideaFactory = new ConcreteIdeaFactory();
    public MockIdeaDataAccessObject() {
        ideas.put(0, ideaFactory.create("A platform that helps employers manage remote workers. It also provides HR services."));
        ideas.put(1, ideaFactory.create("A 3D printing platform that helps business owners create 3D-printed products"));
        ideas.put(2, ideaFactory.create("A platform that helps people easily sell used furniture, household goods, and other items."));
    }
    @Override
    public Idea generateRandomIdea()
    {
        Random random = new Random();
        int randomKeyIndex = random.nextInt(ideas.keySet().size());
        Idea idea = ideas.get(randomKeyIndex);
        return idea;
    }
}
