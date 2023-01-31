import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

/**
 * A class that creates a wordlibrary
 *
 * <p>Purdue University -- CS18000 -- Summer 2022</p>
 *
 * @author Noa Shoval
 * @version July 12, 2022
 */

public class WordLibrary {
    private String[] library;
    private int seed;
    private Random random;

    public WordLibrary(String[] library, int seed) {
        this.library = library;
        this.seed = seed;
        this.random = new Random(seed);
        cleanLibrary();
    }

    public WordLibrary(String fileName) throws IOException, InvalidWordException {
        //TODO: Implement this constructor!
        //TODO: Read the input file to set the values for the library and seed. The input file format is specified in the next section.
        //Instantiate the random field using the seed value.
        //Call the processLibrary method to review the words in the library.
        //Note: The constructor should handle the exception by printing the error message to the terminal
        BufferedReader br = null;
        try {

            //figure out how many lines there are in the file
            //we need to know # lines so we know, in advance, size of String[] (array of strings)
            //with String[] of correct size initialized, we can go line by line and add to the appropriate
            //spot in the array

            br = new BufferedReader(new FileReader(fileName));
            String firstIntString = br.readLine();
            String seedNumber = firstIntString.replaceAll("\\D+", "");
            int seed = Integer.valueOf(seedNumber);
            setSeed(seed);

            // next, find out how many lines are left (i.e. how many strings)
            int numOfLines = 0;
            // COUNT NUMBER OF WORDS IN LIBRARY
            String line = br.readLine();
            while (line != null) {
                numOfLines += 1;
                line = br.readLine();
            }

            //ADD WORDS TO LIBRARY
            String[] temp = new String[numOfLines];
            br = new BufferedReader(new FileReader(fileName));
            line = br.readLine(); // read int here
            // idk if i need this one (line = br.readLine(); )// this will be the first word
            for (int i = 0; i < numOfLines; i++) {
                String wordLine = br.readLine();
                temp[i] = wordLine;
            }
            setLibrary(temp);
            this.random = new Random(seed);
            processLibrary();


            } catch (IOException e) {
            throw new FileNotFoundException();
            }

        }

    public void cleanLibrary() {
        //TODO: Implement this method!
        //Updates the library field to remove any word that does not have exactly 5 letters.
        //The updated array only contains words that meet this requirement.
        //Do not print any error messages if an invalid word is found, simply remove it.
        //if(Arrays.stream(library).allMatch())

        int numOfLibraryLines = 0;
        for (int i = 0; i < getLibrary().length; i++) {
            if (library[i].length() == 5) {
                numOfLibraryLines += 1;
            }
        }
        String[] onlyValidWords = new String[numOfLibraryLines];
        int validIndex = 0;
        int libraryIndex = 0;
        while (libraryIndex < numOfLibraryLines) {
            if(library[libraryIndex].length() == 5) {
                onlyValidWords[validIndex] = library[libraryIndex];
                validIndex += 1;
            }
            libraryIndex += 1;
        }
        setLibrary(onlyValidWords);
    }

    public void processLibrary() throws InvalidWordException {
        //TODO: Implement this method!
        //go through the library array make every line a new word
        //Seed: [Any Positive Integer]
        //First_Word
        //Second_Word
        //Third_Word
        //...
        //Last_Word
        int libraryIndex = 0;
        if (getLibrary().length ==0) {
            System.out.println("ERROR");
        }
        while (libraryIndex < getLibrary().length) {
            if (library[libraryIndex].length() != 5) {
                throw new InvalidWordException("Invalid word!");
            }
            libraryIndex += 1;
        }
    }

    public String chooseWord() {
        return library[random.nextInt(library.length)];
    }

    public String[] getLibrary() {
        return library;
    }

    public void setLibrary(String[] library) {
        this.library = library;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }
}

