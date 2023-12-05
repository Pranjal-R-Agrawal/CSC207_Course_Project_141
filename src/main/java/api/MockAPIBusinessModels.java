package api;

import entity.Idea;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Concrete implementation of the GenerativeAIAPI interface for testing purposes.
 * No AI Model used.
 * @author Sidharth Sawhney
 */
public class MockAPIBusinessModels implements GenerativeAIAPI{
    private Map<Integer, String> business_models = new HashMap<>();


    /**
     * Initializes a collection of business models statically
     */
    public MockAPIBusinessModels(){
        business_models.put(0,"Subscription-based pricing: The platform could offer a subscription-based pricing model, where employers pay a monthly or annual fee to access the platform's features and services. The pricing could be based on the number of employees being managed or the level of HR-related services required.\n");
        business_models.put(1,"Freemium model: The platform could also offer a freemium model, where basic features are available for free and more advanced features are available for a fee. This could be a good option for small businesses or startups that may not have a large budget for HR-related services.\n");
        business_models.put(2,"The business model for this idea could be based on a subscription model, where business owners pay a monthly fee to access the platform and create 3D-printed products\n");
    }


    /**
     * Returns a random business model from the collection of business models for a given idea prompt
     * @param idea the idea for which the business model must be generated
     * @param testForException a flag variable useful for testing purposes (set to false for normal execution)
     * @return the business model for the idea prompt
     * @throws Exception thrown when testForException is true
     */
    @Override
    public String generateBusinessModel(Idea idea, boolean testForException) throws Exception {
        if (testForException)
            throw new Exception("Unable to produce Business Model");
        Random random = new Random();
        int randomKeyIndex = random.nextInt(business_models.keySet().size());
        String business_model = business_models.get(randomKeyIndex);
        return business_model;
    }
}
