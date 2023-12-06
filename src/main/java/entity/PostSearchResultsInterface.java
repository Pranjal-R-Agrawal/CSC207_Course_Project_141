package entity;

import org.bson.types.ObjectId;

public interface PostSearchResultsInterface {
    public ObjectId getId();

    public void setId(ObjectId id);

    public String getTitle();

    public void setTitle(String title);

    public double getScore();

    public void setScore(double score);
}
