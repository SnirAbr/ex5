package filesprocessing.filters;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * a class of a general filter
 */
abstract class Filter {

    /**
     * list of files in the directory
     */
    protected final ArrayList<File> allFiles;

    /**
     * constructs a filter with file directory
     * @param sourceDir path to the source directory
     */
    public Filter(String sourceDir){
        File[] files = new File(sourceDir).listFiles();
        // todo Check for nullity
        allFiles = new ArrayList<File>(Arrays.asList(files));
    }

    /**
     * Filter 'allFiles' and return a list of filtered files
     * @param args filter arguments
     * @return an array of File objects of the filtered list
     */
    abstract public ArrayList<File> filter(String[] args);

}
