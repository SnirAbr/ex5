package filesprocessing.filters;

import filesprocessing.WarningException;
import filesprocessing.analysys.Parser;

/* An exception class for filter warnings */
class FilterWarningException extends WarningException {


    /**
     * Class' Constructor
     */
    FilterWarningException() {
        super(Parser.FILTER_WARNING_ID);
    }

}
