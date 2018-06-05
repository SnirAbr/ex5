package filesprocessing.filters;

import java.io.File;
import java.util.ArrayList;
import java.util.function.Predicate;

class NameFilter extends Filter {

    /**
     * enum of possible types of name filters
     */
    public enum NAME_TYPE {
        PREFIX, SUFFIX, FILE, CONTAINS
    }

    /**
     * this filter's filter type
     */
    private NAME_TYPE filterType;

    /**
     * constructs a new filter
     *
     * @param sourceDir the directory of the files
     * @param filterType type of this filter
     */
    NameFilter(String sourceDir, NAME_TYPE filterType) {
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

    private Predicate<File> createFilter(String[] args){
        Predicate<File> filter = null;
        switch (filterType) {
            case PREFIX:
                filter = new Predicate<File>() {
                    String prefix = args[0];

                    @Override
                    public boolean test(File file) {
                        return file.getName().startsWith(prefix);
                    }
                };
                break;
            case SUFFIX:
                filter = new Predicate<File>() {
                    String suffix = args[0];

                    @Override
                    public boolean test(File file) {
                        return file.getName().endsWith(suffix);
                    }
                };
                break;
            case FILE:
                filter = new Predicate<File>() {
                    String name = args[0];

                    @Override
                    public boolean test(File file) {
                        return file.getName().equals(name);
                    }
                };
                break;
            case CONTAINS:
                filter = new Predicate<File>() {
                    String inName = args[0];

                    @Override
                    public boolean test(File file) {
                        return file.getName().contains(inName);
                    }
                };
                break;
        }
        return filter;
    }
}
