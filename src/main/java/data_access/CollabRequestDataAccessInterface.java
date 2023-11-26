package data_access;
import entity.User;
import entity.Post;
import org.bson.types.ObjectId;
public interface CollabRequestDataAccessInterface {
    User getUserByUsername(String username);
    Post getPostByPostID(ObjectId id);



}
