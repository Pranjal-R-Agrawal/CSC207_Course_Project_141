package use_case.create_post.application_business_rules;

/**
 * Bundles the inputs for a post that is to be created
 * @author Yathusan Koneswararajah
 */
public class CreatePostInputData {
    final private String title;
    final private String body;
    final private String suggestedCollaboratorQualifications;

    /**
     * Initializes the data class that bundles the necessary data for creating posts
     * @param title Title of the new post
     * @param body Description of project
     * @param suggestedCollaboratorQualifications Qualifications the author of the post is looking for
     */
    public CreatePostInputData(String title, String body, String suggestedCollaboratorQualifications){
        this.title = title;
        this.body = body;
        this.suggestedCollaboratorQualifications = suggestedCollaboratorQualifications;
    }

    /**
     * @return Returns the body of the post
     */
    public String getBody(){return body;}

    /**
     * @return Returns the title of the post
     */
    public String getTitle(){return title;}

    /**
     * @return Returns the Suggested Qualifications for Collaborators
     */
    public String getSuggestedCollaboratorQualifications() {return suggestedCollaboratorQualifications;}
}
