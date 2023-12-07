package view;

import api.MistralCodegenAIAPI;
import app.CreatePostUseCaseFactory;
import app.GenerateIdeaUseCaseFactory;
import data_access.IdeaDataFileDataAccessObject;
import data_access.MongoDBDataAccessObject;
import data_access.MongoDBDataAccessObjectBuilder;
import entity.ConcreteIdeaFactory;
import org.junit.Before;
import org.junit.Test;
import use_case.create_post.interface_adapter.CreatePostViewModel;
import use_case.generate_idea.interface_adapter.GenerateIdeaViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static org.junit.Assert.*;

public class GenerateIdeaViewTest {
    private static String message;
    private static GenerateIdeaView generateIdeaView;
    private IdeaDataFileDataAccessObject ideaDataFileDataAccessObject;
    private GenerateIdeaViewModel generateIdeaViewModel;
    private CreatePostViewModel createPostViewModel;
    private JButton ideaButton;

    //TODO: Write tests for back and post button once create_post branch has been merged
    @Before
    public void setUpTest() throws Exception {

        MongoDBDataAccessObject mongoDBDataAccessObject = new MongoDBDataAccessObjectBuilder().setTestParameters().build();

        ideaDataFileDataAccessObject = new IdeaDataFileDataAccessObject("src/main/java/data_access/ideas.csv",new ConcreteIdeaFactory());

        generateIdeaViewModel = new GenerateIdeaViewModel();
        createPostViewModel = new CreatePostViewModel();

        generateIdeaView = GenerateIdeaUseCaseFactory.create(new ViewManagerModel(),generateIdeaViewModel, createPostViewModel,ideaDataFileDataAccessObject, new MistralCodegenAIAPI(), new HomePageViewModel(), CreatePostUseCaseFactory.create(new ViewManagerModel(),createPostViewModel,mongoDBDataAccessObject));

        ideaButton = (JButton) generateIdeaView.getComponent(0);

        message = "";

        JFrame application = new JFrame("Startup Generator");
        application.add(generateIdeaView);
        application.pack();
        application.setVisible(true);
    }
    @Test
    public void testBusinessModelDisplayDynamic() {
        ideaButton.doClick();// this is for the GenerateIdeaViewModel to get updated with a business model from the API
        createCloseTimer().start();
        ideaButton.doClick(); // this is to access the text in the GenerateIdeaView display
        assertTrue(message instanceof String);
        assertNotEquals("", message);
    }
    @Test
    public void testUnableToProduceBusinessModel() {
        generateIdeaView.getGenerateIdeaController().execute(true);
        createCloseTimer().start();
        ideaButton.doClick(); // this is to access the text in the GenerateIdeaView display
        assertEquals("Unable to produce Business Model", message);
    }
    @Test
    public void testBusinessModelDisplayStatic() {
        generateIdeaViewModel.getState().setBusinessModel("3D printing");
        generateIdeaViewModel.firePropertyChanged("business_model_display");
        createCloseTimer().start();
        ideaButton.doClick(); // this is to access the text in the GenerateIdeaView display
        assertEquals("3D printing", message);
    }

    private Timer createCloseTimer() {
        ActionListener close = e -> {

            Window[] windows = Window.getWindows();
            System.out.println(windows);
            for (Window window : windows) {
                if (window instanceof JFrame jFrame) {
                    if (jFrame.isVisible()) {
                        String s = ((GenerateIdeaView) ((BorderLayout) jFrame.getRootPane()
                                .getContentPane().getLayout()).getLayoutComponent(BorderLayout.CENTER)).getGenerateIdeaViewModel().getState().getBusinessModel();

                        GenerateIdeaViewTest.message = s;
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


}
