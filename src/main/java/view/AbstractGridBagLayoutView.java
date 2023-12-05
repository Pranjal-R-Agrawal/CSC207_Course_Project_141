package view;

import javax.swing.*;
import java.awt.*;

public class AbstractGridBagLayoutView extends JPanel {
    protected AbstractGridBagLayoutView(String name) {
        setLayout(new GridBagLayout());
        setName(name);
    }

    protected void addComponent(GridBagConstraints constraints, JPanel panel, JComponent component, int x, int y) {
        constraints.gridx = x;
        constraints.gridy = y;
        panel.add(component, constraints);
    }

    protected void addPanel(GridBagConstraints constraints, JPanel parent, JPanel panel, int x, int y) {
        constraints.gridx = x;
        constraints.gridy = y;
        parent.add(panel, constraints);
    }

    protected void initialiseConstraints(GridBagConstraints constraints) {
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(0, 0, 0, 0);
    }

    protected void setConstraintWeight(GridBagConstraints constraints, double weightx, double weighty) {
        constraints.weightx = weightx;
        constraints.weighty = weighty;
    }

    protected void setConstraintInset(GridBagConstraints constraints, int top, int left, int bottom, int right) {
        constraints.insets = new Insets(top, left, bottom, right);
    }

    protected JTextArea createMultiLineText(String text, Boolean editable) {
        JTextArea textArea = new JTextArea(text);
        textArea.setEditable(editable);
        textArea.setLineWrap(true);
        if (!editable) {
            textArea.setCaretColor(textArea.getBackground());
            textArea.setBackground(this.getBackground());
        }
        textArea.setWrapStyleWord(true);
        return textArea;
    }
}
