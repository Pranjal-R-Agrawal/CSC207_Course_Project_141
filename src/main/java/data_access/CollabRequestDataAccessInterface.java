package data_access;
import entity.Comment;
import org.bson.types.ObjectId;
public interface CollabRequestDataAccessInterface {
    Comment getCommentByCommentId(ObjectId id);
}
