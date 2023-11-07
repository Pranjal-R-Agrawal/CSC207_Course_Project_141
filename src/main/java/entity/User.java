package entity;

import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class User {
    private ObjectId id;
    private String username;
    private String password;
    private List<ObjectId> postIDs;
    private List<ObjectId> commentIDs;
    private String name;
    private String email;
    private String phoneNumber;
    private double rating;
    private int numRatings;

    public User() {}

    public User(String username, String password, String name, String email, String phoneNumber) {
        this.username = (username == null) ? "" : username.trim();
        this.password = (password == null) ? "" : password.trim();
        this.postIDs = new ArrayList<ObjectId>();
        this.commentIDs = new ArrayList<ObjectId>();
        this.name = (name == null) ? "" : name.trim();
        this.email = (email == null) ? "" : email.trim();
        this.phoneNumber = (phoneNumber == null) ? "" : phoneNumber.trim();
        this.rating = 0.0d;
        this.numRatings = 0;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ObjectId> getPostIDs() {
        return postIDs;
    }

    public void setPostIDs(List<ObjectId> postIDs) {
        this.postIDs = postIDs;
    }

    public List<ObjectId> getCommentIDs() {
        return commentIDs;
    }

    public void setCommentIDs(List<ObjectId> commentIDs) {
        this.commentIDs = commentIDs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(int numRatings) {
        this.numRatings = numRatings;
    }
}
