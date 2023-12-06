package entity;

import org.bson.types.ObjectId;

import java.util.List;
/**
 * Represents comments
 * @author Yathusan Koneswararajah
 */
public interface CommentInterface {
    /**
     * @return Returns ID of comment
     */
    public ObjectId getId();

    /**
     * Sets ID to comment
     * @param id New ObjectId for comment
     */
    public void setId(ObjectId id);

    /**
     * @return Returns ID of top comment in the chain of current comment
     */
    public ObjectId getParentId();

    /**
     * Sets the parentID of this comment
     * @param parentId ID of parent comment
     */
    public void setParentId(ObjectId parentId);

    /**
     * @return Returns ID of post that comment is connected to
     */
    public ObjectId getParentPostId();

    /**
     * Sets ParentPostId of this comment
     * @param parentPostId ID of parent post
     */
    public void setParentPostId(ObjectId parentPostId);

    /**
     * @return Returns list of IDs corresponding to comments under current comment
     */
    public List<ObjectId> getChildrenId();

    /**
     * Sets the list of IDs corresponding to comments under current comment
     * @param childrenId List of IDs for children comments
     */
    public void setChildrenId(List<ObjectId> childrenId);

    /**
     * @return Returns ID of author of comment
     */
    public ObjectId getAuthorId();

    /**
     * Sets ID of this comment's author
     * @param authorId ID of author
     */
    public void setAuthorId(ObjectId authorId);

    /**
     * @return Returns the body of the comment
     */
    public String getBody();

    /**
     * Sets the body of the comment
     * @param body Body of comment
     */
    public void setBody(String body);

    /**
     * @return Returns qualifications in this comment
     */
    public List<String> getQualifications();

    /**
     * Sets the list of qualifications for this comment
     * @param qualifications Qualifications
     */
    public void setQualifications(List<String> qualifications);
}
