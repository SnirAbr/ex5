package filesprocessing.analysys;

import java.io.File;
import java.io.*;
import java.util.ArrayList;

/**
 * gets the string of the directory of files and string of the location of the command file and returns the
 * wanted output
 */
public class Parser {

    /* starting string of a filter line*/
    private final static String FILTER_START = "FILTER";
    /* starting string of a order line*/
    private final static String ORDER_START = "ORDER";

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
        ArrayList<String> commandData = new ArrayList<>();
        String line;
        try {
            while ((line = br.readLine()) != null) {
                commandData.add(line);
            }
        } catch (Exception e) {
        }
        ArrayList<String> output = new ArrayList<>();
        while (commandData.size() >= 3) {
            ArrayList<String> section = new ArrayList<>();
            String nextLine = commandData.remove(0);
            if (!nextLine.startsWith(FILTER_START)) {
                // TODO handle error
            }
            section.add(nextLine);
            section.add(commandData.remove(0));
            nextLine = commandData.remove(0);
            if (!nextLine.startsWith(ORDER_START)) {
                // TODO error
            }
            section.add(nextLine);
            if (!commandData.isEmpty()) {
                nextLine = commandData.get(0);
                if (!nextLine.startsWith(FILTER_START)) {
                    commandData.remove(0);
                    section.add(nextLine);
                }
            }
            output.addAll(Sections.handleSection(section));
        }
        if (commandData.size() != 0) {
            //TODO error
        }
        return output;
    }
}
