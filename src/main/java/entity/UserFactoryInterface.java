package entity;


/**
 * Represents a factory to create UserInterface objects
 * @author Sidharth Sawhney
 */
public interface UserFactoryInterface {
    /** Creates a UserInterface Object given username, password, name, email, phone number(optionally),city and field of expertise.
    * @param username the username
     * @param password the password
     * @param name the name of the user
     * @param email email address of the user
     * @param phoneNumber phone number of the user (optional)
     * @param city city of the user
     * @param fieldOfExpertise field of expertise of the user
     * @return UserInterface Object containing username, password, name, email, phone number(optionally),city and field of expertise.
     */
    UserInterface create(String username, String password, String name, String email, String phoneNumber, String city, String fieldOfExpertise);


}
