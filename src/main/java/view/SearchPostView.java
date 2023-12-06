package view;

import data_access.MongoDBDataAccessObject;
import data_access.MongoDBDataAccessObjectBuilder;
import org.bson.types.ObjectId;
import use_case.search_post.application_business_rules.SearchPostInteractor;
import use_case.search_post.application_business_rules.SearchPostOutputBoundary;
import use_case.search_post.interface_adapter.SearchPostController;
import use_case.search_post.interface_adapter.SearchPostPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

public class SearchPostView extends AbstractGridBagLayoutView implements PropertyChangeListener, Scrollable {
    public final String viewName;
    private final ViewManagerModel viewManagerModel;
    private final SearchPostViewModel searchPostViewModel;
    private final SearchPostController searchPostController;
    private final JTextField searchField = new JTextField();
    private final JButton searchButton = new JButton("Search");
    private final JPanel resultsPanel = new JPanel(new GridBagLayout());

    public SearchPostView(ViewManagerModel viewManagerModel, SearchPostViewModel searchPostViewModel, HomePageViewModel homePageViewModel, SearchPostController searchPostController, CreatePostView createPostView) {
        super(searchPostViewModel.getViewName());
        this.viewManagerModel = viewManagerModel;
        this.searchPostViewModel = searchPostViewModel;
        this.viewName = searchPostViewModel.getViewName();
        this.searchPostController = searchPostController;
        searchPostViewModel.addPropertyChangeListener(this);
        setName(viewName);

        GridBagConstraints constraints = new GridBagConstraints();
        initialiseConstraints(constraints);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        setConstraintInset(constraints, 0, 5, 0, 5);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(
                e -> {
                    if (e.getSource().equals(backButton)) {
                        viewManagerModel.switchView(homePageViewModel.getViewName());
                    }
                }
        );
        addComponent(constraints, buttonPanel, backButton, 0, 0);
        JButton createPostButton = new JButton("Create Post");
        createPostButton.addActionListener(
                e -> {
                    if (e.getSource().equals(createPostButton)) {
                        viewManagerModel.displayCreatePost(createPostView);
                    }
                }
        );
        addComponent(constraints, buttonPanel, createPostButton, 1, 0);
        setConstraintWeight(constraints, 1, 0.1);

        addPanel(constraints, this, buttonPanel, 0, 0);

        JPanel searchPanel = new JPanel(new GridBagLayout());
        setConstraintWeight(constraints, 1, 1);
        setConstraintInset(constraints, 0, 5, 0, 3);
        addComponent(constraints, searchPanel, searchField, 0, 0);
        setConstraintWeight(constraints, 0.1, 1);
        setConstraintInset(constraints, 0, 2, 0, 5);
        addComponent(constraints, searchPanel, searchButton, 1, 0);

        searchButton.addActionListener(
                e -> {
                    if (e.getSource().equals(searchButton)) {
                        searchPostController.execute(searchField.getText());
                    }
                }
        );
        searchField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                searchPostViewModel.getState().setSearchQuery(searchField.getText() + e.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        initialiseConstraints(constraints);
        setConstraintWeight(constraints, 1, 0.1);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        addPanel(constraints, this, searchPanel, 0, 1);
        setConstraintWeight(constraints, 1, 10);
        addPanel(constraints, this, resultsPanel, 0, 2);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("search_error")) {
            JOptionPane.showMessageDialog(this, searchPostViewModel.getState().getErrorMessage());
            viewManagerModel.resize("main");
        } else if (evt.getPropertyName().equals("retrieved")) {
            resultsPanel.removeAll();
            GridBagConstraints constraints = new GridBagConstraints();
            initialiseConstraints(constraints);
            setConstraintInset(constraints, 5, 5, 0, 5);
            for (Map<String, Object> result : searchPostViewModel.getState().getResults()) {
                addPanel(constraints, resultsPanel, new SearchPostResultView(result), 0, GridBagConstraints.RELATIVE);
            }
            searchPostViewModel.getState().setResults(new ArrayList<>());
            this.revalidate();
            this.repaint();
            viewManagerModel.resize("main");
            viewManagerModel.firePropertyChanged();
        } else if (evt.getPropertyName().equals("open_post")) {
            viewManagerModel.displayPost(searchPostViewModel.getSelectedPostId());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        JFrame frame = new JFrame("Search Post View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        MongoDBDataAccessObject mongoDBDataAccessObject = new MongoDBDataAccessObjectBuilder().setStandadParameters().build();
        SearchPostViewModel searchPostViewModel = new SearchPostViewModel();
        SearchPostOutputBoundary searchPostOutputBoundary = new SearchPostPresenter(searchPostViewModel);
        SearchPostInteractor searchPostInteractor = new SearchPostInteractor(mongoDBDataAccessObject, searchPostOutputBoundary);
        SearchPostController searchPostController = new SearchPostController(searchPostInteractor);
        SearchPostView searchPostView = new SearchPostView(new ViewManagerModel(), searchPostViewModel, new HomePageViewModel(), searchPostController, null);

        frame.add(searchPostView);
        frame.setVisible(true);
    }

    public Dimension getPreferredScrollableViewportSize() {return getPreferredSize();}
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {return 1;}
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {return ((orientation == SwingConstants.VERTICAL) ? visibleRect.height : visibleRect.width) - 10;}
    public boolean getScrollableTracksViewportWidth() {return true;}
    public boolean getScrollableTracksViewportHeight() {return false;}

    private class SearchPostResultView extends AbstractGridBagLayoutView {
        protected SearchPostResultView(Map<String, Object> result) {
            super("search_post_result");
            GridBagConstraints constraints = new GridBagConstraints();
            initialiseConstraints(constraints);
            setConstraintInset(constraints, 0, 5, 0, 5);
            addComponent(constraints, this,  createMultiLineText("Title: " + result.get("title"), false), 0, 0);
            initialiseConstraints(constraints);
            JButton openButton = new JButton("View Post");
            addComponent(constraints, this,  openButton, 0, 1);
            openButton.addActionListener(
                    e -> {
                        if (e.getSource().equals(openButton)) {
                            searchPostViewModel.setSelectedPostId((ObjectId) result.get("id"));
                            searchPostViewModel.firePropertyChanged("open_post");
                        }
                    }
            );
            setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
        }
    }
}
