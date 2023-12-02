package api;

import entity.Idea;
public interface GenerativeAIAPI {
    String generateBusinessModel(Idea idea, boolean testForException) throws Exception;
}
