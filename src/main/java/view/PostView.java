package view;

import org.bson.types.ObjectId;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostView extends JPanel {
    final ObjectId id;
    final ObjectId authorId;
    final String username;
    final String title;
    final String body;
    final List<String> qualifications;

    public PostView(Map<String, Object> post, PostAndCommentsViewModel postAndCommentsViewModel) {
        this.id = (ObjectId) post.get("id");
        this.authorId = (ObjectId) post.get("authorId");
        this.username = (String) post.get("username");
        this.title = (String) post.get("title");
        this.body = (String) post.get("body");
        this.qualifications = (post.get("suggested_collaborator_qualifications") != null)? (ArrayList<String>) post.get("suggested_collaborator_qualifications") : new ArrayList<String>();
        this.qualifications.add(0, "Suggested collaborator qualifications:");

        boolean qualificationsVisibility = qualifications.size() > 1;
        boolean viewAuthorInformationButtonVisibility = (post.get("show_more_info_button") != null) && (boolean) post.get("show_more_info_button");

        setLayout(new GridBagLayout());

        JPanel titleRow = new JPanel(new GridBagLayout());
        JPanel bodyRow = new JPanel(new GridBagLayout());
        JPanel qualificationsRow = new JPanel(new GridBagLayout());
        JPanel buttonRow = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        JLabel usernameLabel = new JLabel("Poster: " + username);
        if (post.get("logged_in_user_is_post_author") != null && (boolean) post.get("logged_in_user_is_post_author")) {
            usernameLabel.setForeground(Color.RED);
        }

        JTextArea titleArea = createMultiLineText("Title: " + title);
        titleArea.setCaretColor(titleArea.getBackground());

        initialiseConstraints(constraints);
        addComponent(constraints, titleRow, usernameLabel, 0, 0, 1, 1);
        setConstraintInset(constraints, 3, 0, 0, 0);

        addComponent(constraints, titleRow, titleArea, 0, GridBagConstraints.RELATIVE, 1, 1);

        initialiseConstraints(constraints);
        addComponent(constraints, bodyRow, createMultiLineText(body), 0, 0, 1, 1);

        initialiseConstraints(constraints);
        JTextArea qualificationsArea = createMultiLineText(String.join(System.lineSeparator(), this.qualifications));
        addComponent(constraints, qualificationsRow, qualificationsArea, 0, 0, 1, 1);

        JButton commentButton = new JButton("Comment");
        commentButton.addActionListener(e -> {
            postAndCommentsViewModel.getState().setReplyParentId(id).setReplyParentPostId(id);
            postAndCommentsViewModel.firePropertyChanged("reply_to_comment");
        });

        JButton viewAuthorInformationButton = new JButton("View Poster's Information");
        viewAuthorInformationButton.addActionListener(e -> {
            // TODO: implement similar to comment button
        });

        initialiseConstraints(constraints);
        setConstraintWeight(constraints, 1.3, 1);
        addComponent(constraints, buttonRow, commentButton, 0, 0, 1, 1);
        setConstraintWeight(constraints, 1, 1);
        setConstraintInset(constraints, 0, 3, 0, 0);
        addComponent(constraints, buttonRow, viewAuthorInformationButton, GridBagConstraints.RELATIVE, 0, 1, 1);

        viewAuthorInformationButton.setVisible(viewAuthorInformationButtonVisibility);

        initialiseConstraints(constraints);
        setConstraintWeight(constraints, 1, 0.1);
        setConstraintInset(constraints, 3, 5, 3, 5);
        addPanel(constraints, this, titleRow, 0, 0, 1, 1);
        setConstraintWeight(constraints, 1, 2);
        setConstraintInset(constraints, 0, 5, 3, 5);
        addPanel(constraints, this, bodyRow, 0, GridBagConstraints.RELATIVE, 1, 1);
        setConstraintWeight(constraints, 1, 0.1);
        addPanel(constraints, this, qualificationsRow, 0, GridBagConstraints.RELATIVE, 1, 1);
        setConstraintWeight(constraints, 1, 0.1);
        setConstraintInset(constraints, 0, 3, 3, 3);
        addPanel(constraints, this, buttonRow, 0, GridBagConstraints.RELATIVE, 1, 1);

        qualificationsRow.setVisible(qualificationsVisibility);
    }

    private void addComponent(GridBagConstraints constraints, JPanel panel, JComponent component, int x, int y, int width, int height) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        panel.add(component, constraints);
    }

    private void addPanel(GridBagConstraints constraints, JPanel parent, JPanel panel, int x, int y, int width, int height) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        parent.add(panel, constraints);
    }

    private void initialiseConstraints(GridBagConstraints constraints) {
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(0, 0, 0, 0);
    }

    private void setConstraintWeight(GridBagConstraints constraints, double weightx, double weighty) {
        constraints.weightx = weightx;
        constraints.weighty = weighty;
    }

    private void setConstraintInset(GridBagConstraints constraints, int top, int left, int bottom, int right) {
        constraints.insets = new Insets(top, left, bottom, right);
    }

    private JTextArea createMultiLineText(String text) {
        JTextArea textArea = new JTextArea(text);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setCaretColor(textArea.getBackground());
        textArea.setBackground(this.getBackground());
        textArea.setWrapStyleWord(true);
        return textArea;
    }
}
