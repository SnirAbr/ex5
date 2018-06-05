package filesprocessing.filters;

import java.io.File;
import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * a class of a filter which gives only files larger than an asked number
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

    /**
     * constructs a new filter
     *
     * @param sourceDir the directory of the files
     * @param filterType type of this filter
     */
    SizeFilter(String sourceDir, SIZE_TYPE filterType) {
        super(sourceDir);
        this.filterType = filterType;
    }

    @Override
    public ArrayList<File> filter(String[] args) {
        //TODO handle exceptions
        if (args.length != 1) {
        }
        try {
            int numBytes = Integer.parseInt(args[0]);
        } catch (Exception e) {
        }
        ArrayList<File> goodFiles = new ArrayList<File>();
        Predicate<File> filter = createFilter(args);
        for (File file : allFiles) {
            if (filter.test(file)) {
                goodFiles.add(file);
            }
        }
        return goodFiles;
    }

    private Predicate<File> createFilter(String[] args) {
        Predicate<File> filter = null;
        switch (filterType) {
            case GREATER:
                filter = new Predicate<File>() {
                    int lowerBound = Integer.parseInt(args[0]);

                    @Override
                    public boolean test(File file) {
                        return file.length() > lowerBound;
                    }
                };
                break;
            case BETWEEN:
                filter = new Predicate<File>() {
                    int lowerBound = Integer.parseInt(args[0]);
                    int upperBound = Integer.parseInt(args[1]);

                    @Override
                    public boolean test(File file) {
                        return file.length() >= lowerBound && file.length() <= upperBound;
                    }
                };
                break;
            case SMALLER:
                filter = new Predicate<File>() {
                    int upperBound = Integer.parseInt(args[0]);

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
