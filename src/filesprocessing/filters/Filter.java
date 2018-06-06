package filesprocessing.filters;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * a class of a general filter
 */
abstract class Filter {

    /**
     * Filter 'allFiles' and return a list of filtered files
     * @param args filter arguments
     * @return an array of File objects of the filtered list
     * @throws FilterWarningException
     */
    abstract public ArrayList<File> filter(String[] args) throws FilterWarningException;

}
