package data_access;
import entity.CollabRequest;
import entity.Comment;
import entity.User;
import entity.Post;
import org.bson.types.ObjectId;
public interface CollabRequestDataAccessInterface {
    Post getPostByPostId(ObjectId id);
    User getUserById(ObjectId id);
    void addCollabRequest(CollabRequest collabRequest);
    Comment getCommentByCommentID(ObjectId id);

}
