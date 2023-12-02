package data_access;

import entity.Idea;
import entity.IdeaFactory;
import java.io.*;
import java.util.*;

/**
 * Concrete implementation of the GenerateIdeaDataAccessInterface interface with a CSV file database.
 * @author Sidharth Sawhney
 */
public class IdeaDataFileDataAccessObject implements GenerateIdeaDataAccessInterface{

    private final File ideaFile;
    private Map<Integer, Idea> ideas = new HashMap<>();
    private IdeaFactory ideaFactory;


    /**
     * Initializes a collection of ideas.
     * @param csvPath a valid file path
     * @param ideaFactory factory to create Ideas from prompts in the Database
     * @throws Exception occurs when file is not found or ideas couldn't be retrieved
     */
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


    /**
     * Reads random idea from the loaded collection of ideas from the database
     * @return an idea containing its prompt only (no business model)
     */
    @Override
    public Idea generateRandomIdea()
    {
        Random random = new Random();
        int randomKeyIndex = random.nextInt(ideas.keySet().size());
        Idea idea = ideas.get(randomKeyIndex);
        return idea;
    }
}
