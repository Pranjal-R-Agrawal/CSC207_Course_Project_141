package view;

import use_case.create_post.interface_adapter.CreatePostController;
import use_case.create_post.interface_adapter.CreatePostState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CreatePostView extends AbstractGridBagLayoutView implements PropertyChangeListener {
    public final String viewName;
    private final CreatePostViewModel createPostViewModel;
    private final JTextField titleInputField= new JTextField(20);;
    private final JTextArea bodyInputArea = createMultiLineText("", true);
    private final JTextField collaboratorRolesInputField = new JTextField(20);
    private final JButton postButton = new JButton();

    public CreatePostView(CreatePostViewModel createPostViewModel, ViewManagerModel viewManagerModel, CreatePostController createPostController){
        super(createPostViewModel.getViewName());
        this.createPostViewModel = createPostViewModel;

        viewName = createPostViewModel.getViewName();
        setName(viewName);
        postButton.setText(CreatePostViewModel.POST_BUTTON_LABEL);

        createPostViewModel.addPropertyChangeListener(this);

        JPanel bodyPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        initialiseConstraints(constraints);

        JPanel titlePanel = new JPanel(new GridBagLayout());
        setConstraintInset(constraints, 0, 0, 0, 5);
        setConstraintWeight(constraints, 0.1, 1);
        addComponent(constraints, titlePanel, new JLabel(CreatePostViewModel.TITLE_LABEL), 0, 0);
        setConstraintInset(constraints, 0, 0, 0, 0);
        setConstraintWeight(constraints, 1, 1);
        addComponent(constraints, titlePanel, titleInputField, GridBagConstraints.RELATIVE, 0);

        setConstraintInset(constraints, 0, 0, 0, 5);
        setConstraintWeight(constraints, 0.1, 1);
        addComponent(constraints, bodyPanel, new JLabel(CreatePostViewModel.BODY_LABEL), 0, 0);
        setConstraintInset(constraints, 0, 0, 0, 0);
        setConstraintWeight(constraints, 1, 1);
        addComponent(constraints, bodyPanel, bodyInputArea, GridBagConstraints.RELATIVE, 0);

        JPanel colabRolesPanel = new JPanel(new GridBagLayout());
        setConstraintInset(constraints, 0, 0, 0, 5);
        setConstraintWeight(constraints, 0.1, 1);
        addComponent(constraints, colabRolesPanel, new JLabel(CreatePostViewModel.COLLABORATOR_ROLES_LABEL), 0, 0);
        setConstraintInset(constraints, 0, 0, 0, 0);
        setConstraintWeight(constraints, 1, 1);
        addComponent(constraints, colabRolesPanel, collaboratorRolesInputField, GridBagConstraints.RELATIVE, 0);

        postButton.addActionListener(
                e -> {
                    if (e.getSource().equals(postButton)) {
                        CreatePostState postState = createPostViewModel.getState();
                        createPostController.execute(
                                postState.getTitle(),
                                postState.getBody(),
                                postState.getSuggestedCollaboratorRoles()
                        );
                    }
                }
        );

        titleInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                createPostViewModel.getState().setTitle(titleInputField.getText() + e.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        bodyInputArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                viewManagerModel.resize("create_post");
                createPostViewModel.getState().setBody(bodyInputArea.getText() + e.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        collaboratorRolesInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                createPostViewModel.getState().setSuggestedCollaboratorRoles(collaboratorRolesInputField.getText() + e.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        setLayout(new GridBagLayout());

        JPanel postButtonPanel = new JPanel(new GridBagLayout());
        setConstraintInset(constraints, 0, 0, 0, 0);
        addComponent(constraints, postButtonPanel, postButton, 0, 0);

        setConstraintInset(constraints, 0, 5, 0, 5);
        setConstraintWeight(constraints, 1, 1);
        addPanel(constraints, this, titlePanel, 0, 0);
        addPanel(constraints, this, bodyPanel, 0, GridBagConstraints.RELATIVE);
        addPanel(constraints, this, colabRolesPanel, 0, GridBagConstraints.RELATIVE);
        addPanel(constraints, this, postButtonPanel, 0, GridBagConstraints.RELATIVE);
        add(postButton, constraints);
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("post_error")){
            CreatePostState currentState = createPostViewModel.getState();
            JOptionPane.showMessageDialog(this, currentState.getErrorMessage());
        }
    }
    public JButton getPostButton(){return postButton;}
}
