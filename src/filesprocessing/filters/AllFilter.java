package filesprocessing.filters;

import java.io.File;
import java.util.ArrayList;

/**
 * a class of a filter which gives only files who are either executable or not (depends on input)
 */
class AllFilter extends Filter {

    /**
     * constructs a new filter
     *
     * @param sourceDir the directory of the files
     */
    AllFilter(String sourceDir) {
        super(sourceDir);
    }

    @Override
    public ArrayList<File> filter(String[] args) {
        //TODO handle exceptions
        if (args.length != 0) {
        }
        return allFiles;
    }
}
