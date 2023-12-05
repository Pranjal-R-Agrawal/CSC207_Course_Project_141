package data_access;
import entity.CollabRequest;
import entity.Comment;
import entity.User;
import entity.Post;
import org.bson.types.ObjectId;
public interface CollabRequestDataAccessInterface {
    Post getPostByPostId(ObjectId id);
    User getUserByUserId(ObjectId id);

}
