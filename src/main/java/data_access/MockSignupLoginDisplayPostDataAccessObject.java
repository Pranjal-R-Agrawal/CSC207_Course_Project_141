package data_access;

import entity.Comment;
import entity.Post;
import entity.User;
import entity.UserInterface;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockSignupLoginDisplayPostDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface, DisplayCommentDataAccessInterface{
    Map<ObjectId, UserInterface> users = new HashMap<>();
    Map<ObjectId, Post> posts = new HashMap<>();
    Map<ObjectId, Comment> comments = new HashMap<>();
    ObjectId loggedInUserID = null;

    @Override
    public Comment getCommentByCommentID(ObjectId id) {
        return comments.get(id);
    }

    @Override
    public Post getPostByPostID(ObjectId id) {
        return posts.get(id);
    }

    @Override
    public List<Comment> getCommentsByParentPostID(ObjectId id) {
        List<Comment> comments = new ArrayList<>();
        for (Comment comment : this.comments.values()) {
            if (comment.getParentPostId().equals(id)) {
                comments.add(comment);
            }
        }
        return comments;
    }

    @Override
    public User getUserById(ObjectId id) {
        return (User) users.get(id);
    }

    @Override
    public ObjectId getLoggedInUserId() {
        return loggedInUserID;
    }

    @Override
    public boolean isValid(String username, String password) {
        for (UserInterface user : users.values()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User getUserByUsername(String username) {
        for (UserInterface user : users.values()) {
            if (user.getUsername().equals(username)) {
                return (User) user;
            }
        }
        return null;
    }

    @Override
    public void setLoggedInUserID(ObjectId id) {
        loggedInUserID = id;
    }

    @Override
    public boolean usernameUsed(String username) {
        for (UserInterface user : users.values()) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addUser(UserInterface user) {
        user.setId(new ObjectId());
        users.put(user.getId(), user);
    }

    public void addPost(Post post) {
        post.setId(new ObjectId());
        posts.put(post.getId(), post);
    }

    public void addComment(Comment comment) {
        comment.setId(new ObjectId());
        comments.put(comment.getId(), comment);
    }
}
