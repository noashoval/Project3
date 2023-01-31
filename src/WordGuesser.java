/**
 * A class that creates a word guesser class
 *
 * <p>Purdue University -- CS18000 -- Summer 2022</p>
 *
 * @author Noa Shoval
 * @version July 12, 2022
 */
public class WordGuesser {
    private String[][] playingField; //A 5x5 String grid that stores the game field
    private int round; //A counter variable that tracks the current round
    private String solution; //The word the user tries to guess

    public WordGuesser(String solution) {
        this.solution = solution;
        this.round = 1;
        this.playingField = new String[5][5];
        for (int row = 0; row < playingField.length; row++) {
            for (int col = 0; col < playingField[row].length; col++) {
                playingField[row][col] = "   ";
            }
        }
    }

    public String[][] getPlayingField() {
        return playingField;
    }
    public int getRound() {
        return round;
    }
    public String getSolution() {
        return solution;
    }
    public void setPlayingField(String[][] playingField) {
        this.playingField = playingField;
    }
    public void setRound(int round) {
        this.round = round;
    }
    void setSolution(String solution) {
        this.solution = solution;
    }
    //Updates the playingField by adding each character of the guess
    // to the row corresponding to the current round. Use the following
    // characters to tell the user how they did:
    //'' surrounding a character that is in the solution and is in the
    // correct position. Note: This is a single quote on either side
    //** surrounding a character that is in the solution but is not in the correct position
    //{} surrounding a character that is not in the solution

    public boolean checkGuess(String guess) throws InvalidGuessException {

        //if guess is not a five letter word throw error
        //else guess.indexOf 0 = setplayingfield[0][0] either '' for correct, ** for wrong spot, {} for red

        String letterResult = "";
        if (guess.length() != 5) {
            throw new InvalidGuessException("Invalid Guess!");
        } else {

            String[][] tempPlayingField = getPlayingField();

            for (int i = 0; i < solution.length(); i++) {
                if (solution.charAt(i) == guess.charAt(i)) {
                    tempPlayingField[getRound() - 1][i] = "*" + String.valueOf(guess.charAt(i)) + "*";
                } else if (solution.contains(String.valueOf(guess.charAt(i)))){
                    tempPlayingField[getRound() - 1][i] = "'" + String.valueOf(guess.charAt(i)) + "'";
                } else {
                    tempPlayingField[getRound() - 1][i] = "{" + String.valueOf(guess.charAt(i)) + "}";
                }
            }
//            for (int i = 0; i < guess.length(); i++) {
//                String letter = Character.toString(guess.charAt(i));
//                if (guess.charAt(i) == solution.charAt(i)) {
//                    if (i == (guess.length() - 1)) {
//                        letterResult = letter;
//                    } else {
//                        letterResult = letter;
//                    }
//                } else if (solution.contains(letter)) {
//                    if (i == (guess.length() - 1)) {
//                        letterResult = letter;
//                    } else {
//                        letterResult = letter;
//                    }
//                } else {
//                    if (i == (guess.length() - 1)) {
//                        letterResult = letter;
//                    } else {
//                        letterResult = letter;
//                    }
//                }
//                tempPlayingField[i][i] = letterResult;
//            }
//
//        }
            setPlayingField(tempPlayingField);
            if (guess.equals(solution)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public void printField() {
        String seperator = " | ";

        String[][] playingField = getPlayingField();
        for (int row = 0; row < playingField.length; row ++) {
            for (int col = 0; col < playingField[row].length; col ++) {
                System.out.print(playingField[row][col]);
                if (col != playingField[row].length  - 1) {
                    System.out.print(seperator);
                }
            }
            System.out.println();
        }
    }


}
