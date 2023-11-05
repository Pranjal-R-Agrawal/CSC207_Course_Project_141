package use_case.signup.application_business_rules;

import org.bson.types.ObjectId;

public class SignupOutputData {
    private final ObjectId id;
    private final String username;

    public SignupOutputData(ObjectId id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public ObjectId getId() {
        return id;
    }
}
