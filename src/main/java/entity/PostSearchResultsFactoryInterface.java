package entity;

import org.bson.types.ObjectId;

public interface PostSearchResultsFactoryInterface {
    PostSearchResultsInterface create(ObjectId id, String title, double score);
}
