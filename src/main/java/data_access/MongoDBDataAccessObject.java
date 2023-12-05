package data_access;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import entity.*;
import org.bson.types.ObjectId;

import javax.swing.*;


public class MongoDBDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface, CreateCommentDataAccessInterface, DisplayCommentDataAccessInterface, ViewUserInfoDataAccessInterface {
    private final MongoDatabase database;
    protected MongoCollection<User> users;
    protected MongoCollection<Post> posts;
    protected MongoCollection<Comment> comments;
    private ObjectId loggedInUserID;
    private final String usersCollectionName;
    private final String postsCollectionName;
    private final String commentsCollectionName;

    public MongoDBDataAccessObject(
            String databaseConnectionPath, String databaseName, String usersCollectionName, String postsCollectionName, String commentsCollectionName
    ) throws FileNotFoundException, NoSuchElementException {
        String uri;
        this.usersCollectionName = usersCollectionName;
        this.postsCollectionName = postsCollectionName;
        this.commentsCollectionName = commentsCollectionName;
        try {
            File databaseConnection = new File(databaseConnectionPath);
            Scanner scanner = new Scanner(databaseConnection);
            uri = scanner.nextLine();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Could not find database connection file" + System.lineSeparator() + e.getMessage());
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Database connection file is empty" + System.lineSeparator() + e.getMessage());
        }
        try {
            MongoClient mongoClient = MongoClients.create(uri);

            database = mongoClient.getDatabase(databaseName);

            List<String> collections = database.listCollectionNames().into(new java.util.ArrayList<>());

            if (!collections.contains(usersCollectionName)) database.createCollection(usersCollectionName);
            users = getUsersCollection();

            if (!collections.contains(postsCollectionName)) database.createCollection(postsCollectionName);
            posts = getPostsCollection();

            if (!collections.contains(commentsCollectionName)) database.createCollection(commentsCollectionName);
            comments = getCommentsCollection();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Could not connect to the database\nError: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private MongoCollection<User> getUsersCollection() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        return database.getCollection(usersCollectionName, User.class).withCodecRegistry(pojoCodecRegistry);
    }

    private MongoCollection<Post> getPostsCollection() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        return database.getCollection(postsCollectionName, Post.class).withCodecRegistry(pojoCodecRegistry);
    }

    private MongoCollection<Comment> getCommentsCollection() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        return database.getCollection(commentsCollectionName, Comment.class).withCodecRegistry(pojoCodecRegistry);
    }

    public void resetDatabase() {
        users.drop();
        posts.drop();
        comments.drop();
    }

    public boolean usernameUsed(String username) {
        users = getUsersCollection();
        users.createIndex(Indexes.text("username"));
        for(User user : users.find(Filters.text(username))) return true;
        return false;
    }

    public void addUser(User user) {
        users = getUsersCollection();
        users.insertOne(user);
    }

    public boolean isValid(String username, String password) {
        users = getUsersCollection();
        users.createIndex(Indexes.text("username"));
        for (User user : users.find(Filters.text(username))) {
            if (user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public User getUserByUsername(String username) {
        users = getUsersCollection();
        users.createIndex(Indexes.text("username"));
        return users.find(Filters.text(username)).first();
    }

    public User getUserById(ObjectId id) {
        users = getUsersCollection();
        return users.find(Filters.eq("_id", id)).first();
    }

    public void setLoggedInUserID(ObjectId id) {
        loggedInUserID = id;
    }

    public User getLoggedInUser() {
        users = getUsersCollection();
        return users.find(Filters.eq("_id", loggedInUserID)).first();
    }

    public List<Comment> getCommentsByParentPostID(ObjectId id) {
        comments = getCommentsCollection();
        comments.createIndex(Indexes.text("parentPostId"));
        return comments.find(Filters.eq("parentPostId", id)).into(new ArrayList<Comment>());
    }

    public Comment getCommentByCommentID(ObjectId id) {
        comments = getCommentsCollection();
        return comments.find(Filters.eq("_id", id)).first();
    }

    public Post getPostByPostID(ObjectId id) {
        posts = getPostsCollection();
        return posts.find(Filters.eq("_id", id)).first();
    }

    @Override
    public void addComment(Comment comment) {
        comments = getCommentsCollection();
        comments.insertOne(comment);
    }

    public void addPost(Post post) {
        posts = getPostsCollection();
        posts.insertOne(post);
    }

    @Override
    public ObjectId getLoggedInUserId() {
        return loggedInUserID;
    }
}
