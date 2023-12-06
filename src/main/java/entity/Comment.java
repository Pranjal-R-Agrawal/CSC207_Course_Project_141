package entity;

import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a comment.
 * @author Yathusan Koneswararajah
 */
public class Comment implements CommentInterface{
    private ObjectId id;
    private ObjectId parentId;
    private ObjectId parentPostId;
    private List<ObjectId> childrenId;
    private ObjectId authorId;
    private String body;
    private List<String> qualifications;

    public Comment() {}

    /**
     * Initializes a Comment Object
     * @param parentId ID of parent comment
     * @param parentPostId ID of parent post
     * @param authorId ID of author of comment
     * @param body Body of comment
     * @param qualifications Qualifications being shared by commenter
     */
    public Comment(ObjectId parentId, ObjectId parentPostId, ObjectId authorId, String body, List<String> qualifications) {
        this.parentId = parentId;
        this.parentPostId = parentPostId;
        this.childrenId = new ArrayList<ObjectId>();
        this.authorId = authorId;
        this.body = body;
        this.qualifications = qualifications;
    }

    /**
     * @return Returns ID of comment
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * Sets ID to comment
     * @param id New ObjectId for comment
     */
    public void setId(ObjectId id) {
        this.id = id;
    }

    /**
     * @return Returns ID of top comment in the chain of current comment
     */
    public ObjectId getParentId() {
        return parentId;
    }

    /**
     * Sets the parentID of this comment
     * @param parentId ID of parent comment
     */
    public void setParentId(ObjectId parentId) {
        this.parentId = parentId;
    }

    /**
     * @return Returns ID of post that comment is connected to
     */
    public ObjectId getParentPostId() {
        return parentPostId;
    }

    /**
     * Sets ParentPostId of this comment
     * @param parentPostId ID of parent post
     */
    public void setParentPostId(ObjectId parentPostId) {
        this.parentPostId = parentPostId;
    }

    /**
     * @return Returns list of IDs corresponding to comments under current comment
     */
    public List<ObjectId> getChildrenId() {
        return childrenId;
    }

    /**
     * Sets the list of IDs corresponding to comments under current comment
     * @param childrenId List of IDs for children comments
     */
    public void setChildrenId(List<ObjectId> childrenId) {
        this.childrenId = childrenId;
    }

    /**
     * @return Returns ID of author of comment
     */
    public ObjectId getAuthorId() {
        return authorId;
    }

    /**
     * Sets ID of this comment's author
     * @param authorId ID of author
     */
    public void setAuthorId(ObjectId authorId) {
        this.authorId = authorId;
    }

    /**
     * @return Returns the body of the comment
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the body of the comment
     * @param body Body of comment
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @return Returns qualifications in this comment
     */
    public List<String> getQualifications() {
        return qualifications;
    }

    /**
     * Sets the list of qualifications for this comment
     * @param qualifications Qualifications
     */
    public void setQualifications(List<String> qualifications) {
        this.qualifications = qualifications;
    }
}
