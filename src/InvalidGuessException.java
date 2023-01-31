/**
 * A class that creates a guess exception
 *
 * <p>Purdue University -- CS18000 -- Summer 2022</p>
 *
 * @author Noa Shoval
 * @version July 12, 2022
 */

public class InvalidGuessException extends Exception {
    public String message;

    public InvalidGuessException(String message) {
        super(message);
    }
}
