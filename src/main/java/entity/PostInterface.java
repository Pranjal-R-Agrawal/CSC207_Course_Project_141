package entity;

import org.bson.types.ObjectId;

import java.util.List;

public interface PostInterface {
    public ObjectId getId();
    public void setId(ObjectId id);

    public List<ObjectId> getChildrenId();

    public void setChildrenId(List<ObjectId> childrenId);

    public ObjectId getAuthorID();

    public void setAuthorID(ObjectId authorID);

    public List<ObjectId> getCollaboratorIDs();

    public void setCollaboratorIDs(List<ObjectId> collaboratorIDs);

    public String getTitle();

    public void setTitle(String title);

    public String getBody();

    public void setBody(String body);

    public List<String> getSuggestedCollaboratorQualifications();

    public void setSuggestedCollaboratorQualifications(List<String> suggestedCollaboratorQualifications);

    public boolean isClosed();

    public void setClosed(boolean closed);

    public boolean isCompleted();

    public void setCompleted(boolean completed);

}
