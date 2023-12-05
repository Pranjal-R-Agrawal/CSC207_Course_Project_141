package use_case.create_post.application_business_rules;

import data_access.CreatePostDataAccessInterface;
import entity.Post;
import entity.ConcretePostFactory;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.List;

/**
 * A concrete implementation of the CreatePostInputBoundary which adds a post to the database
 * and passes the id of the post to be displayed to the user.
 * @author Yathusan Koneswararajah
 */
public class CreatePostInteractor implements CreatePostInputBoundary{
    CreatePostOutputBoundary createPostPresenter;
    CreatePostDataAccessInterface createPostDataAccessObject;
    ConcretePostFactory postFactory;

    /**
     *Initializes the CreatePostInteractor object.
     * @param createPostPresenter Recieves output data (id) to display the appropriate post.
     * @param createPostDataAccessObject An object that puts the created post into the database.
     * @param postFactory An object that generates a Post entity given the necessary information
     */
    public CreatePostInteractor(CreatePostOutputBoundary createPostPresenter, CreatePostDataAccessInterface createPostDataAccessObject, ConcretePostFactory postFactory){
        this.createPostPresenter = createPostPresenter;
        this.createPostDataAccessObject = createPostDataAccessObject;
        this.postFactory = postFactory;
    }

    /**
     * Creates a Post entity based on the InputData and puts it into the database
     * and passes the created post's id to the presenter or if inputs are invalid, it sends an
     * error to be displayed by the Presenter.
     * @param createPostInputData A data class that contains the inputs for the post
     */
    public void execute(CreatePostInputData createPostInputData){
        if (createPostInputData.getTitle() == null || createPostInputData.getTitle().trim().isEmpty()){
            createPostPresenter.prepareFailureView("Please enter a title.");
        } else if (createPostInputData.getBody() == null || createPostInputData.getBody().trim().isEmpty()) {
            createPostPresenter.prepareFailureView("Please enter text in body.");
        } else if (createPostInputData.getSuggestedCollaboratorQualifications() == null || createPostInputData.getSuggestedCollaboratorQualifications().trim().isEmpty()) {
            createPostPresenter.prepareFailureView("Please at least 1 suggested collaborator qualification.");
        } else{
            List<String> suggestedCollaboratorQualifications = Arrays.asList(createPostInputData.getSuggestedCollaboratorQualifications().split(";"));
            suggestedCollaboratorQualifications.replaceAll(String::trim);
            suggestedCollaboratorQualifications.removeIf(String::isEmpty);
            ObjectId authorId = createPostDataAccessObject.getLoggedInUserId();
            Post post = postFactory.create(authorId, createPostInputData.getTitle(), createPostInputData.getBody(), suggestedCollaboratorQualifications);
            createPostDataAccessObject.addPost(post);
            createPostPresenter.prepareSuccessView(post.getId());

        }
    }
}
