package entity;
import org.bson.types.ObjectId;
public class ConcreteCollabRequestFactory implements CollabRequestFactory{
    @Override
    public CollabRequest create(ObjectId postId, String commenter, String author, String title) {
        return new ConcreteCollabRequest(postId, commenter, author, title);
    }
}
