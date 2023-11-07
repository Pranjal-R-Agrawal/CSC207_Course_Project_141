package entity;

import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class Post {
    private ObjectId id;
    private List<ObjectId> childrenId;
    private ObjectId authorID;
    private List<ObjectId> collaboratorIDs;
    private String title;
    private String body;
    private List<String> collaborationRoles;
    private List<String> suggestedCollaboratorQualifications;
    private boolean closed;
    private boolean completed;

    public Post() {}

    public Post(ObjectId authorID, String title, String body, List<String> collaborationRoles, List<String> suggestedCollaboratorQualifications) {
        this.childrenId = new ArrayList<ObjectId>();
        this.authorID = authorID;
        this.collaboratorIDs = new ArrayList<ObjectId>();
        this.title = title;
        this.body = body;
        this.collaborationRoles = collaborationRoles;
        this.suggestedCollaboratorQualifications = suggestedCollaboratorQualifications;
        this.closed = false;
        this.completed = false;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public List<ObjectId> getChildrenId() {
        return childrenId;
    }

    public void setChildrenId(List<ObjectId> childrenId) {
        this.childrenId = childrenId;
    }

    public ObjectId getAuthorID() {
        return authorID;
    }

    public void setAuthorID(ObjectId authorID) {
        this.authorID = authorID;
    }

    public List<ObjectId> getCollaboratorIDs() {
        return collaboratorIDs;
    }

    public void setCollaboratorIDs(List<ObjectId> collaboratorIDs) {
        this.collaboratorIDs = collaboratorIDs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getCollaborationRoles() {
        return collaborationRoles;
    }

    public void setCollaborationRoles(List<String> collaborationRoles) {
        this.collaborationRoles = collaborationRoles;
    }

    public List<String> getSuggestedCollaboratorQualifications() {
        return suggestedCollaboratorQualifications;
    }

    public void setSuggestedCollaboratorQualifications(List<String> suggestedCollaboratorQualifications) {
        this.suggestedCollaboratorQualifications = suggestedCollaboratorQualifications;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
