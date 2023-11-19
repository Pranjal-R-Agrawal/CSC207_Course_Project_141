package view;

import entity.Comment;
import use_case.comment.interface_adapter.CommentController;
import use_case.comment.interface_adapter.CommentState;
import use_case.login.interface_adapter.LoginState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CommentView extends JPanel implements PropertyChangeListener {
    public final String viewName;
    private final CommentViewModel commentViewModel;
    private final JTextArea bodyInputArea = new JTextArea(5, 20);
    private final JTextField qualificationInputField = new JTextField(20);
    private final CommentController commentController;
    private final JButton commentButton = new JButton();
    public CommentView(CommentViewModel commentViewModel, CommentController commentController){
        this.commentViewModel = commentViewModel;
        this.commentController = commentController;

        viewName = commentViewModel.getViewName();
        setName(viewName);
        commentButton.setText(CommentViewModel.COMMENT_BUTTON_LABEL);

        commentViewModel.addPropertyChangeListener(this);

        JPanel bodyPanel = new JPanel();
        bodyPanel.add(new JLabel(CommentViewModel.BODY_LABEL));
        bodyPanel.add(bodyInputArea);

        JPanel qualificationPanel = new JPanel();
        qualificationPanel.add(new JLabel(CommentViewModel.QUALIFICATIONS_LABEL));
        qualificationPanel.add(qualificationInputField);

        commentButton.addActionListener(
                e -> {
                    if (e.getSource().equals(commentButton)) {
                        CommentState currentState = commentViewModel.getState();
                        commentController.execute(
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
                commentViewModel.getState().setBody(bodyInputArea.getText() + e.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        qualificationInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
               commentViewModel.getState().setQualifications(qualificationInputField.getText() + e.getKeyChar());
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
            CommentState currentState = commentViewModel.getState();
            JOptionPane.showMessageDialog(this, currentState.getErrorMessage());
        } else if (evt.getPropertyName().equals("reset_input_fields")) {
            bodyInputArea.setText("");
            qualificationInputField.setText("");
        }
    }
}
