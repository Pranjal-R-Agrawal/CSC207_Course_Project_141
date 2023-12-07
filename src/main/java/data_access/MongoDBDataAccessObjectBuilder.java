package data_access;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

public class MongoDBDataAccessObjectBuilder {
    private String databaseConnectionPath;
    private String databaseName;
    private String usersCollectionName;
    private String postsCollectionName;
    private String commentsCollectionName;
    private String collabRequestsCollectionName;

    public MongoDBDataAccessObjectBuilder setDatabaseConnectionPath(String databaseConnectionPath) {
        this.databaseConnectionPath = databaseConnectionPath;
        return this;
    }

    public MongoDBDataAccessObjectBuilder setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
        return this;
    }

    public MongoDBDataAccessObjectBuilder setUsersCollectionName(String usersCollectionName) {
        this.usersCollectionName = usersCollectionName;
        return this;
    }

    public MongoDBDataAccessObjectBuilder setPostsCollectionName(String postsCollectionName) {
        this.postsCollectionName = postsCollectionName;
        return this;
    }

    public MongoDBDataAccessObjectBuilder setCommentsCollectionName(String commentsCollectionName) {
        this.commentsCollectionName = commentsCollectionName;
        return this;
    }
    public MongoDBDataAccessObjectBuilder setcollabRequestsCollectionName(String collabRequestsCollectionName) {
        this.collabRequestsCollectionName = collabRequestsCollectionName;
        return this;
    }

    public MongoDBDataAccessObjectBuilder setStandadParameters() {
        setDatabaseConnectionPath("src/main/java/data_access/database_connection.txt");
        setDatabaseName("forum");
        setUsersCollectionName("users");
        setPostsCollectionName("posts");
        setCommentsCollectionName("comments");
        setcollabRequestsCollectionName("collabRequests");
        return this;
    }

    public MongoDBDataAccessObjectBuilder setTestParameters() {
        setDatabaseConnectionPath("src/main/java/data_access/database_connection.txt");
        setDatabaseName("forum_test");
        setUsersCollectionName("users_test");
        setPostsCollectionName("posts_test");
        setCommentsCollectionName("comments_test");
        setcollabRequestsCollectionName("collabRequests_test");
        return this;
    }

    public MongoDBDataAccessObject build() throws FileNotFoundException, NoSuchElementException {
        return new MongoDBDataAccessObject(
                databaseConnectionPath, databaseName, usersCollectionName, postsCollectionName, commentsCollectionName, collabRequestsCollectionName
        );
    }
}
