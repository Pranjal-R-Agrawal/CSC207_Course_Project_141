package use_case.generate_idea.interface_adapter;

import use_case.generate_idea.application_business_rules.GenerateIdeaInputBoundary;

/**
 * Routes the control to application logic to perform the use case
 * @author Sidharth Sawhney
 */
public class GenerateIdeaController {
    final GenerateIdeaInputBoundary generateIdeaUseCaseInteractor;

    /**
     * Initializes the controller configured with the GenerateIdeaInputBoundary to route to.
     * @param generateIdeaUseCaseInteractor
     */
    public GenerateIdeaController(GenerateIdeaInputBoundary generateIdeaUseCaseInteractor) {
        this.generateIdeaUseCaseInteractor= generateIdeaUseCaseInteractor;
    }

    /**
     * Passes the control to the GenerateIdeaInputBoundary to perform application logic
     * @param testForException a flag variable useful for testing purposes (set to false for normal execution)
     */
    public void execute(boolean testForException) {
       generateIdeaUseCaseInteractor.execute(testForException);
    }
}
