package view;

import javax.swing.*;
import java.awt.*;

public class AbstractGridBagLayoutView extends JPanel {
    protected AbstractGridBagLayoutView(String name) {
        setLayout(new GridBagLayout());
        setName(name);
    }

    /**
     * Add a component to a panel with the given constraints
     * @param constraints The constraints to use
     * @param panel The panel to add the component to
     * @param component The component to add
     * @param x The x position
     * @param y The y position
     */
    protected void addComponent(GridBagConstraints constraints, JPanel panel, JComponent component, int x, int y) {
        constraints.gridx = x;
        constraints.gridy = y;
        panel.add(component, constraints);
    }

    /**
     * Add a panel to a panel with the given constraints
     * @param constraints The constraints to use
     * @param parent The panel to add the panel to
     * @param panel The panel to add
     * @param x The x position
     * @param y The y position
     */
    protected void addPanel(GridBagConstraints constraints, JPanel parent, JPanel panel, int x, int y) {
        constraints.gridx = x;
        constraints.gridy = y;
        parent.add(panel, constraints);
    }

    /**
     * Initialise the constraints
     * weightx and weighty are set to 1
     * fill is set to BOTH
     * insets are set to 0
     * @param constraints The constraints to initialise
     */
    protected void initialiseConstraints(GridBagConstraints constraints) {
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(0, 0, 0, 0);
    }

    /**
     * Set the constraints weightx and weighty
     * @param constraints The constraints to initialise
     * @param weightx The weightx to set
     * @param weighty The weighty to set
     */
    protected void setConstraintWeight(GridBagConstraints constraints, double weightx, double weighty) {
        constraints.weightx = weightx;
        constraints.weighty = weighty;
    }

    /**
     * Set the constraints insets
     * @param constraints The constraints to initialise
     * @param top The top inset to set
     * @param left The left inset to set
     * @param bottom The bottom inset to set
     * @param right The right inset to set
     */
    protected void setConstraintInset(GridBagConstraints constraints, int top, int left, int bottom, int right) {
        constraints.insets = new Insets(top, left, bottom, right);
    }

    /**
     * Create a multi line text area
     * The background and caret colour are set to the same as the parent if not editable
     * The text area is set to wrap lines using the word wrap style
     * @param text The text to display
     * @param editable Whether the text area should be editable
     * @return The created text area
     */
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
