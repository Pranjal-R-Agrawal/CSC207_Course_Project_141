package entity;

import org.bson.types.ObjectId;

public class PostSearchResultsFactory implements PostSearchResultsFactoryInterface {
    @Override
    public PostSearchResultsInterface create(ObjectId id, String title, double score) {
        return new PostSearchResults(id, title, score);
    }
}
