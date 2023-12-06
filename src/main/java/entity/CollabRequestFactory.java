package entity;
import org.bson.types.ObjectId;
public class CollabRequestFactory {
    public CollabRequest create(ObjectId postId, String author, String title) {
        return new CollabRequest(postId, author, title);
    }
}
