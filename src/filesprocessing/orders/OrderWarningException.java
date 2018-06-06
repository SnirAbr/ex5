package filesprocessing.orders;
import filesprocessing.WarningException;

/* An exception class for order warnings */
class OrderWarningException extends WarningException {

	private static final String MESSAGE = "ORDER";

	/**
	 * Class' Constructor
	 */
	OrderWarningException() {
		super(MESSAGE);
	}

}
