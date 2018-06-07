package filesprocessing.orders;
import filesprocessing.WarningException;
import filesprocessing.analysys.Parser;

/* An exception class for order warnings */
class OrderWarningException extends WarningException {

	/**
	 * Class' Constructor
	 */
	OrderWarningException() {
		super(Parser.ORDER_WARNING_ID);
	}

}
