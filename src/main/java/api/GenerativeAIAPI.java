package api;

import entity.Idea;
public interface GenerativeAIAPI {
    String generateBusinessModel(Idea idea) throws Exception;
}
