package entity;

import org.bson.types.ObjectId;
/**
 * Represents a CollabRequest entity
 */
public interface CollabRequestFactory {
    CollabRequest create(ObjectId postId, String commenter, String author, String title);
}
