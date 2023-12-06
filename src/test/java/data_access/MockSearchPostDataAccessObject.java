package data_access;

import entity.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class MockSearchPostDataAccessObject implements SearchPostsByTitleDataAccessInterface {
    List<PostInterface> posts = new ArrayList<>();
    List<User> users = new ArrayList<>();
    @Override
    public List<PostSearchResultsInterface> getPostsByTitle(String query) {
        ArrayList<PostSearchResultsInterface> results = new ArrayList<>();
        for (PostInterface post : posts) {
            if (post.getTitle().contains(query)) {
                results.add(new PostSearchResults(post.getId(), post.getTitle(), 0));
            }
        }
        return results;
    }

    public void addPost(PostInterface post) {
        post.setId(new ObjectId());
        posts.add(post);
    }

    public void addUser(User user) {
        user.setId(new ObjectId());
        users.add(user);
    }
}
