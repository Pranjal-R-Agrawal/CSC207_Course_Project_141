package use_case.create_post.interface_adapter;

public class CreatePostState {
    private String title;
    private String body;
    private String suggestedCollaboratorRoles;
    private String errorMessage;

    public String getBody() {
        return body;
    }

    public CreatePostState setBody(String body) {
        this.body = body;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CreatePostState setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSuggestedCollaboratorRoles() {
        return suggestedCollaboratorRoles;
    }

    public CreatePostState setSuggestedCollaboratorRoles(String suggestedCollaboratorRoles) {
        this.suggestedCollaboratorRoles = suggestedCollaboratorRoles;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public CreatePostState setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}