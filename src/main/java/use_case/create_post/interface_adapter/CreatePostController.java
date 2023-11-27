package use_case.create_post.interface_adapter;

import use_case.create_comment.application_business_rules.CreateCommentInputBoundary;
import use_case.create_post.application_business_rules.CreatePostInputBoundary;
import use_case.create_post.application_business_rules.CreatePostInputData;
import use_case.create_post.application_business_rules.CreatePostInteractor;

public class CreatePostController {
    CreatePostInputBoundary createPostInteractor;

    public CreatePostController(CreatePostInteractor createPostInteractor){
        this.createPostInteractor = createPostInteractor;
    }

    public void execute(String title, String body, String suggestedCollaboratorRequests){
        CreatePostInputData inputData = new CreatePostInputData(title, body, suggestedCollaboratorRequests);
        createPostInteractor.execute(inputData);
    }
}
