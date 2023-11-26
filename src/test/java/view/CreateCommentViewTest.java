package view;

import app.CreateCommentUseCaseBuilder;
import data_access.MongoDBDataAccessObject;
import data_access.MongoDBDataAccessObjectBuilder;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CreateCommentViewTest {
    private static String message;
    private static boolean popUpDiscovered;
    private MongoDBDataAccessObject mongoDBDataAccessObject;
    private CreateCommentViewModel createCommentViewModel;
    private JButton createCommentButton;

    @Test
    public void testAllFieldsEmpty(){
        createCloseTimer().start();
        createCommentButton.doClick();
        assert message.equals("Please enter text in body.");
    }

    @Test
    public void testBodyEmpty(){
        createCommentViewModel.getState().setBody(null).setQualifications("test;test1");
        createCommentViewModel.firePropertyChanged("update_fields");
        createCloseTimer().start();
        createCommentButton.doClick();
        assert message.equals("Please enter text in body.");
    }

    @Test
    public void testBodySpaces(){
        createCommentViewModel.getState().setBody("       ").setQualifications("test;test1");
        createCommentViewModel.firePropertyChanged("update_fields");
        createCloseTimer().start();
        createCommentButton.doClick();
        assert message.equals("Please enter text in body.");
    }

    @Test
    public void testQualificationsEmpty(){
        createCommentViewModel.getState().setBody("body").setQualifications(null);
        createCommentViewModel.firePropertyChanged("update_fields");
        createCloseTimer().start();
        createCommentButton.doClick();
        assert message.equals("Please enter at least 1 qualification.");
    }

    @Test
    public void testQualificationsSpaces(){
        createCommentViewModel.getState().setBody("body").setQualifications("       ");
        createCommentViewModel.firePropertyChanged("update_fields");
        createCloseTimer().start();
        createCommentButton.doClick();
        assert message.equals("Please enter at least 1 qualification.");
    }

    @Test
    public void testValidInputs(){
        createCommentViewModel.getState().setBody("body").setQualifications("test;test1");
        createCommentViewModel.firePropertyChanged("update_fields");
        createCloseTimer().start();
        createCommentButton.doClick();
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

                        CreateCommentViewTest.message = s;
                        CreateCommentViewTest.popUpDiscovered = true;
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
        createCommentViewModel = new CreateCommentViewModel();
        CreateCommentView createCommentView = CreateCommentUseCaseBuilder.create(new PostAndCommentsViewModel(), createCommentViewModel, mongoDBDataAccessObject);

        createCommentButton = createCommentView.getCommentButton();

        message = "";
        popUpDiscovered = false;

        JFrame application = new JFrame("Startup Generator");
        application.add(createCommentView);
        application.pack();
        application.setVisible(true);
    }
}
