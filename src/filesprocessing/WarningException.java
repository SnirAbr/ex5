package filesprocessing;

/* An exception class for warnings (mainly used for invalid parameters) */
public class WarningException extends Exception {

    /**
     * Class' Constructor
     *
     * @param message error message
     */
    public WarningException(String message) {
        super(message);
    }

}
