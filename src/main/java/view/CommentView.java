package view;

import org.bson.types.ObjectId;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class CommentView extends JPanel {
    final ObjectId id;
    final ObjectId parentId;
    final ObjectId parentPostId;
    final ObjectId authorId;
    final String authorName;
    final String body;
    final List<String> qualifications;
    private final Container commentContainerVertical = Box.createVerticalBox();
    private final Container commentContainerHorizontal = Box.createHorizontalBox();
    private final JPanel children = new JPanel(new GridBagLayout());
    private final JButton collapseButton;
    private int numChildren = 0;
    private final GridBagConstraints constraints = new GridBagConstraints();
    private boolean qualificationsVisibility = false;
    private boolean childrenVisibility = false;

    public CommentView(Map<String, Object> comment) {
        this.id = (ObjectId) comment.get("id");
        this.parentId = (ObjectId) comment.get("parentId");
        this.parentPostId = (ObjectId) comment.get("parentPostId");
        this.authorId = (ObjectId) comment.get("authorId");
        this.authorName = (String) comment.get("authorName");
        this.body = (String) comment.get("body");
        this.qualifications = (List<String>) comment.get("qualifications");

        setLayout(new GridBagLayout());

        initialiseConstraints();

        JPanel informationRow = new JPanel(new GridBagLayout());
        JPanel commentRow = new JPanel(new GridBagLayout());
        JPanel buttonRow = new JPanel(new GridBagLayout());
        JPanel qualificationsRow = new JPanel(new GridBagLayout());

        collapseButton = new JButton(" Expand ");
        collapseButton.setVisible(false);
        collapseButton.addActionListener(e -> {
            if (numChildren > 0) {
                childrenVisibility = !childrenVisibility;
                children.setVisible(childrenVisibility);
            }
            if (childrenVisibility) collapseButton.setText(" Collapse ");
            else collapseButton.setText(" Expand ");
        });

        JLabel usernameLabel = new JLabel(authorName);

        setConstraintInset(0, 0);
        setConstraintWeight(0.1, 1);
        addComponent(informationRow, collapseButton, 0, 0, 1, 1);
        addComponent(informationRow, usernameLabel, GridBagConstraints.RELATIVE, 0, 1, 1);

        setConstraintWeight(1, 0.1);
        addPanel(this, informationRow, 0, GridBagConstraints.RELATIVE, 1, 1);

        setConstraintInset(7, 7);
        addComponent(commentRow, createMultiLineText(body), 0, 0, 1, 1);

        setConstraintInset(0, 0);
        setConstraintWeight(1, 2);
        addPanel(this, commentRow, 0, GridBagConstraints.RELATIVE, 1, 1);

        JButton replyButton = new JButton("Reply");
        replyButton.addActionListener(e -> {
            // TODO: Add reply functionality
        });

        JButton qualificationsButton = new JButton("View Qualifications");
        qualificationsButton.addActionListener(e -> {
            qualificationsVisibility = !qualificationsVisibility;
            qualificationsRow.setVisible(qualificationsVisibility);
        });

        setConstraintWeight(1, 1);
        addComponent(buttonRow, replyButton, 0, 0, 1, 1);
        addComponent(buttonRow, qualificationsButton, GridBagConstraints.RELATIVE, 0, 1, 1);

        setConstraintWeight(1, 0.1);
        addPanel(this, buttonRow, 0, GridBagConstraints.RELATIVE, 1, 1);

        setConstraintInset(3, 3);
        setConstraintWeight(1, 0.45);
        addComponent(qualificationsRow, createMultiLineText(String.join(System.lineSeparator(), qualifications)), 0, 0, 1, 1);

        setConstraintInset(0, 0);
        setConstraintWeight(1, 0.3);
        addPanel(this, qualificationsRow, 0, GridBagConstraints.RELATIVE, 1, 1);
        qualificationsRow.setVisible(false);

        JSeparator separatorVertical = new JSeparator();
        separatorVertical.setOrientation(1);

        JSeparator separatorHorizontal = new JSeparator();

        commentContainerHorizontal.add(separatorVertical);
        commentContainerHorizontal.add(commentContainerVertical);

        commentContainerVertical.add(this);
        commentContainerVertical.add(separatorHorizontal);
        commentContainerVertical.add(children);

        children.setVisible(false);
    }

    private void addComponent(JPanel panel, JComponent component, int x, int y, int width, int height) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        panel.add(component, constraints);
    }

    private void addPanel(JPanel parent, JPanel panel, int x, int y, int width, int height) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        parent.add(panel, constraints);
    }

    private void initialiseConstraints() {
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(0, 0, 0, 0);
    }

    private void setConstraintWeight(double weightx, double weighty) {
        constraints.weightx = weightx;
        constraints.weighty = weighty;
    }

    private void setConstraintInset(int left, int right) {
        constraints.insets = new Insets(2, left, 2, right);
    }

    private JTextArea createMultiLineText(String text) {
        JTextArea textArea = new JTextArea(text);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        return textArea;
    }

    public Container getComment() {
        return commentContainerHorizontal;
    }

    private void addChild(Container child) {
        setConstraintWeight(1, 0.1);
        addComponent(children, new JLabel("     "), 0, GridBagConstraints.RELATIVE, 1, 1);
        setConstraintWeight(1, 1);
        addComponent(children, (JComponent) child, 1, GridBagConstraints.RELATIVE, 1, 1);
        childrenVisibility = true;
        collapseButton.setText(" Collapse ");
        children.setVisible(true);
        collapseButton.setVisible(true);
        numChildren++;
    }

    public void addChild(CommentView child) {
        addChild(child.getComment());
    }

    public ObjectId getParentId() {
        return parentId;
    }
}
