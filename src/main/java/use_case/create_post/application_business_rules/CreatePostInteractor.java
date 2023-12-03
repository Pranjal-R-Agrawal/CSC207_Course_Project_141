package use_case.create_post.application_business_rules;

import data_access.CreatePostDataAccessInterface;
import entity.Post;
import entity.PostFactory;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.List;

public class CreatePostInteractor implements CreatePostInputBoundary{
    CreatePostOutputBoundary createPostPresenter;
    CreatePostDataAccessInterface createPostDataAccessObject;
    PostFactory postFactory;

    public CreatePostInteractor(CreatePostOutputBoundary createPostPresenter, CreatePostDataAccessInterface createPostDataAccessObject, PostFactory postFactory){
        this.createPostPresenter = createPostPresenter;
        this.createPostDataAccessObject = createPostDataAccessObject;
        this.postFactory = postFactory;
    }

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
