package entity;

import org.bson.types.ObjectId;

import java.util.List;

/**
 * Creates a Post entity
 */
public class ConcretePostFactory implements PostFactory {
    /**
     * Creates and returns a Post object
     * @param authorId ID of the author of the post
     * @param title Title of the post
     * @param body Body of the post
     * @param suggestedCollaboratorQualifications Suggested qualifications of the post
     * @return a Post object
     */
    public Post create(ObjectId authorId, String title, String body, List<String> suggestedCollaboratorQualifications){
        return new Post(authorId, title, body, suggestedCollaboratorQualifications);
    }
}
