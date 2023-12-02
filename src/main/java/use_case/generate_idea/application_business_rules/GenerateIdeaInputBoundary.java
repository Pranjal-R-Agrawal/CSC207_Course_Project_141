package use_case.generate_idea.application_business_rules;

/**
 * Interface responsible for core application logic.
 * @author Sidharth Sawhney
 */
public interface GenerateIdeaInputBoundary {

    /**
     * Executes the application logic.
     * @param testForException a flag variable useful for testing purposes (set to false for normal execution)
     */
    void execute(boolean testForException);
}
