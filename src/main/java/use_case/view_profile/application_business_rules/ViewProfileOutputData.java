package use_case.view_profile.application_business_rules;

import java.util.ArrayList;
import java.util.List;

public class ViewProfileOutputData {
    private final String username;

    private final String name;

    private final String email;
    private final String projects;
    private final ArrayList<String> collabRequests;



    public ViewProfileOutputData(String username, String name, String email, String projects, ArrayList<String> collabRequests) {
        this.username = username;
        this.name = name;
        this.email = email;

        this.projects = projects;
        this.collabRequests = collabRequests;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }


    public String getProjects() {
        return projects;
    }
    public ArrayList<String> getCollabRequests() {
        return collabRequests;
    }
}
