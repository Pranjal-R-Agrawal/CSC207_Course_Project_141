package view;

import use_case.create_comment.interface_adapter.CreateCommentController;
import use_case.create_comment.interface_adapter.CreateCommentState;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CreateCommentView extends JPanel implements PropertyChangeListener {
    public final String viewName;
    private final CreateCommentViewModel createCommentViewModel;
    private final JTextArea bodyInputArea = new JTextArea(5, 20);
    private final JTextField qualificationInputField = new JTextField(20);
    private final CreateCommentController createCommentController;
    private final JButton commentButton = new JButton();
    public CreateCommentView(CreateCommentViewModel createCommentViewModel, CreateCommentController createCommentController){
        this.createCommentViewModel = createCommentViewModel;
        this.createCommentController = createCommentController;

        viewName = createCommentViewModel.getViewName();
        setName(viewName);
        commentButton.setText(CreateCommentViewModel.COMMENT_BUTTON_LABEL);

        createCommentViewModel.addPropertyChangeListener(this);

        JPanel bodyPanel = new JPanel();
        bodyPanel.add(new JLabel(CreateCommentViewModel.BODY_LABEL));
        bodyPanel.add(bodyInputArea);

        JPanel qualificationPanel = new JPanel();
        qualificationPanel.add(new JLabel(CreateCommentViewModel.QUALIFICATIONS_LABEL));
        qualificationPanel.add(qualificationInputField);

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
        GridBagConstraints constraints = new GridBagConstraints();

        addComponent(bodyPanel, constraints, 0, 0, GridBagConstraints.HORIZONTAL);
        addComponent(qualificationPanel, constraints, 0, GridBagConstraints.RELATIVE, GridBagConstraints.HORIZONTAL);
        add(commentButton, constraints);

    }

    private void addComponent(JPanel panel, GridBagConstraints c, int gridx, int gridy, int fill) {
        c.gridx = gridx;
        c.gridy = gridy;
        c.fill = fill;
        add(panel, c);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("comment_error")){
            CreateCommentState currentState = createCommentViewModel.getState();
            JOptionPane.showMessageDialog(this, currentState.getErrorMessage());
        } else if (evt.getPropertyName().equals("reset_input_fields")) {
            bodyInputArea.setText("");
            qualificationInputField.setText("");
        }
    }
}
