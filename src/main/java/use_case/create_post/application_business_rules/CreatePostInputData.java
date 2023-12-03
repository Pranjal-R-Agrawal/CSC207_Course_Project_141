package use_case.create_post.application_business_rules;

import org.bson.types.ObjectId;

import java.util.List;

public class CreatePostInputData {
    final private String title;
    final private String body;
    final private String suggestedCollaboratorQualifications;
    public CreatePostInputData(String title, String body, String suggestedCollaboratorQualifications){
        this.title = title;
        this.body = body;
        this.suggestedCollaboratorQualifications = suggestedCollaboratorQualifications;
    }

    public String getBody(){return body;}
    public String getTitle(){return title;}
    public String getSuggestedCollaboratorQualifications() {return suggestedCollaboratorQualifications;}
}
