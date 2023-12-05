package use_case.generate_idea.application_business_rules;

/*
* Responsible for facilitating display of output data.
* @author Sidharth Sawhney
*/
public interface GenerateIdeaOutputBoundary {

    /**
     * Directs components of the Interface Adapters to display the output data
     * @param ideaOutputData - Output data (the Idea object) to be displayed
     */
    void prepareSuccessView(GenerateIdeaOutputData ideaOutputData);


}
