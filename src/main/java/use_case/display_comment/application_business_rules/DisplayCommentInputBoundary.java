package use_case.display_comment.application_business_rules;

import org.bson.types.ObjectId;

public interface DisplayCommentInputBoundary {
    public void execute(ObjectId parentPostID);
}
