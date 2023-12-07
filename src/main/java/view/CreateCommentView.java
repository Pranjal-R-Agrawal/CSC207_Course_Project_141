package view;

import use_case.create_comment.interface_adapter.CreateCommentController;
import use_case.create_comment.interface_adapter.CreateCommentState;
import use_case.create_comment.interface_adapter.CreateCommentViewModel;
import use_case.display_post.interface_adapter.PostAndCommentsViewModel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CreateCommentView extends AbstractGridBagLayoutView implements PropertyChangeListener {
    public final String viewName;
    private final CreateCommentViewModel createCommentViewModel;
    private final JTextArea bodyInputArea = createMultiLineText("", true);
    private final JTextField qualificationInputField = new JTextField(20);
    private final JButton commentButton = new JButton();

    public CreateCommentView(CreateCommentViewModel createCommentViewModel, PostAndCommentsViewModel postAndCommentsViewModel, CreateCommentController createCommentController){
        super(createCommentViewModel.getViewName());
        this.createCommentViewModel = createCommentViewModel;

        viewName = createCommentViewModel.getViewName();
        setName(viewName);
        commentButton.setText(CreateCommentViewModel.COMMENT_BUTTON_LABEL);

        createCommentViewModel.addPropertyChangeListener(this);

        JPanel bodyPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        initialiseConstraints(constraints);

        setConstraintInset(constraints, 0, 0, 0, 5);
        setConstraintWeight(constraints, 0.1, 1);
        addComponent(constraints, bodyPanel, new JLabel(CreateCommentViewModel.BODY_LABEL), 0, 0);
        setConstraintInset(constraints, 0, 0, 0, 0);
        setConstraintWeight(constraints, 1, 1);
        addComponent(constraints, bodyPanel, bodyInputArea, GridBagConstraints.RELATIVE, 0);

        JPanel qualificationPanel = new JPanel(new GridBagLayout());
        setConstraintInset(constraints, 0, 0, 0, 5);
        setConstraintWeight(constraints, 0.1, 1);
        addComponent(constraints, qualificationPanel, new JLabel(CreateCommentViewModel.QUALIFICATIONS_LABEL), 0, 0);
        setConstraintInset(constraints, 0, 0, 0, 0);
        setConstraintWeight(constraints, 1, 1);
        addComponent(constraints, qualificationPanel, qualificationInputField, GridBagConstraints.RELATIVE, 0);

        commentButton.addActionListener(
                e -> {
                    if (e.getSource().equals(commentButton)) {
                        CreateCommentState currentState = createCommentViewModel.getState();
                        createCommentController.execute(
                                currentState.getParentId(),
                                currentState.getParentPostId(),
                                currentState.getBody(),
                                currentState.getQualifications()
                        );
                    }
                }
        );

        bodyInputArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                postAndCommentsViewModel.firePropertyChanged("resize_reply_frame");
                createCommentViewModel.getState().setBody(bodyInputArea.getText() + e.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        qualificationInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
               createCommentViewModel.getState().setQualifications(qualificationInputField.getText() + e.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        setLayout(new GridBagLayout());

        JPanel commentButtonPanel = new JPanel(new GridBagLayout());
        setConstraintInset(constraints, 0, 0, 0, 0);
        addComponent(constraints, commentButtonPanel, commentButton, 0, 0);

        setConstraintInset(constraints, 0, 5, 0, 5);
        setConstraintWeight(constraints, 1, 1);
        addPanel(constraints, this, bodyPanel, 0, 0);
        addPanel(constraints, this, qualificationPanel, 0, GridBagConstraints.RELATIVE);
        addPanel(constraints, this, commentButtonPanel, 0, GridBagConstraints.RELATIVE);
        add(commentButton, constraints);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("comment_error")){
            CreateCommentState currentState = createCommentViewModel.getState();
            JOptionPane.showMessageDialog(this, currentState.getErrorMessage());
        } else if (evt.getPropertyName().equals("reset_input_fields")) {
            bodyInputArea.setText("");
            qualificationInputField.setText("");
        } else if (evt.getPropertyName().equals("update_fields")) {
            bodyInputArea.setText(createCommentViewModel.getState().getBody());
            qualificationInputField.setText(createCommentViewModel.getState().getQualifications());
        }
    }

    public JButton getCommentButton() {
        return commentButton;
    }
}
