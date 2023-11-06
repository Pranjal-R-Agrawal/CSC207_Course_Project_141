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
import java.util.Scanner;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import entity.*;

import javax.swing.*;

public class MongoDBDataAccessObject implements SignupUserDataAccessInterface {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<User> users;
    private MongoCollection<Post> posts;
    private MongoCollection<Comment> comments;

    public MongoDBDataAccessObject(String databaseConnectionPath) {
        String uri;
        try {
            File databaseConnection = new File(databaseConnectionPath);
            Scanner scanner = new Scanner(databaseConnection);
            uri = scanner.nextLine();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Couldn't read database connection string\nError: " + e.getMessage());
            throw new RuntimeException();
        }
        try {
            mongoClient = MongoClients.create(uri);
            CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
            CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

            database = mongoClient.getDatabase("CSC207");
            users = database.getCollection("Users", User.class).withCodecRegistry(pojoCodecRegistry);
            posts = database.getCollection("Posts", Post.class).withCodecRegistry(pojoCodecRegistry);
            comments = database.getCollection("Comments", Comment.class).withCodecRegistry(pojoCodecRegistry);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Could not connect to the database\nError: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public MongoDBDataAccessObject() {
        this("src/main/java/data_access/database_connection.txt");
    }

    public boolean usernameUsed(String username) {
        users.createIndex(Indexes.text("username"));
        for(User user : users.find(Filters.text(username))) return true;
        return false;
    }

    public void addUser(User user) {
        users.insertOne(user);
    }
}
