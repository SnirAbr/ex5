package filesprocessing.filters;
import filesprocessing.WarningException;

/* An exception class for filter warnings */
class FilterWarningException extends WarningException {

	private static final String MESSAGE = "FILTER";

	/**
	 * Class' Constructor
	 */
	FilterWarningException() {
		super(MESSAGE);
	}

}
