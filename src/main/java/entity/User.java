package entity;

import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete implementation of the UserInterface. Represents a User.
 * @author Sidharth Sawhney
 */
public class User implements UserInterface{
    private ObjectId id;
    private String username;
    private String password;
    private List<ObjectId> postIDs;
    private List<ObjectId> commentIDs;
    private List<ObjectId> collaborationRequestIDs;
    private String name;
    private String email;
    private String phoneNumber;
    private String city;
    private String fieldOfExpertise;
    private double rating;
    private int numRatings;

    public User() {}
    /**
     * Initializes a User containing username, password, name, email, phone number(optionally),city and field of expertise.
     * @param username the username
     * @param password the password
     * @param name the name of the user
     * @param email email address of the user
     * @param phoneNumber phone number of the user (optional)
     * @param city city of the user
     * @param fieldOfExpertise field of expertise of the user
     */
    public User(String username, String password, String name, String email, String phoneNumber, String city, String fieldOfExpertise) {
        this.username = (username == null) ? "" : username.trim();
        this.password = (password == null) ? "" : password.trim();
        this.postIDs = new ArrayList<ObjectId>();
        this.commentIDs = new ArrayList<ObjectId>();
        this.collaborationRequestIDs = new ArrayList<ObjectId>();
        this.name = (name == null) ? "" : name.trim();
        this.email = (email == null) ? "" : email.trim();
        this.phoneNumber = (phoneNumber == null) ? "" : phoneNumber.trim();
        this.city = (city == null) ? "" : city.trim();
        this.fieldOfExpertise = (fieldOfExpertise == null) ? "" : fieldOfExpertise.trim();
        this.rating = 0.0d;
        this.numRatings = 0;
    }
    /**
     * @return Retrieves the id of user
     */
@Override
    public ObjectId getId() {
        return id;
    }
    /**
     * @return Sets the id of user
     */
    @Override
    public void setId(ObjectId id) {
        this.id = id;
    }
    /**
     * @return Gets username
     */
    @Override
    public String getUsername() {
        return username;
    }
    /**
     * @return Sets username
     */
    @Override
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * @return Gets password
     */
    @Override
    public String getPassword() {
        return password;
    }
    /**
     * @return Sets password
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @return Gets postIds
     */
    @Override
    public List<ObjectId> getPostIDs() {
        return postIDs;
    }
    /**
     * @return Set postIds
     */
    @Override
    public void setPostIDs(List<ObjectId> postIDs) {
        this.postIDs = postIDs;
    }
    /**
     * @return Gets the comment ids
     */
    @Override
    public List<ObjectId> getCommentIDs() {
        return commentIDs;
    }
    /**
     * @return Sets the comment ids
     */
    @Override
    public void setCommentIDs(List<ObjectId> commentIDs) {
        this.commentIDs = commentIDs;
    }
    /**
     * @return Gets the collaboration request ids
     */
    @Override
    public List<ObjectId> getCollaborationRequestIDs() {
        return collaborationRequestIDs;
    }
    /**
     * @return Sets the collaboration request ids
     */
    @Override
    public void setCollaborationRequestIDs(List<ObjectId> collaborationRequestIDs) {
        this.collaborationRequestIDs = collaborationRequestIDs;
    }
    /**
     * @return Gets the name of the user
     */
    @Override
    public String getName() {
        return name;
    }
    /**
     * @return Sets the name of the user
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return Gets the email of the user
     */
    @Override
    public String getEmail() {
        return email;
    }
    /**
     * @return Sets the email of the user
     */
    @Override
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return Gets the phone number of the user
     */
    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * @return Sets the phone number of the user
     */
    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * @return Gets the city of the user
     */

    @Override
    public String getCity() {
        return city;
    }
    /**
     * @return Sets the city of the user
     */
    @Override
    public void setCity(String city) {
        this.city = city;
    }
    /**
     * @return Gets the field of expertise of the user
     */
    @Override
    public String getFieldOfExpertise() {
        return fieldOfExpertise;
    }
    /**
     * @return Sets the field of expertise of the user
     */
    @Override
    public void setFieldOfExpertise(String fieldOfExpertise) {
        this.fieldOfExpertise = fieldOfExpertise;
    }
    /**
     * @return Gets the rating of the user
     */
    @Override
    public double getRating() {
        return rating;
    }
    /**
     * @return Sets the rating of the user
     */
    @Override
    public void setRating(double rating) {
        this.rating = rating;
    }
    /**
     * @return Gets the number of ratings of the user
     */
    @Override
    public int getNumRatings() {
        return numRatings;
    }
    /**
     * @return Sets the number of ratings of the user
     */
    @Override
    public void setNumRatings(int numRatings) {
        this.numRatings = numRatings;
    }
}
