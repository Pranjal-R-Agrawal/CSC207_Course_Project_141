package view;

import org.bson.types.ObjectId;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentView extends AbstractGridBagLayoutView {
    final ObjectId id;
    final ObjectId parentId;
    final ObjectId parentPostId;
    final ObjectId authorId;
    final String username;
    final String body;
    final List<String> qualifications;
    private final JPanel commentContainerVertical = new JPanel(new GridBagLayout());
    private final JPanel commentContainerHorizontal = new JPanel(new GridBagLayout());
    private final JPanel children = new JPanel(new GridBagLayout());
    private final JButton collapseButton;
    private int numChildren = 0;
    private boolean qualificationsVisibility = false;
    private boolean childrenVisibility = false;

    /**
     * This class is responsible for displaying a comment.
     * @param comment The map containing the information of the comment to display
     * @param postAndCommentsViewModel The view model for this view
     */
    public CommentView(Map<String, Object> comment, PostAndCommentsViewModel postAndCommentsViewModel) {
        super("Comment" + (ObjectId) comment.get("id"));

        this.id = (ObjectId) comment.get("id");
        this.parentId = (ObjectId) comment.get("parentId");
        this.parentPostId = (ObjectId) comment.get("parentPostId");
        this.authorId = (ObjectId) comment.get("authorId");
        this.username = (String) comment.get("username");
        this.body = (String) comment.get("body");
        this.qualifications = (comment.get("qualifications") != null)? (ArrayList<String>) comment.get("qualifications") : new ArrayList<String>();
        boolean moreInfo = comment.get("show_more_info_button") != null && (boolean) comment.get("show_more_info_button");

        GridBagConstraints constraints = new GridBagConstraints();

        initialiseConstraints(constraints);

        JPanel informationRow = new JPanel(new GridBagLayout());
        JPanel commentRow = new JPanel(new GridBagLayout());
        JPanel buttonRow = new JPanel(new GridBagLayout());
        JPanel qualificationsRow = new JPanel(new GridBagLayout());

        collapseButton = new JButton("Expand");
        collapseButton.setVisible(false);
        collapseButton.addActionListener(e -> {
            if (numChildren > 0) {
                childrenVisibility = !childrenVisibility;
                children.setVisible(childrenVisibility);
            }
            if (childrenVisibility) collapseButton.setText("Collapse");
            else collapseButton.setText("Expand");
        });

        JLabel usernameLabel = new JLabel(username);
        if (comment.get("comment_author_is_post_author") != null && (boolean) comment.get("comment_author_is_post_author")) {
            usernameLabel.setForeground(Color.BLUE);
        } if (comment.get("logged_in_user_is_comment_author") != null && (boolean) comment.get("logged_in_user_is_comment_author")) {
            usernameLabel.setForeground(Color.RED);
        }

        initialiseConstraints(constraints);
        setConstraintInset(constraints, 2, 0, 2, 0);
        setConstraintWeight(constraints, 0.1, 1);
        addComponent(constraints, informationRow, collapseButton, 0, 0);
        setConstraintWeight(constraints, 10, 1);
        addComponent(constraints, informationRow, usernameLabel, GridBagConstraints.RELATIVE, 0);

        initialiseConstraints(constraints);
        setConstraintWeight(constraints, 1, 0.1);
        addPanel(constraints, this, informationRow, 0, GridBagConstraints.RELATIVE);

        initialiseConstraints(constraints);
        setConstraintInset(constraints, 2, 7, 2, 7);
        addComponent(constraints, commentRow, createMultiLineText(body, false), 0, 0);

        initialiseConstraints(constraints);
        setConstraintInset(constraints, 2, 0, 2, 0);
        setConstraintWeight(constraints, 1, 2);
        addPanel(constraints, this, commentRow, 0, GridBagConstraints.RELATIVE);

        JButton replyButton = new JButton("Reply");
        replyButton.addActionListener(e -> {
            postAndCommentsViewModel.getState().setReplyParentId(id).setReplyParentPostId(parentPostId);
            postAndCommentsViewModel.firePropertyChanged("reply_to_comment");
        });

        JButton qualificationsButton = new JButton("Qualifications");
        qualificationsButton.addActionListener(e -> {
            qualificationsVisibility = !qualificationsVisibility;
            qualificationsRow.setVisible(qualificationsVisibility);
        });

        JButton viewMoreInfoButton = new JButton("More Info");
        viewMoreInfoButton.addActionListener(e -> {
            postAndCommentsViewModel.getState().setShowMoreInfoOfID(authorId).setMoreInfo(moreInfo);
            postAndCommentsViewModel.firePropertyChanged("show_more_info");
        });

        JButton collaborationRequestButton = new JButton("Request Collaboration");
        collaborationRequestButton.addActionListener(e -> {
            // TODO: Implement similar to reply button
        });

        initialiseConstraints(constraints);
        setConstraintWeight(constraints, 1, 1);
        addComponent(constraints, buttonRow, replyButton, 0, 0);
        addComponent(constraints, buttonRow, qualificationsButton, GridBagConstraints.RELATIVE, 0);
        addComponent(constraints, buttonRow, viewMoreInfoButton, GridBagConstraints.RELATIVE, 0);
        addComponent(constraints, buttonRow, collaborationRequestButton, GridBagConstraints.RELATIVE, 0);

        initialiseConstraints(constraints);
        setConstraintWeight(constraints, 1, 0.1);
        addPanel(constraints, this, buttonRow, 0, GridBagConstraints.RELATIVE);

        initialiseConstraints(constraints);
        setConstraintInset(constraints, 2, 7, 3, 7);
        setConstraintWeight(constraints, 1, 0.45);
        addComponent(constraints, qualificationsRow, createMultiLineText(String.join(System.lineSeparator(), qualifications), false), 0, 0);

        initialiseConstraints(constraints);
        setConstraintInset(constraints, 2, 0, 2, 0);
        setConstraintWeight(constraints, 1, 0.3);
        addPanel(constraints, this, qualificationsRow, 0, GridBagConstraints.RELATIVE);

        qualificationsButton.setVisible(!qualifications.isEmpty());
        collaborationRequestButton.setVisible(comment.get("logged_in_user_is_post_author") != null && (boolean) comment.get("logged_in_user_is_post_author"));
//        viewMoreInfoButton.setVisible(comment.get("show_more_info_button") != null && (boolean) comment.get("show_more_info_button"));
        qualificationsRow.setVisible(qualificationsVisibility);

        JSeparator verticalSeparator = new JSeparator();
        verticalSeparator.setOrientation(SwingConstants.VERTICAL);

        addComponent(constraints, commentContainerHorizontal, verticalSeparator, 0, 0);
        addPanel(constraints, commentContainerHorizontal, commentContainerVertical, 1, 0);

        addPanel(constraints, commentContainerVertical, this, 0, 0);
        addPanel(constraints, commentContainerVertical, children, 0, 1);

        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));

        children.setVisible(childrenVisibility);
    }

    /**
     * The comment view instance is actually in a container, so this function returns the container.
     * @return The JPanel containing the comment
     */
    public Container getComment() {
        return commentContainerHorizontal;
    }

    private void addChild(Container child) {
        GridBagConstraints constraints = new GridBagConstraints();
        initialiseConstraints(constraints);
        setConstraintWeight(constraints, 1, 0.1);
        addComponent(constraints, children, new JLabel("     "), 0, GridBagConstraints.RELATIVE);
        setConstraintWeight(constraints, 1, 1);
        addComponent(constraints, children, (JComponent) child, 1, GridBagConstraints.RELATIVE);
        childrenVisibility = true;
        collapseButton.setText("Collapse");
        children.setVisible(true);
        collapseButton.setVisible(true);
        numChildren++;
    }

    /**
     * This function adds a comment to the list of children of this comment.
     * @param child The view of the comment to add
     */
    public void addChild(CommentView child) {
        addChild(child.getComment());
    }
}
