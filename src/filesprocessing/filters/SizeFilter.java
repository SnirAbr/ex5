package filesprocessing.filters;

import java.io.File;
import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * a class of a filters regarding the size of the files
 */
class SizeFilter extends Filter {

    /**
     * enum of possible types of size filters
     */
    public enum SIZE_TYPE {
        GREATER, BETWEEN, SMALLER
    }

    /**
     * this filter's filter type
     */
    private SIZE_TYPE filterType;

    /* Ratio between kb and bytes */
    private static final double KB_TO_BYTES_MUL = 1024;

    /**
     * constructs a new size filter
     *
     * @param filterType type of this filter
     */
    SizeFilter(SIZE_TYPE filterType) {
        this.filterType = filterType;
    }

    @Override
    public ArrayList<File> filter(String[] args) throws FilterWarningException {
        ArrayList<File> goodFiles = new ArrayList<File>();
        for (String arg : args) {
            try {
                Double.parseDouble(arg);
            } catch (NumberFormatException e) {
                throw new FilterWarningException();
            }
            if (Double.parseDouble(arg) < 0) {
                throw new FilterWarningException();
            }
        }
        MyPredicate filter = createFilter(args);
        for (File file : FilterFactory.allFiles) {
            if (filter.test(file)) {
                goodFiles.add(file);
            }
        }
        return goodFiles;
    }

    private MyPredicate createFilter(String[] args) throws FilterWarningException {
        MyPredicate filter = null;
        switch (filterType) {
            case GREATER:
                if (args.length != 1) {
                    throw new FilterWarningException();
                }
                filter = new MyPredicate() {
                    double lowerBound = Double.parseDouble(args[0]) * KB_TO_BYTES_MUL;

                    @Override
                    public boolean test(File file) {
                        return file.length() > lowerBound;
                    }
                };
                break;
            case BETWEEN:
                if (args.length != 2) {
                    throw new FilterWarningException();
                }
                filter = new MyPredicate() {
                    double lowerBound = Double.parseDouble(args[0]) * KB_TO_BYTES_MUL;
                    double upperBound = Double.parseDouble(args[1]) * KB_TO_BYTES_MUL;

                    @Override
                    public boolean test(File file) throws FilterWarningException {
                        if (upperBound < lowerBound) {
                            throw new FilterWarningException();
                        }
                        return file.length() >= lowerBound && file.length() <= upperBound;
                    }
                };
                break;
            case SMALLER:
                if (args.length != 1) {
                    throw new FilterWarningException();
                }
                filter = new MyPredicate() {
                    double upperBound = Double.parseDouble(args[0]) * KB_TO_BYTES_MUL;

                    @Override
                    public boolean test(File file) {
                        return file.length() < upperBound;
                    }
                };
                break;

        }
        return filter;
    }
}
