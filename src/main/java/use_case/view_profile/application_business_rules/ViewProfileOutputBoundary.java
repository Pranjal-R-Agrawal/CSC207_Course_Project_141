package use_case.view_profile.application_business_rules;
/*
 * Responsible for facilitating display of output data.
 * @author Anbuselvan Ragunathan
 */
public interface ViewProfileOutputBoundary {
    /**
     * Directs components of the Interface Adapters to display the output data
     * @param user - Output data (the ViewProfileOutputData object) to be displayed
     */
    void prepareSuccessView(ViewProfileOutputData user);


}
