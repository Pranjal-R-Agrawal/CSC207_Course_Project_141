package use_case.view_profile.interface_adapter;

import entity.CollabRequest;

import java.util.List;
/**
 * Stores the state of the ViewProfileViewModel
 * @author Anbuselvan Ragunathan
 */
public class ViewProfileState {
    private String username;
    private String name;
    private String email;
    private String errorMessage;
    private String projects;
    private List<CollabRequest> collabRequests;

    /**
     * @return Returns the current state's username
     */
    public String getUsername() {
        return username;
    }
    /**
     * Sets the username of the state
     * @param username the username
     * @return Returns updated state containing updated username
     */
    public ViewProfileState setUsername(String username) {
        this.username = username;
        return this;
    }
    /**
     * @return Returns the current state's name
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the state
     * @param name the username
     * @return Returns updated state containing updated name
     */
    public ViewProfileState setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return Returns the current state's username
     */
    public String getEmail() {
        return email;
    }
    /**
     * Sets the username of the state
     * @param email the username
     * @return Returns updated state containing updated email
     */
    public ViewProfileState setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * @return Returns the current state's error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the errorMessage of the state
     * @param errorMessage the username
     * @return Returns updated state containing updated errorMessage
     */
    public ViewProfileState setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    /**
     * @return Returns the current state's projects
     */
    public String getProjects() {
        return projects;
    }

    /**
     * Sets the projects of the state
     * @param projects the username
     * @return Returns updated state containing updated projects
     */
    public ViewProfileState setProjects(String projects) {
        this.projects = projects;
        return this;
    }
    /**
     * @return Returns the current state's collab requests
     */
    public List<CollabRequest> getCollabRequests() {
        return collabRequests;
    }

    /**
     * Sets the collabRequests of the state
     * @param collabRequests the username
     * @return Returns updated state containing updated collabRequests
     */
    public ViewProfileState setCollabRequests(List<CollabRequest> collabRequests) {
        this.collabRequests = collabRequests;
        return this;
    }





}
