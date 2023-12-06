package data_access;
import entity.CollabRequest;
import entity.Post;
import entity.User;
import org.bson.types.ObjectId;


public interface ViewProfileDataAccessInterface {
    User getLoggedInUser();
    Post getPostByPostID(ObjectId id);
    CollabRequest getCollabRequestById(ObjectId id);
    void addCollabRequest(CollabRequest collabRequest);


}
