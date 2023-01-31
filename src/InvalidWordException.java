/**
 * A class that creates a word exception
 *
 * <p>Purdue University -- CS18000 -- Summer 2022</p>
 *
 * @author Noa Shoval
 * @version July 12, 2022
 */
public class InvalidWordException extends Exception {
    public String message;

    public InvalidWordException(String message) {
        super(message);
    }
}
