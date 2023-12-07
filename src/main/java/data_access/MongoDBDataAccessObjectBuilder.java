package data_access;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

public class MongoDBDataAccessObjectBuilder implements MongoDBDataAccessObjectBuilderInterface {
    private String databaseConnectionPath;
    private String databaseName;
    private String usersCollectionName;
    private String postsCollectionName;
    private String commentsCollectionName;
    private String collabRequestsCollectionName;

    @Override
    public MongoDBDataAccessObjectBuilderInterface setDatabaseConnectionPath(String databaseConnectionPath) {
        this.databaseConnectionPath = databaseConnectionPath;
        return this;
    }

    @Override
    public MongoDBDataAccessObjectBuilderInterface setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
        return this;
    }

    @Override
    public MongoDBDataAccessObjectBuilderInterface setUsersCollectionName(String usersCollectionName) {
        this.usersCollectionName = usersCollectionName;
        return this;
    }

    @Override
    public MongoDBDataAccessObjectBuilderInterface setPostsCollectionName(String postsCollectionName) {
        this.postsCollectionName = postsCollectionName;
        return this;
    }

    @Override
    public MongoDBDataAccessObjectBuilderInterface setCommentsCollectionName(String commentsCollectionName) {
        this.commentsCollectionName = commentsCollectionName;
        return this;
    }
    @Override
    public MongoDBDataAccessObjectBuilderInterface setcollabRequestsCollectionName(String collabRequestsCollectionName) {
        this.collabRequestsCollectionName = collabRequestsCollectionName;
        return this;
    }

    @Override
    public MongoDBDataAccessObjectBuilderInterface setStandadParameters() {
        setDatabaseConnectionPath("src/main/java/data_access/database_connection.txt");
        setDatabaseName("forum");
        setUsersCollectionName("users");
        setPostsCollectionName("posts");
        setCommentsCollectionName("comments");
        setcollabRequestsCollectionName("collabRequests");
        return this;
    }

    @Override
    public MongoDBDataAccessObjectBuilderInterface setTestParameters() {
        setDatabaseConnectionPath("src/main/java/data_access/database_connection.txt");
        setDatabaseName("forum_test");
        setUsersCollectionName("users_test");
        setPostsCollectionName("posts_test");
        setCommentsCollectionName("comments_test");
        setcollabRequestsCollectionName("collabRequests_test");
        return this;
    }

    @Override
    public MongoDBDataAccessObject build() throws FileNotFoundException, NoSuchElementException {
        return new MongoDBDataAccessObject(
                databaseConnectionPath, databaseName, usersCollectionName, postsCollectionName, commentsCollectionName, collabRequestsCollectionName
        );
    }
}
