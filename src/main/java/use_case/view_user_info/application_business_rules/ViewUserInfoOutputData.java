package use_case.view_user_info.application_business_rules;

/**
 * Output Data class packaging the data that needs to be displayed
 * @author Tanmay Shinde
 */
public class ViewUserInfoOutputData {

    private final String name;

    private final String fieldOfExpertise;

    private final String city;

    private final String email;

    private final String phoneNumber;

    /**
     * Initializes the output data object
     * @param name name of the user
     * @param fieldOfExpertise field of expertise of the user
     * @param city city of the user
     * @param email email address of the user
     * @param phoneNumber phone number of the user
     */
    public ViewUserInfoOutputData(String name, String fieldOfExpertise, String city, String email, String phoneNumber) {
        this.name = name;
        this.fieldOfExpertise = fieldOfExpertise;
        this.city = city;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Getter for name
     * @return Returns the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for email
     * @return Returns the user's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter for phoneNumber
     * @return Returns the user's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Getter for fieldOfExpertise
     * @return Returns the user's field of expertise
     */
    public String getFieldOfExpertise() {
        return fieldOfExpertise;
    }

    /**
     * Getter for city
     * @return Returns the user's city of residence
     */
    public String getCity() {
        return city;
    }
}
