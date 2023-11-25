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
    private final JTextArea bodyInputArea = new JTextArea();
    private final JTextField qualificationInputField = new JTextField(20);
    private final JButton commentButton = new JButton();

    public CreateCommentView(CreateCommentViewModel createCommentViewModel, DisplayCommentsViewModel displayCommentsViewModel, CreateCommentController createCommentController){
        this.createCommentViewModel = createCommentViewModel;

        viewName = createCommentViewModel.getViewName();
        setName(viewName);
        commentButton.setText(CreateCommentViewModel.COMMENT_BUTTON_LABEL);

        createCommentViewModel.addPropertyChangeListener(this);

        JPanel bodyPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(0, 0, 0, 5); constraints.weightx = 0.1;
        addComponent(bodyPanel, new JLabel(CreateCommentViewModel.BODY_LABEL), constraints, 0, 0, GridBagConstraints.HORIZONTAL);
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.weightx = 1;
        bodyInputArea.setLineWrap(true);
        addComponent(bodyPanel, bodyInputArea, constraints, GridBagConstraints.RELATIVE, 0, GridBagConstraints.HORIZONTAL);

        JPanel qualificationPanel = new JPanel(new GridBagLayout());
        constraints.insets = new Insets(0, 0, 0, 5);
        constraints.weightx = 0.1;
        addComponent(qualificationPanel, new JLabel(CreateCommentViewModel.QUALIFICATIONS_LABEL), constraints, 0, 0, GridBagConstraints.HORIZONTAL);
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.weightx = 1;
        addComponent(qualificationPanel, qualificationInputField, constraints, GridBagConstraints.RELATIVE, 0, GridBagConstraints.HORIZONTAL);

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
                displayCommentsViewModel.firePropertyChanged("resize_reply_frame");
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
        constraints.insets = new Insets(0, 0, 0, 0);
        addComponent(commentButtonPanel, commentButton, constraints, 0, 0, GridBagConstraints.HORIZONTAL);

        constraints.insets = new Insets(0, 5, 0, 5);
        constraints.weightx = 1; constraints.weighty = 1;
        addPanel(this, bodyPanel, constraints, 0, 0, GridBagConstraints.HORIZONTAL);
        addPanel(this, qualificationPanel, constraints, 0, GridBagConstraints.RELATIVE, GridBagConstraints.HORIZONTAL);
        addPanel(this, commentButtonPanel, constraints, 0, GridBagConstraints.RELATIVE, GridBagConstraints.HORIZONTAL);
        add(commentButton, constraints);
    }

    private void addPanel(JPanel parent, JPanel panel, GridBagConstraints c, int gridx, int gridy, int fill) {
        c.gridx = gridx;
        c.gridy = gridy;
        c.fill = fill;
        parent.add(panel, c);
    }

    private void addComponent(JPanel panel, JComponent component, GridBagConstraints c, int gridx, int gridy, int fill) {
        c.gridx = gridx;
        c.gridy = gridy;
        c.fill = fill;
        panel.add(component, c);
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
}
