package entity;

import org.bson.types.ObjectId;

import java.util.List;

/**
 * A concrete implementation interface for a factory to create Comment objects
 * @author Yathusan Koneswararajah
 */
public class CommentFactory implements CommentFactoryInterface{
    /**
     * Creates and returns a Comment object
     * @param parentId ID of parent comment
     * @param parentPostId of parent post
     * @param authorId ID of the author of the post
     * @param body Body of the post
     * @param qualifications Qualifications listed by commenter
     * @return a Comment object
     */
    public Comment create(ObjectId parentId, ObjectId parentPostId, ObjectId authorId, String body, List<String> qualifications){
        return new Comment(parentId, parentPostId, authorId, body, qualifications);
    }
}
