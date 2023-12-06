package entity;

import org.bson.types.ObjectId;

public class PostSearchResults implements PostSearchResultsInterface{
    ObjectId id;
    String title;
    double score;

    public PostSearchResults() {
    }

    public PostSearchResults(ObjectId id, String title, double score) {
        this.id = id;
        this.title = title;
        this.score = score;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
