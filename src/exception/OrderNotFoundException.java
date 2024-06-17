package exception;

/**
 * @author Sattya
 * create at 6/17/2024 1:52 PM
 */
public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(String message) {
        super(message);
    }
}