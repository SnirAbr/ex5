package filesprocessing.analysys;

import java.io.File;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * gets the string of the directory of files and string of the location of the command file and returns the
 * wanted output
 */
public class Parser {

    /* starting string of a filter line*/
    private final static String FILTER_LINE = "FILTER";
    /* starting string of a order line*/
    private final static String ORDER_LINE = "ORDER";

    /**
     * takes the files from the sourceDir and filters and ordered them according to the command file in a
     * String array list
     *
     * @param sourceDir  the directory of the source files
     * @param commandDir the directory of the command file
     * @return the wanted output data of the program
     */
    public static ArrayList<String> processFiles(String sourceDir, String commandDir) {
        File commandFile = new File(commandDir);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(commandFile));
        } catch (Exception e) {

        }
        ArrayList<String> commandData = new ArrayList<String>();
        String line;
        try {
            while ((line = br.readLine()) != null) {
                commandData.add(line);
            }
        } catch (Exception e) {
        }
        ArrayList<String> output = new ArrayList<String>();
        while (commandData.size() >= 3) {
            ArrayList<String> section = new ArrayList<String>();
            String nextLine = commandData.remove(0);
            if (!nextLine.startsWith(FILTER_LINE)) {
                // TODO handle error
            }
            section.add(commandData.remove(0));
            nextLine = commandData.remove(0);
            if (!nextLine.equals(ORDER_LINE)) {
                // TODO error
            }
            if (!commandData.isEmpty()) {
                nextLine = commandData.get(0);
                if (!nextLine.equals(FILTER_LINE)) {
                    commandData.remove(0);
                    section.add(nextLine);
                }
            }
            output.addAll(SectionHandler.handleSection(section, sourceDir));
        }
        if (commandData.size() != 0) {
            //TODO error
        }
        return output;
    }
}
