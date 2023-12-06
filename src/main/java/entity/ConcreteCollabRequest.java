package entity;

import org.bson.types.ObjectId;
/**
 * Represents a CollabRequest entity
 */
public class ConcreteCollabRequest implements CollabRequest {
    private ObjectId postId;
    private String commenter;
    private String author;
    private String title;
    /**
     * Constructor for CollabRequest prevents instantiation
     */
    public ConcreteCollabRequest() {}
    /**
     * Constructor for CollabRequest
     * @param postId the id of the post
     * @param commenter the commenter
     * @param author the author
     * @param title the title
     */
    public ConcreteCollabRequest(ObjectId postId, String commenter, String author, String title) {
        this.postId = postId;
        this.commenter = commenter;
        this.author = author;
        this.title = title;
    }
    /**
     * Returns the id of the Post.
     * @return the id of the Post
     */
    @Override
    public ObjectId getPostId() {
        return postId;
    }
    /**
     * set id of the post.
     */
    @Override
    public void setPostId(ObjectId postId) {
        this.postId =  postId;
    }
    /**
     * Returns the author from the database
     * @return the author string username
     */
    @Override
    public String getAuthor() {
        return author;
    }
    /**
     * set author of the post.
     */
    @Override
    public void setAuthor(String author) {
        this.author = author;
    }
    /**
     * Returns the title of the Post.
     * @return the title of the Post
     */
    @Override
    public String getTitle() {
        return title;
    }
    /**
     * set title of the post.
     */
    @Override
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Returns the commenter
     * @return the commenter
     */
    @Override
    public String getCommenter() {
        return commenter;
    }
    /**
     * set commenter of the post.
     */
    @Override
    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

}
