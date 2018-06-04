package filesprocessing.filters;

import java.io.File;
import java.util.ArrayList;

/**
 * a class of a filter which gives only files who are either writable or not (depends on input)
 */
public class WritableFilter extends Filter{

    /**
     * constructs a new filter
     * @param sourceDir the directory of the files
     */
    public WritableFilter(String sourceDir){
        super(sourceDir);
    }

    @Override
    public ArrayList<File> filter(String[] args) {
        //TODO handle exceptions
        if(args.length != 1 || (!args[0].equals("YES") && !args[0].equals("NO"))){
        }
        ArrayList<File> goodFiles = new ArrayList<File>();
        boolean writable = args[0].equals("YES");
        for(File file: allFiles){
            if(writable == file.canWrite()){
                goodFiles.add(file);
            }
        }
        return goodFiles;
    }
}
