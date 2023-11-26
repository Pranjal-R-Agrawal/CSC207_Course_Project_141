package use_case.display_comment.application_business_rules;

import org.bson.types.ObjectId;

public class DisplayCommentInputData {
    private final ObjectId parentPostId;
    private final int config;

    public DisplayCommentInputData(ObjectId parentPostId, int config) {
        this.parentPostId = parentPostId;
        this.config = config;
    }

    public ObjectId getParentPostId() {
        return parentPostId;
    }

    public int getConfig() {
        return config;
    }
}
