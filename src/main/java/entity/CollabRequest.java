package entity;

import org.bson.types.ObjectId;
/**
 * Represents a CollabRequest entity
 */
public interface CollabRequest {
    /**
     * Returns the id of the Post.
     * @return the id of the Post
     */
    ObjectId getPostId();
    /**
     * set id of the post.
     */
    void setPostId(ObjectId postId);
    /**
     * Returns the author from the database
     * @return the author string username
     */
    String getAuthor();
    /**
     * set author of the post.
     */
    void setAuthor(String author);
    /**
     * Returns the title of the Post.
     * @return the title of the Post
     */
    String getTitle();
    /**
     * set title of the post.
     */
    void setTitle(String title);
    /**
     * Returns the commenter
     * @return the commenter
     */
    String getCommenter();

    /**
     * set commenter of the post.
     */
    void setCommenter(String commenter);
}
