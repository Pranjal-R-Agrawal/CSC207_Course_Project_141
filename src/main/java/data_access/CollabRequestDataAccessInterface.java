package data_access;
import entity.CollabRequest;
import entity.Comment;
import entity.User;
import entity.Post;
import org.bson.types.ObjectId;
/**
 * Represents a Data Access Object to access data from the database for the CollabRequestUseCase
 * @author Anbuselvan Ragunathan
 */
public interface CollabRequestDataAccessInterface {
    /**
     * Returns a post from the database.
     * @return a post pertaining to the id
     */
    Post getPostByPostId(ObjectId id);
    /**
     * Returns a user from the database.
     * @return a user pertaining to the id
     */
    User getUserById(ObjectId id);
    /**
     * add a collab request to the database
     */
    void addCollabRequest(CollabRequest collabRequest);
    /**
     * Returns a comment from the database.
     * @return a comment pertaining to the id
     */
    Comment getCommentByCommentID(ObjectId id);

}
