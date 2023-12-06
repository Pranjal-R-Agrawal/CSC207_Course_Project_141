package entity;
import org.bson.types.ObjectId;
/**
 * Represents a CollabRequest entity
 */
public class ConcreteCollabRequestFactory implements CollabRequestFactory{
    /**
     * Constructor for CollabRequest prevents instantiation
     */
    @Override
    public CollabRequest create(ObjectId postId, String commenter, String author, String title) {
        return new ConcreteCollabRequest(postId, commenter, author, title);
    }
}
