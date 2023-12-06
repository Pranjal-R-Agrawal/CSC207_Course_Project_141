package data_access;

import entity.PostSearchResultsInterface;

import java.util.List;

public interface SearchPostsByTitleDataAccessInterface {
    List<PostSearchResultsInterface> getPostsByTitle(String query);
}
