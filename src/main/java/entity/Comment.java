package entity;

import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class Comment {
    private ObjectId id;
    private ObjectId parentId;
    private ObjectId parentPostId;
    private List<ObjectId> childrenId;
    private ObjectId authorId;
    private String body;
    private List<String> qualifications;

    public Comment() {}

    public Comment(ObjectId parentId, ObjectId parentPostId, ObjectId authorId, String body, List<String> qualifications) {
        this.parentId = parentId;
        this.parentPostId = parentPostId;
        this.childrenId = new ArrayList<ObjectId>();
        this.authorId = authorId;
        this.body = body;
        this.qualifications = qualifications;
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

    public ObjectId getParentPostId() {
        return parentPostId;
    }

    public void setParentPostId(ObjectId parentPostId) {
        this.parentPostId = parentPostId;
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
}
