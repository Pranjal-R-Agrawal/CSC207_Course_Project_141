package entity;

import org.bson.types.ObjectId;

import java.util.List;

/**
 * Represents different types of users
 * @author Sidharth Sawhney
 */
public interface UserInterface {
    /**
     * @return Retrieves the id of user
     */
     ObjectId getId();
    /**
     * @return Sets the id of user
     */
    void setId(ObjectId id);
    /**
     * @return Gets username
     */
    String getUsername();
    /**
     * @return Sets username
     */
     void setUsername(String username);
    /**
     * @return Gets password
     */
     String getPassword();
    /**
     * @return Sets password
     */
    void setPassword(String password);
    /**
     * @return Gets postIds
     */
   List<ObjectId> getPostIDs();
    /**
     * @return Set postIds
     */
 void setPostIDs(List<ObjectId> postIDs);
    /**
     * @return Gets the comment ids
     */
    List<ObjectId> getCommentIDs();
    /**
     * @return Sets the comment ids
     */
    void setCommentIDs(List<ObjectId> commentIDs);
    /**
     * @return Gets the collaboration request ids
     */
    List<ObjectId> getCollaborationRequestIDs();
    /**
     * @return Sets the collaboration request ids
     */
    void setCollaborationRequestIDs(List<ObjectId> collaborationRequestIDs);

    /**
     * @return Gets the name of the user
     */
    String getName();
    /**
     * @return Sets the name of the user
     */
     void setName(String name);
    /**
     * @return Gets the email of the user
     */
    String getEmail();
    /**
     * @return Sets the email of the user
     */
     void setEmail(String email);
    /**
     * @return Gets the phone number of the user
     */
     String getPhoneNumber();
    /**
     * @return Sets the phone number of the user
     */
     void setPhoneNumber(String phoneNumber);
    /**
     * @return Gets the city of the user
     */
    String getCity();
    /**
     * @return Sets the city of the user
     */
     void setCity(String city);
    /**
     * @return Gets the field of expertise of the user
     */
    String getFieldOfExpertise();
    /**
     * @return Sets the field of expertise of the user
     */
     void setFieldOfExpertise(String fieldOfExpertise);
    void addCollabRequest(ObjectId postId);
}
