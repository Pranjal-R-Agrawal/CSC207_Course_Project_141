package entity;

import org.bson.types.ObjectId;

public interface CollabRequestFactory {
    CollabRequest create(ObjectId postId, String commenter, String author, String title);
}
