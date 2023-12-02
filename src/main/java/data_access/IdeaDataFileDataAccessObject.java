package data_access;

import entity.Idea;
import entity.IdeaFactory;
import java.io.*;
import java.util.*;

public class IdeaDataFileDataAccessObject implements GenerateIdeaDataAccessInterface{

    private final File ideaFile;

    private Map<Integer, Idea> ideas = new HashMap<>();

    private IdeaFactory ideaFactory;

    public IdeaDataFileDataAccessObject(String csvPath, IdeaFactory ideaFactory) throws Exception {
        this.ideaFactory = ideaFactory;
        try {
            Scanner scanner = new Scanner(new File(csvPath));
        }
        catch(FileNotFoundException e)
        {
            throw new FileNotFoundException("Couldn't find Database");
        }

        this.ideaFile = new File(csvPath);

        try {
            loadIdeas();
        }catch(Exception e)
        {
            throw new Exception("Couldn't retrieve Ideas from Database");
        }

    }

    /**
     * Precondition: ideaFile is a valid path
     * @throws Exception
     */
    private void loadIdeas() throws Exception{
            try (BufferedReader reader = new BufferedReader(new FileReader(ideaFile))) {
                reader.readLine(); // Skip the header line

                String row;
                int index=0;
                while ((row = reader.readLine()) != null) {

                    Idea idea = ideaFactory.create(row);
                    ideas.put(index++,idea);
                }
            }
        }
    @Override
    public Idea generateRandomIdea()
    {
        Random random = new Random();
        int randomKeyIndex = random.nextInt(ideas.keySet().size());
        Idea idea = ideas.get(randomKeyIndex);
        return idea;
    }
}
