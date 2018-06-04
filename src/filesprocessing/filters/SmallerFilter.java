package filesprocessing.filters;

import java.io.File;
import java.util.ArrayList;

/**
 * a class of a filter which gives only files smaller than an asked number
 */
public class SmallerFilter extends Filter{

    /**
     * constructs a new filter
     * @param sourceDir the directory of the files
     */
    public SmallerFilter(String sourceDir){
        super(sourceDir);
    }

    @Override
    public ArrayList<File> filter(String[] args) {
        //TODO handle exceptions
        if(args.length != 1){
        }
        try{
            int numBytes = Integer.parseInt(args[0]);
        }
        catch(Exception e){
        }
        ArrayList<File> goodFiles = new ArrayList<File>();
        for(File file: allFiles){
            if(file.length() < Integer.parseInt(args[0])){
                goodFiles.add(file);
            }
        }
        return goodFiles;
    }
}
