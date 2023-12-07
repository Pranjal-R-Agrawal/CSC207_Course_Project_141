package data_access;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

public interface MongoDBDataAccessObjectBuilderInterface {
    MongoDBDataAccessObjectBuilderInterface setDatabaseConnectionPath(String databaseConnectionPath);

    MongoDBDataAccessObjectBuilderInterface setDatabaseName(String databaseName);

    MongoDBDataAccessObjectBuilderInterface setUsersCollectionName(String usersCollectionName);

    MongoDBDataAccessObjectBuilderInterface setPostsCollectionName(String postsCollectionName);

    MongoDBDataAccessObjectBuilderInterface setCommentsCollectionName(String commentsCollectionName);

    MongoDBDataAccessObjectBuilderInterface setcollabRequestsCollectionName(String collabRequestsCollectionName);

    MongoDBDataAccessObjectBuilderInterface setStandadParameters();

    MongoDBDataAccessObjectBuilderInterface setTestParameters();

    MongoDBDataAccessObject build() throws FileNotFoundException, NoSuchElementException;
}
