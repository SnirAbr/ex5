package filesprocessing.filters;

import java.io.File;

/**
 * this class is designed to check a file according to a certain comparison
 */
abstract class MyPredicate {

    /**
     * Tests the file by a given comparison
     *
     * @param file the file to use
     * @return true if the comparison is valid, false otherwise
     * @throws FilterWarningException in case of invalid arguments
     */
    abstract boolean test(File file) throws FilterWarningException;
}
