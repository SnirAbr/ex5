package filesprocessing.filters;

import java.io.File;
import java.util.ArrayList;

/**
 * a class of a filter which gives only files who are either executable or not (depends on input)
 */
class AllFilter extends Filter {

    @Override
    public ArrayList<File> filter(String[] args) throws FilterWarningException {
        if (args.length != 0) {
			throw new FilterWarningException();
        }
        return FilterFactory.allFiles;
    }
}
