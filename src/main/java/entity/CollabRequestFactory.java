package entity;
import org.bson.types.ObjectId;
public class CollabRequestFactory {
    public CollabRequest create(ObjectId collabRequestId, String author, String title) {
        return new CollabRequest(collabRequestId, author, title);
    }
}
