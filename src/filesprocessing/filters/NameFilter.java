package filesprocessing.filters;

import java.io.File;
import java.util.ArrayList;

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
     * constructs a new size filter
     * @param filterType type of this filter
     */
    NameFilter(NAME_TYPE filterType) {
        this.filterType = filterType;
    }

    @Override
    public ArrayList<File> filter(String[] args) throws FilterWarningException {
        if(args.length != 1) {
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
            case PREFIX:
                filter = new MyPredicate() {
                    String prefix = args[0];

                    @Override
                    public boolean test(File file) {
                        return file.getName().startsWith(prefix);
                    }
                };
                break;
            case SUFFIX:
                filter = new MyPredicate() {
                    String suffix = args[0];

                    @Override
                    public boolean test(File file) {
                        return file.getName().endsWith(suffix);
                    }
                };
                break;
            case FILE:
                filter = new MyPredicate() {
                    String name = args[0];

                    @Override
                    public boolean test(File file) {
                        return file.getName().equals(name);
                    }
                };
                break;
            case CONTAINS:
                filter = new MyPredicate() {
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
