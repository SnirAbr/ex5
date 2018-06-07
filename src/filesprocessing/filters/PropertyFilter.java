package filesprocessing.filters;

import java.io.File;
import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * filters checking the property of the files
 */
class PropertyFilter extends Filter {

    /* strings to check if the filter should take the files answering to the argument or not answering to
    the argument*/
    private final String POSITIVE = "YES";
    private final String NEGATIVE = "NO";

    /**
     * enum of possible types of property filters
     */
    public enum PROPERTY_TYPE {
        HIDDEN, WRITABLE, EXE
    }

    /**
     * this filter's filter type
     */
    private PROPERTY_TYPE filterType;

    /**
     * constructs a new property filter
     *
     * @param filterType type of this filter
     */
    PropertyFilter(PROPERTY_TYPE filterType) {
        this.filterType = filterType;
    }

    @Override
    public ArrayList<File> filter(String[] args) throws FilterWarningException {
        if (args.length != 1) {
            throw new FilterWarningException();
        }
        if (!args[0].equals(POSITIVE) && !args[0].equals(NEGATIVE)) {
            throw new FilterWarningException();
        }
        ArrayList<File> goodFiles = new ArrayList<File>();
        MyPredicate filter = createFilter(args);
        for (File file : FilterFactory.allFiles) {
            if (filter.test(file)) {
                goodFiles.add(file);
            }
        }
        return goodFiles;
    }

    private MyPredicate createFilter(String[] args) {
        MyPredicate filter = null;
        switch (filterType) {
            case HIDDEN:
                filter = new MyPredicate() {
                    boolean checkHidden = args[0].equals(POSITIVE);

                    @Override
                    public boolean test(File file) {
                        return file.isHidden() == checkHidden;
                    }
                };
                break;
            case WRITABLE:
                filter = new MyPredicate() {
                    boolean checkWritable = args[0].equals(POSITIVE);

                    @Override
                    public boolean test(File file) {
                        return file.canWrite() == checkWritable;
                    }
                };
                break;
            case EXE:
                filter = new MyPredicate() {
                    boolean checkExecutable = args[0].equals(POSITIVE);

                    @Override
                    public boolean test(File file) {
                        return file.canExecute() == checkExecutable;
                    }
                };
                break;
        }
        return filter;
    }
}
