package use_case.search_post;

import data_access.MockSearchPostDataAccessObject;
import static org.junit.Assert.*;

import entity.ConcretePostFactory;
import entity.PostInterface;
import org.junit.Before;
import org.junit.Test;
import use_case.search_post.application_business_rules.*;

import java.util.ArrayList;

public class SearchPostInteractorTest {
    MockSearchPostDataAccessObject mockSearchPostDataAccessObject;
    SearchPostInteractor searchPostInteractor;

    @Test
    public void testSearchPostNullQuery() {
        SearchPostOutputBoundary presenter = new SearchPostOutputBoundary() {
            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Search query cannot be empty", errorMessage);
            }

            @Override
            public void prepareSuccessView(SearchPostOutputData searchPostOutputData) {
                fail("Should not reach here");
            }
        };
        SearchPostInputBoundary searchPostInteractor = new SearchPostInteractor(mockSearchPostDataAccessObject, presenter);
        searchPostInteractor.execute(new SearchPostInputData(null));
    }

    @Test
    public void testSearchPostEmptyQuery() {
        SearchPostOutputBoundary presenter = new SearchPostOutputBoundary() {
            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Search query cannot be empty", errorMessage);
            }

            @Override
            public void prepareSuccessView(SearchPostOutputData searchPostOutputData) {
                fail("Should not reach here");
            }
        };
        SearchPostInputBoundary searchPostInteractor = new SearchPostInteractor(mockSearchPostDataAccessObject, presenter);
        searchPostInteractor.execute(new SearchPostInputData(""));
    }

    @Test
    public void testSearchPostWrongTitle() {
        SearchPostOutputBoundary presenter = new SearchPostOutputBoundary() {
            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("No results found", errorMessage);
            }

            @Override
            public void prepareSuccessView(SearchPostOutputData searchPostOutputData) {
                fail("Should not reach here");
            }
        };
        SearchPostInputBoundary searchPostInteractor = new SearchPostInteractor(mockSearchPostDataAccessObject, presenter);
        searchPostInteractor.execute(new SearchPostInputData("title"));
    }

    @Test
    public void testSearchPostPopulateOutputData() {
        PostInterface post = new ConcretePostFactory().create(null, "title", "content", new ArrayList<>());
        mockSearchPostDataAccessObject.addPost(post);
        SearchPostOutputBoundary presenter = new SearchPostOutputBoundary() {
            @Override
            public void prepareFailView(String errorMessage) {
                fail("Should not reach here");
            }

            @Override
            public void prepareSuccessView(SearchPostOutputData searchPostOutputData) {
                assertEquals(1, searchPostOutputData.getResults().size());
                assertEquals(post.getId(), searchPostOutputData.getResults().get(0).get("id"));
                assertEquals("title", searchPostOutputData.getResults().get(0).get("title"));
            }
        };
        SearchPostInputBoundary searchPostInteractor = new SearchPostInteractor(mockSearchPostDataAccessObject, presenter);
        searchPostInteractor.execute(new SearchPostInputData("title"));
    }

    @Test
    public void testSearchPostMultiple() {
        PostInterface post1 = new ConcretePostFactory().create(null, "title", "content", new ArrayList<>());
        PostInterface post2 = new ConcretePostFactory().create(null, "title", "content", new ArrayList<>());
        PostInterface post3 = new ConcretePostFactory().create(null, "title", "content", new ArrayList<>());
        mockSearchPostDataAccessObject.addPost(post1);
        mockSearchPostDataAccessObject.addPost(post2);
        mockSearchPostDataAccessObject.addPost(post3);
        SearchPostOutputBoundary presenter = new SearchPostOutputBoundary() {
            @Override
            public void prepareFailView(String errorMessage) {
                fail("Should not reach here");
            }

            @Override
            public void prepareSuccessView(SearchPostOutputData searchPostOutputData) {
                assertEquals(3, searchPostOutputData.getResults().size());
            }
        };
        SearchPostInputBoundary searchPostInteractor = new SearchPostInteractor(mockSearchPostDataAccessObject, presenter);
        searchPostInteractor.execute(new SearchPostInputData("title"));
    }

    @Before
    public void setUpTest() {
        mockSearchPostDataAccessObject = new MockSearchPostDataAccessObject();
    }
}
