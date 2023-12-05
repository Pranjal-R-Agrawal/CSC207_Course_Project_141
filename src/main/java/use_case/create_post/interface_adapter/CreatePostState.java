package use_case.create_post.interface_adapter;

/**
 * Stores the state of the CreatePostViewModel
 * @author Yathusan Koneswararajah
 */
public class CreatePostState {
    private String title;
    private String body;
    private String suggestedCollaboratorRoles;
    private String errorMessage;

    /**
     * @return Returns current state's body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body The body of the post
     * @return Returns updated state with updated body
     */
    public CreatePostState setBody(String body) {
        this.body = body;
        return this;
    }

    /**
     * @return Returns current state's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title of the post
     * @return Returns updated state with updated title
     */
    public CreatePostState setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * @return Returns the current state's suggested roles input
     */
    public String getSuggestedCollaboratorRoles() {
        return suggestedCollaboratorRoles;
    }

    /**
     * @param suggestedCollaboratorRoles The syggested roles for collaborators
     * @return Returns the updated state with the updated suggested roles
     */
    public CreatePostState setSuggestedCollaboratorRoles(String suggestedCollaboratorRoles) {
        this.suggestedCollaboratorRoles = suggestedCollaboratorRoles;
        return this;
    }

    /**
     * @return Returns the current state's error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage
     * @return Returns the updated state with the updated error message
     */
    public CreatePostState setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}
