package filesprocessing.filters;

import java.io.File;
import java.util.ArrayList;

/**
 * a class of a filter which gives only files of size in given bounds
 */
public class BetweenFilter extends Filter {

    /**
     * constructs a new filter
     *
     * @param sourceDir the directory of the files
     */
    public BetweenFilter(String sourceDir) {
        super(sourceDir);
    }

    @Override
    public ArrayList<File> filter(String[] args) {
        //TODO handle exceptions
        if (args.length != 2) {
        }
        try {
            Integer.parseInt(args[0]);
            Integer.parseInt(args[1]);
        } catch (Exception e) {
        }
        ArrayList<File> goodFiles = new ArrayList<File>();
        for (File file : allFiles) {
            if (file.length() >= Integer.parseInt(args[0]) && file.length() <= Integer.parseInt(args[1])) {
                goodFiles.add(file);
            }
        }
        return goodFiles;
    }
}
