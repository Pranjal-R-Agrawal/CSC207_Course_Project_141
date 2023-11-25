package entity;

import org.bson.types.ObjectId;

import java.util.List;

public class CommentFactory {
    public Comment create(ObjectId parentId, ObjectId parentPostId, ObjectId authorId, String body, List<String> qualifications){
        return new Comment(parentId, parentPostId, authorId, body, qualifications);
    }
}
