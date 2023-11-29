package use_case.display_post.application_business_rules;

import org.bson.types.ObjectId;

public class DisplayPostInputData {
    private final ObjectId id;
    private final int config;

    public DisplayPostInputData(ObjectId id, int config) {
        this.id = id;
        this.config = config;
    }

    public ObjectId getId() {
        return id;
    }

    public int getConfig() {
        return config;
    }
}
