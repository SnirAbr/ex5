package filesprocessing.filters;

import java.io.File;
import java.util.ArrayList;

public class FilterFactory {

    private static final SizeFilter GREATER = new SizeFilter(SizeFilter.SIZE_TYPE.GREATER);
    private static final SizeFilter BETWEEN = new SizeFilter(SizeFilter.SIZE_TYPE.BETWEEN);
    private static final SizeFilter SMALLER = new SizeFilter(SizeFilter.SIZE_TYPE.SMALLER);
    private static final NameFilter FILE = new NameFilter(NameFilter.NAME_TYPE.FILE);
    private static final NameFilter CONTAINS = new NameFilter(NameFilter.NAME_TYPE.CONTAINS);
    private static final NameFilter PREFIX = new NameFilter(NameFilter.NAME_TYPE.PREFIX);
    private static final NameFilter SUFFIX = new NameFilter(NameFilter.NAME_TYPE.SUFFIX);
    private static final PropertyFilter EXE = new PropertyFilter(PropertyFilter.PROPERTY_TYPE.EXE);
    private static final PropertyFilter WRITE = new PropertyFilter(PropertyFilter.PROPERTY_TYPE.WRITABLE);
    private static final PropertyFilter HIDDEN = new PropertyFilter(PropertyFilter.PROPERTY_TYPE.HIDDEN);
    private static final AllFilter ALL = new AllFilter();

    /* ArrayList that contains all of the files in the given directory */
    static ArrayList<File> allFiles;

    /**
     * Factory for creating filters
     *
     * @param filesDir path for the directory with files
     * @param type     type of filter to create
     * @param not      whether the 'NOT' option was used
     * @return a filter decorator with the fitting filter
     * @throws FilterWarningException in case of invalid arguments
     */
    public static FilterDecorator createFilter(String filesDir, String type, boolean not)
            throws FilterWarningException {
        FilterDecorator decorator = new FilterDecorator(null, not);
        updateFiles(filesDir);
        addFilter(decorator, type);
        return decorator;
    }

    private static void updateFiles(String filesDir) {
        File[] files = new File(filesDir).listFiles();
        allFiles = new ArrayList<File>();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    allFiles.add(file);
                }
            }
        }
    }

    private static void addFilter(FilterDecorator decorator, String type) throws FilterWarningException {
        switch (type) {
            case "greater_than":
                decorator.setFilter(GREATER);
                break;
            case "between":
                decorator.setFilter(BETWEEN);
                break;
            case "smaller_than":
                decorator.setFilter(SMALLER);
                break;
            case "file":
                decorator.setFilter(FILE);
                break;
            case "contains":
                decorator.setFilter(CONTAINS);
                break;
            case "prefix":
                decorator.setFilter(PREFIX);
                break;
            case "suffix":
                decorator.setFilter(SUFFIX);
                break;
            case "writable":
                decorator.setFilter(WRITE);
                break;
            case "executable":
                decorator.setFilter(EXE);
                break;
            case "hidden":
                decorator.setFilter(HIDDEN);
                break;
            case "all":
                decorator.setFilter(ALL);
                break;
            default:
                throw new FilterWarningException();
        }
    }
}
