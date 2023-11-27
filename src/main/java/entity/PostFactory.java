package entity;

import org.bson.types.ObjectId;

import java.util.List;

public class PostFactory {
    public Post create(ObjectId authorId, String title, String body, List<String> suggestedCollaboratorQualifications){
        return new Post(authorId, title, body, suggestedCollaboratorQualifications);
    }
}
