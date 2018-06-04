package filesprocessing.filters;

import java.io.File;

/**
 * a class of a general filter
 */
abstract class Filter {

    /**
     * list of files in the directory
     */
    protected final File[] allFiles;

    /**
     * constructs a filter with file directory
     * @param sourceDir path to the source directory
     */
    public Filter(String sourceDir){
        allFiles = (new File(sourceDir)).listFiles();
    }

    /**
     * Filter 'allFiles' and return a list of filtered files
     * @param args filter arguments
     * @return an array of File objects of the filtered list
     */
    abstract public File[] filter(String[] args);

}
