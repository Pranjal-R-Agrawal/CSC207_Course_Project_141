package view.display_post;

import org.bson.types.ObjectId;
import view.AbstractGridBagLayoutView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostView extends AbstractGridBagLayoutView {
    final ObjectId id;
    final ObjectId authorId;
    final String username;
    final String title;
    final String body;
    final List<String> qualifications;

    public PostView(Map<String, Object> post, PostAndCommentsViewModel postAndCommentsViewModel) {
        super("Post" + (ObjectId) post.get("id"));

        this.id = (ObjectId) post.get("id");
        this.authorId = (ObjectId) post.get("authorId");
        this.username = (String) post.get("username");
        this.title = (String) post.get("title");
        this.body = (String) post.get("body");
        this.qualifications = (post.get("suggested_collaborator_qualifications") != null)? (ArrayList<String>) post.get("suggested_collaborator_qualifications") : new ArrayList<String>();
        this.qualifications.add(0, "Suggested collaborator qualifications:");

        boolean qualificationsVisibility = qualifications.size() > 1;
        boolean viewAuthorInformationButtonVisibility = (boolean) post.get("logged_in_user_is_post_author") || (boolean) post.get("logged_in_user_is_collaborator");

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

        JTextArea titleArea = createMultiLineText("Title: " + title, false);
        titleArea.setCaretColor(titleArea.getBackground());

        initialiseConstraints(constraints);
        addComponent(constraints, titleRow, usernameLabel, 0, 0);
        setConstraintInset(constraints, 3, 0, 0, 0);

        addComponent(constraints, titleRow, titleArea, 0, GridBagConstraints.RELATIVE);

        initialiseConstraints(constraints);
        addComponent(constraints, bodyRow, createMultiLineText(body, false), 0, 0);

        initialiseConstraints(constraints);
        JTextArea qualificationsArea = createMultiLineText(String.join(System.lineSeparator(), this.qualifications), false);
        addComponent(constraints, qualificationsRow, qualificationsArea, 0, 0);

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
        addComponent(constraints, buttonRow, commentButton, 0, 0);
        setConstraintWeight(constraints, 1, 1);
        setConstraintInset(constraints, 0, 3, 0, 0);
        addComponent(constraints, buttonRow, viewAuthorInformationButton, GridBagConstraints.RELATIVE, 0);

        viewAuthorInformationButton.setVisible(viewAuthorInformationButtonVisibility);

        initialiseConstraints(constraints);
        setConstraintWeight(constraints, 1, 0.1);
        setConstraintInset(constraints, 3, 5, 3, 5);
        addPanel(constraints, this, titleRow, 0, 0);
        setConstraintWeight(constraints, 1, 2);
        setConstraintInset(constraints, 0, 5, 3, 5);
        addPanel(constraints, this, bodyRow, 0, GridBagConstraints.RELATIVE);
        setConstraintWeight(constraints, 1, 0.1);
        addPanel(constraints, this, qualificationsRow, 0, GridBagConstraints.RELATIVE);
        setConstraintWeight(constraints, 1, 0.1);
        setConstraintInset(constraints, 0, 3, 3, 3);
        addPanel(constraints, this, buttonRow, 0, GridBagConstraints.RELATIVE);

        qualificationsRow.setVisible(qualificationsVisibility);
    }
}
