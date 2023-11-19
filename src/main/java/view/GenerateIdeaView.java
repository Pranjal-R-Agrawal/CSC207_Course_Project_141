package view;

import use_case.generate_idea.interface_adapter.GenerateIdeaController;
import use_case.generate_idea.interface_adapter.GenerateIdeaState;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GenerateIdeaView extends JPanel implements PropertyChangeListener {
    public final String viewName;
    private final GenerateIdeaViewModel generateIdeaViewModel;
    private final GenerateIdeaController generateIdeaController;
    private final HomePageViewModel homePageViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JButton ideaButton = new JButton();
    private final JButton backButton = new JButton();
    private final JButton postButton = new JButton();

    public GenerateIdeaView(ViewManagerModel viewManagerModel, GenerateIdeaViewModel generateIdeaViewModel,GenerateIdeaController generateIdeaController,HomePageViewModel homePageViewModel){
        this.generateIdeaViewModel = generateIdeaViewModel;
        this.generateIdeaController = generateIdeaController;
        this.viewManagerModel = viewManagerModel;
        this.homePageViewModel = homePageViewModel;

        viewName = generateIdeaViewModel.getViewName();
        setName(viewName);

        ideaButton.setText(GenerateIdeaViewModel.IDEA_BUTTON_LABEL);
        backButton.setText(GenerateIdeaViewModel.BACK_BUTTON_LABEL);
        postButton.setText(GenerateIdeaViewModel.POST_BUTTON_LABEL);

        generateIdeaViewModel.addPropertyChangeListener(this);

        ideaButton.addActionListener(
                e -> {
                    if (e.getSource().equals(ideaButton)) {
                        generateIdeaController.execute();
                    }
                }
        );


        backButton.addActionListener(
                e -> {
                    if (e.getSource().equals(backButton)) {
                        generateIdeaViewModel.setState(new GenerateIdeaState());
                        viewManagerModel.setActiveView(homePageViewModel.getViewName());
                        viewManagerModel.firePropertyChanged();
                    }
                }
        );


        postButton.addActionListener(
                e -> {
                    if (e.getSource().equals(postButton)) {
//                        SignupState currentState = signupViewModel.getState();
//                        signupController.execute(
//                                currentState.getUsername(),
//                                currentState.getPassword(),
//                                currentState.getRepeatPassword(),
//                                currentState.getName(),
//                                currentState.getEmail(),
//                                currentState.getPhoneNumber()
//                        );
                    }
                }
        );


        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        if (generateIdeaViewModel.getState().getBusinessModel().equals(""))
        {
            add(ideaButton, c);
            add(backButton, c);
        }
        else {
            add(ideaButton, c);
            JPanel ideaOutputData = create();
            addComponent(ideaOutputData, c, 0, 0, GridBagConstraints.HORIZONTAL);
            add(backButton, c);
            add(postButton, c);
        }
    }

    private void addComponent(JPanel panel, GridBagConstraints c, int gridx, int gridy, int fill) {
        c.gridx = gridx;
        c.gridy = gridy;
        c.fill = fill;
        add(panel, c);
    }

    private JPanel create() {
        JPanel panel = new JPanel();

        JTextArea textArea1 = new JTextArea(3, 20);
        textArea1.setLineWrap(true);
        textArea1.setWrapStyleWord(true);
        textArea1.setText(generateIdeaViewModel.getState().getIdea());
        textArea1.setEditable(false); // read only mode
        JScrollPane scrollPane1 = new JScrollPane(textArea1);

        JTextArea textArea2 = new JTextArea(20, 50);
        textArea2.setLineWrap(true);
        textArea2.setWrapStyleWord(true);
        textArea2.setText(generateIdeaViewModel.getState().getBusinessModel());
        textArea2.setEditable(false); // read only mode
        JScrollPane scrollPane2 = new JScrollPane(textArea2);

        panel.add(scrollPane1);
        panel.add(scrollPane2);

        return panel;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
//        if (evt.getPropertyName().equals("sign_up_error")) {
//            SignupState currentState = signupViewModel.getState();
//            JOptionPane.showMessageDialog(this, currentState.getErrorMessage());
//        } else if (evt.getPropertyName().equals("reset_input_fields")) {
//            usernameInputField.setText("");
//            passwordInputField.setText("");
//            repeatPasswordInputField.setText("");
//            nameInputField.setText("");
//            phoneNumberInputField.setText("");
//            emailInputField.setText("");
//        } else if (evt.getPropertyName().equals("update_fields")) {
//            usernameInputField.setText(signupViewModel.getState().getUsername());
//            passwordInputField.setText(signupViewModel.getState().getPassword());
//            repeatPasswordInputField.setText(signupViewModel.getState().getRepeatPassword());
//            nameInputField.setText(signupViewModel.getState().getName());
//            phoneNumberInputField.setText(signupViewModel.getState().getPhoneNumber());
//            emailInputField.setText(signupViewModel.getState().getEmail());
//        }
    }
}
