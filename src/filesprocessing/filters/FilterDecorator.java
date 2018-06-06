package filesprocessing.filters;

import java.io.File;
import java.util.ArrayList;

public class FilterDecorator {

    /* The filter to work with */
    Filter filter;

    /* whether the 'NOT' option was used or not */
    boolean not;

    /**
     * Class' Constructor
     */
    public FilterDecorator(Filter filter, boolean not) {
        this.filter = filter;
        this.not = not;
    }

    /**
     * Filter 'allFiles' and return a list of filtered files
     *
     * @param args filter arguments
     * @return an array of File objects of the filtered list
     * @throws FilterWarningException in case of bad arguments
     */
    public ArrayList<File> filter(String[] args) throws FilterWarningException {
        ArrayList<File> filteredFiles = null;
        try {
            filteredFiles = this.filter.filter(args);
        } catch (FilterWarningException e) {
            throw e;
        }
        if (not) {
            ArrayList<File> allFilesCopy = new ArrayList<File>(FilterFactory.allFiles);
            allFilesCopy.removeAll(filteredFiles);
            return allFilesCopy;
        }
        return filteredFiles;
    }

    /**
     * Sets a new filter to the decorator
     *
     * @param newFilter the new filter
     */
    public void setFilter(Filter newFilter) {
        this.filter = newFilter;
    }

}
