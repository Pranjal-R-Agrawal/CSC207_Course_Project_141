package use_case.display_comment.application_business_rules;

import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.Map;

public class DisplayCommentOutputData {
    private  Map<ObjectId, Map<String, Object>> comments;
    private Map<String, Object> post;

    public DisplayCommentOutputData(int size) {
        comments = new HashMap<>(size + 1, 1);
        post = new HashMap<>(7, 1);
    }

    public Map<ObjectId, Map<String, Object>> getComments() {
        return comments;
    }

    public Map<String, Object> getPost() {
        return post;
    }
}
