package use_case.create_comment;

import data_access.MongoDBDataAccessObject;
import data_access.MongoDBDataAccessObjectBuilder;
import entity.CommentFactory;
import org.junit.Before;
import org.junit.Test;
import use_case.create_comment.application_business_rules.CreateCommentInteractor;
import use_case.create_comment.interface_adapter.CreateCommentController;
import use_case.create_comment.interface_adapter.CreateCommentPresenter;
import view.CreateCommentViewModel;
import view.ViewManagerModel;

public class CreateCommentUseCaseTest {
    CreateCommentViewModel createCommentViewModel;
    MongoDBDataAccessObject mongoDBDataAccessObject;
    CreateCommentController createCommentController;

    @Before
    public void setUpTest(){
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        createCommentViewModel = new CreateCommentViewModel();
        try {
            mongoDBDataAccessObject = new MongoDBDataAccessObjectBuilder().setTestParameters().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        createCommentController = new CreateCommentController(
                new CreateCommentInteractor(new CreateCommentPresenter(viewManagerModel, createCommentViewModel), mongoDBDataAccessObject, new CommentFactory())
        );

        mongoDBDataAccessObject.resetDatabase();
    }

    @Test
    public void testAllFieldsEmpty(){

        createCommentController.execute(null, null, null, null);
        assert createCommentViewModel.getState().getErrorMessage().equals("Please enter text in body.");
    }

    @Test
    public void testBodyEmpty(){
        createCommentController.execute(null, null, null, "test");
        assert createCommentViewModel.getState().getErrorMessage().equals("Please enter text in body.");
    }

    @Test
    public void testQualificationsEmpty(){
        createCommentController.execute(null, null, "body", null);
        assert createCommentViewModel.getState().getErrorMessage().equals("Please enter at least 1 qualification.");
    }

    @Test
    public void testValidInputs(){
        createCommentController.execute(null, null, "body", "qualification");
        assert createCommentViewModel.getState().getErrorMessage() == null;
    }
}

