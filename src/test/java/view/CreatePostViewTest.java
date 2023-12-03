package view;

import app.CreateCommentUseCaseBuilder;
import app.CreatePostUseCaseFactory;
import data_access.MongoDBDataAccessObject;
import data_access.MongoDBDataAccessObjectBuilder;
import org.junit.Before;
import org.junit.Test;
import view.display_post.PostAndCommentsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CreatePostViewTest {
    private static String message;
    private static boolean popUpDiscovered;
    private MongoDBDataAccessObject mongoDBDataAccessObject;
    private CreatePostViewModel createPostViewModel;
    private JButton postButton;
    @Test
    public void testAllFieldsEmpty(){
        createCloseTimer().start();
        postButton.doClick();
        assert message.equals("Please enter a title.");
    }

    @Test
    public void testTitleEmpty(){
        createPostViewModel.getState().setTitle(null).setBody("test").setSuggestedCollaboratorRoles("test;test1");
        createPostViewModel.firePropertyChanged("update_fields");
        createCloseTimer().start();
        postButton.doClick();
        assert message.equals("Please enter a title.");
    }

    @Test
    public void testBodyEmpty(){
        createPostViewModel.getState().setTitle("title").setBody("").setSuggestedCollaboratorRoles("test;test1");
        createPostViewModel.firePropertyChanged("update_fields");
        createCloseTimer().start();
        postButton.doClick();
        assert message.equals("Please enter text in body.");
    }

    @Test
    public void testSuggestedCollabRolesEmpty(){
        createPostViewModel.getState().setTitle("title").setBody("body").setSuggestedCollaboratorRoles(null);
        createPostViewModel.firePropertyChanged("update_fields");
        createCloseTimer().start();
        postButton.doClick();
        assert message.equals("Please at least 1 suggested collaborator qualification.");
    }

    @Test
    public void testValidInputs(){
        createPostViewModel.getState().setTitle("title").setBody("body").setSuggestedCollaboratorRoles("test;test1");
        createPostViewModel.firePropertyChanged("update_fields");
        createCloseTimer().start();
        postButton.doClick();
        assert !popUpDiscovered;
    }
    private Timer createCloseTimer() {
        ActionListener close = e -> {
            Window[] windows = Window.getWindows();
            for (Window window : windows) {
                if (window instanceof JDialog dialog) {
                    if (dialog.isVisible()) {
                        String s = ((JOptionPane) ((BorderLayout) dialog.getRootPane()
                                .getContentPane().getLayout()).getLayoutComponent(BorderLayout.CENTER)).getMessage().toString();

                        System.out.println("message = " + s);

                        CreatePostViewTest.message = s;
                        CreatePostViewTest.popUpDiscovered = true;
                    }
                }
            }
            for (Window window : windows) {
                window.dispose();
            }
        };

        Timer t = new Timer(1000, close);
        t.setRepeats(false);
        return t;
    }

    @Before
    public void setUpTest(){
        try {
            mongoDBDataAccessObject = new MongoDBDataAccessObjectBuilder().setTestParameters().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        createPostViewModel = new CreatePostViewModel();
        CreatePostView createPostView = CreatePostUseCaseFactory.create(new ViewManagerModel(), createPostViewModel, mongoDBDataAccessObject);

        postButton = createPostView.getPostButton();

        message = "";
        popUpDiscovered = false;

        JFrame application = new JFrame("Startup Generator");
        application.add(createPostView);
        application.pack();
        application.setVisible(true);
    }
}
