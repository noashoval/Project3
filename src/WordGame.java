import java.io.*;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
/**
 * A class that creates a word game
 *
 * <p>Purdue University -- CS18000 -- Summer 2022</p>
 *
 * @author Noa Shoval
 * @version July 16, 2022
 */

public class WordGame {

    public static String welcome = "Ready to play?";
    public static String yesNo = "1.Yes\n2.No";
    public static String noPlay = "Maybe next time!";
    public static String currentRoundLabel = "Current Round: ";
    public static String enterGuess = "Please enter a guess!";
    public static String winner = "You got the answer!";
    public static String outOfGuesses = "You ran out of guesses!";
    public static String solutionLabel = "Solution: ";
    public static String incorrect = "That's not it!";
    public static String keepPlaying = "Would you like to make another guess?";
    public static String enterWords = "Please enter a comma-separated list of words";
    public static String enterSeed = "Please enter a seed for the random number generator";

    public static void main(String[] args) throws InvalidWordException, IOException, InvalidGuessException {
        Scanner scanner = new Scanner(System.in);
        boolean solved = true;
        String userFileName = "";
        File gameLog = new File("gamelog.txt");
        gameLog.createNewFile();
        PrintWriter pw = new PrintWriter(gameLog);
        pw.println("Games Completed: 0");
        pw.close();
        System.out.println("Please enter a filename");
        userFileName = scanner.nextLine();
        WordLibrary wl = new WordLibrary(userFileName);
        boolean wantsToPlay = true;
        System.out.println(welcome);
        System.out.println(yesNo);
        String userInput = scanner.nextLine();
        System.out.println(userInput);
        while (wantsToPlay) {
            String wordForGame = wl.chooseWord();
            WordGuesser wg = new WordGuesser(wordForGame);
            wg.setSolution(wordForGame);
            wg.setRound(1);
            String[] guesses = new String[5];
            int numbGuess = 0;
            boolean hasWon = false;


            while (userInput.equals("1") && numbGuess < 5 && !hasWon) {

                System.out.println(currentRoundLabel + wg.getRound());
                wg.printField();
                System.out.println(enterGuess);
                String userGuess = scanner.nextLine();
                hasWon = wg.checkGuess(userGuess);
                while (hasWon != true && hasWon != false) {
                    System.out.println(enterGuess);
                    userGuess = scanner.nextLine();
                    hasWon = wg.checkGuess(userGuess);
                }
                guesses[numbGuess] = userGuess;
                numbGuess += 1;
                if (!hasWon && numbGuess < 5) {
                    System.out.println(incorrect);
                    System.out.println(keepPlaying);
                    System.out.println(yesNo);
                    userInput = scanner.nextLine();
                }
                wg.setRound(numbGuess + 1);
            }
            if (userInput.equals("2")) {
                System.out.println(noPlay);
            } else if (hasWon) {
                System.out.println(winner);
                wg.printField();
                solved = true;
            }  else {
                System.out.println(outOfGuesses);
                System.out.println(solutionLabel + wg.getSolution());
                solved = false;

            }
            updateGameLog(wg.getSolution(), guesses, solved);
            System.out.println(welcome);
            System.out.println(yesNo);
            userInput = scanner.nextLine();
            if (userInput.equals("2")) {
                wantsToPlay = false;
            } else {
                wantsToPlay = true;
            }

        }


    }
    public static void updateGameLog(String solution, String[] guesses, boolean solved) throws IOException {
        WordGuesser wg = new WordGuesser(solution);
        FileReader fr = new FileReader("gamelog.txt");
        BufferedReader bfr = new BufferedReader(fr);
        String firstLine = bfr.readLine();
        String digitString = firstLine.replaceAll("\\D+",String.valueOf(wg.getRound()));
        int gamesPlayed = Integer.parseInt(digitString);
        PrintWriter pw = new PrintWriter("gamelog.txt");
        pw.println("Games Completed: " + wg.getRound());
        pw.println("Game: " + wg.getRound());
        pw.println("Solution: " + solution);
        pw.print("Guesses: ");
        for (int i = 0; i < guesses.length && guesses[i] != null; i++) {
            if(i == guesses.length - 1|| guesses[i+1] == null) {
                pw.print(guesses[i]);
            } else {
                pw.print(guesses[i] + ",");
            }

        }
        pw.println();
        pw.println("Solved: " + solved);
        pw.close();


    }
}
