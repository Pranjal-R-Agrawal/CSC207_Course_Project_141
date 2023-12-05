package entity;

import org.bson.types.ObjectId;

import java.util.List;

/**
 * An interface for a factory to create Post objects
 * @author Yathusan Koneswararajah
 */
public interface PostFactory {
    /**
     * Creates and returns a Post object
     * @param authorId ID of the author of the post
     * @param title Title of the post
     * @param body Body of the post
     * @param suggestedCollaboratorQualifications Suggested qualifications of the post
     * @return a Post object
     */
    Post create(ObjectId authorId, String title, String body, List<String> suggestedCollaboratorQualifications);
}
