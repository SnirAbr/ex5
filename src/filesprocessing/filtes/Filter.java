package filesprocessing.filtes;

import java.io.File;

/**
 * a class filtering the files in a given directory by a certain command
 */
class Filter {

    /**
     * enum holding all the command types
     */
    public enum FILTER_COMMAND {
        GREATER_THAN, BETWEEN, SMALLER_THAN, FILE, CONTAINS, PREFIX, SUFFIX, WRITABLE, EXECUTABLE, HIDDEN, ALL
    }

    /**
     * this filter's command
     */
    private FILTER_COMMAND filter_command;
    /**
     * string holding the directory holding the files to be filtered
     */
    private String sourceDir;

    /**
     * constructs a filter
     * @param command the command this filter acts uppon
     * @param sourceDir the directory of the files
     */
    public Filter(String sourceDir, FILTER_COMMAND command) {
        filter_command = command;
        this.sourceDir = sourceDir;
    }

    /**
     * returns all files answering the requirements of this filter's command and the given parameters for it
     * @param args arguments for the filter command
     * @return all files answering the criteria of the command and it's parameters
     */
    public File[] get_files(String[] args){
        return null;
    }


}
