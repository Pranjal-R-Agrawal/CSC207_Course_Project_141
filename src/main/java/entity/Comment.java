package entity;

import org.bson.types.ObjectId;

import java.util.List;

public class Comment {
    private ObjectId id;
    private ObjectId parentId;
    private List<ObjectId> childrenId;
    private ObjectId authorId;
    private String body;
    private List<String> qualifications;
    private List<String> collaborationRoles;

    public Comment() {}

    public Comment(ObjectId parentId, List<ObjectId> childrenId, ObjectId authorId, String body, List<String> qualifications, List<String> collaborationRoles) {
        this.parentId = parentId;
        this.childrenId = childrenId;
        this.authorId = authorId;
        this.body = body;
        this.qualifications = qualifications;
        this.collaborationRoles = collaborationRoles;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getParentId() {
        return parentId;
    }

    public void setParentId(ObjectId parentId) {
        this.parentId = parentId;
    }

    public List<ObjectId> getChildrenId() {
        return childrenId;
    }

    public void setChildrenId(List<ObjectId> childrenId) {
        this.childrenId = childrenId;
    }

    public ObjectId getAuthorId() {
        return authorId;
    }

    public void setAuthorId(ObjectId authorId) {
        this.authorId = authorId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<String> qualifications) {
        this.qualifications = qualifications;
    }

    public List<String> getCollaborationRoles() {
        return collaborationRoles;
    }

    public void setCollaborationRoles(List<String> collaborationRoles) {
        this.collaborationRoles = collaborationRoles;
    }
}
