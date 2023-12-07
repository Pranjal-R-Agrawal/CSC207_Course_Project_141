package use_case.view_profile.application_business_rules;

import java.util.ArrayList;
import java.util.List;
/**
 * Bundles the ViewProfile object's data.
 * @author Anbuselvan Ragunathan
 */
public class ViewProfileOutputData {
    private final String username;

    private final String name;

    private final String email;
    private final String projects;
    private final ArrayList<String> collabRequests;
    private final String collab;

    /**
     * Initializes a bundle storing the ViewProfile's output data
     * @param username the username of the logged in user
     * @param email the email of the logged in user
     * @param projects the projects of the logged in user
     * @param collabRequests the collab requests of the logged in user
     */
    public ViewProfileOutputData(String username, String name, String email, String projects, ArrayList<String> collabRequests, String collab) {
        this.username = username;
        this.name = name;
        this.email = email;

        this.projects = projects;
        this.collabRequests = collabRequests;
        this.collab = collab;
    }
    /**
     * @return Returns the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return Returns the name
     */
    public String getName() {
        return name;
    }
    /**
     * @return Returns the email
     */
    public String getEmail() {
        return email;
    }


    public String getProjects() {
        return projects;
    }
    public ArrayList<String> getCollabRequests() {
        return collabRequests;
    }
    public String getCollab() {
        return collab;
    }
}
