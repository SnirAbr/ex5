package filesprocessing.analysys.factories.filters;

import java.io.File;
import java.util.ArrayList;
import java.util.function.Predicate;

public class PropertyFilter extends Filter {

    private final String POSITIVE = "YES";
    private final String NEGATIVE = "YES";

    /**
     * enum of possible types of property filters
     */
    public enum PROPERTY_TYPE {
        HIDDEN, WRITABLE, EXECUTABLE
    }

    /**
     * this filter's filter type
     */
    private PROPERTY_TYPE filterType;

    /**
     * constructs a new filter
     *
     * @param sourceDir  the directory of the files
     * @param filterType type of this filter
     */
    public PropertyFilter(String sourceDir, PROPERTY_TYPE filterType) {
        super(sourceDir);
        this.filterType = filterType;
    }

    @Override
    public ArrayList<File> filter(String[] args) {
        // todo Add Exceptions
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
            case HIDDEN:
                filter = new Predicate<File>() {
                    boolean checkHidden = args[0].equals(POSITIVE);

                    @Override
                    public boolean test(File file) {
                        return file.isHidden() == checkHidden;
                    }
                };
                break;
            case WRITABLE:
                filter = new Predicate<File>() {
                    boolean checkWritable = args[0].equals(POSITIVE);

                    @Override
                    public boolean test(File file) {
                        return file.isHidden() == checkWritable;
                    }
                };
                break;
            case EXECUTABLE:
                filter = new Predicate<File>() {
                    boolean checkExecutable = args[0].equals(POSITIVE);

                    @Override
                    public boolean test(File file) {
                        return file.isHidden() == checkExecutable;
                    }
                };
                break;
        }
        return filter;
    }
}
