package filesprocessing.analysys;

import filesprocessing.OutputLine;
import filesprocessing.WarningException;

import java.io.File;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * gets the string of the directory of files and string of the location of the command file and returns the
 * wanted output
 */
public class Parser {

    /* string of a filter line*/
    private final static String FILTER_LINE = "FILTER";
    /* string of a order line*/
    private final static String ORDER_LINE = "ORDER";

    /* Error message in case the command file is missing */
    private final static String MISSING_FILE_ERROR = "ERROR: Command file is missing";

    /* Error message in case the command file is missing */
    private final static String BAD_SUBSECTION_NAME = "ERROR: Subsection name is invalid";

    /* Error message in case the command file is missing */
    private final static String BAD_COMMAND_FORMAT = "ERROR: Command format is invalid";

    /* Error message while trying to read the command file */
    private final static String READING_ERROR = "ERROR: Couldn't read from command file";

    /* Warning message */
    private final static String WARNING_MESSAGE = "Warning in line ";

    /* id message as a filter warning */
    public final static String FILTER_WARNING_ID = "FILTER";
    /* id message as an order warning*/
    public final static String ORDER_WARNING_ID = "ORDER";

    /* default filter */
    private final static String DEFAULT_FILTER = "all";

    /**
     * takes the files from the sourceDir and filters and ordered them according to the command file in a
     * String array list
     *
     * @param sourceDir  the directory of the source files
     * @param commandDir the directory of the command file
     * @return the wanted output data of the program
     */
    public static ArrayList<OutputLine> processFiles(String sourceDir, String commandDir) {
        ArrayList<OutputLine> output = new ArrayList<OutputLine>();
        File commandFile = new File(commandDir);
        // reads the command file
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(commandFile));
        } catch (IOException e) {
            output.add(new OutputLine(MISSING_FILE_ERROR, OutputLine.STATUS.ERROR));
            return output;
        }
        ArrayList<String> commandData = new ArrayList<String>();
        String line;
        try {
            while ((line = br.readLine()) != null) {
                commandData.add(line);
            }
        } catch (IOException e) {
            output.add(new OutputLine(READING_ERROR, OutputLine.STATUS.ERROR));
            return output;
        }
        int lineCounter = 0;
        // runs on every section
        while (commandData.size() >= 3) {
            ArrayList<String> section = new ArrayList<String>();
            // should be FILTER line
            String nextLine = commandData.remove(0);
            lineCounter++;
            if (!nextLine.equals(FILTER_LINE)) {
                output.clear();
                output.add(new OutputLine(BAD_SUBSECTION_NAME, OutputLine.STATUS.ERROR));
                return output;
            }
            // this is the filter's arguments line
            section.add(commandData.remove(0));
            // should be ORDER line
            nextLine = commandData.remove(0);
            lineCounter += 2;
            if (!nextLine.equals(ORDER_LINE)) {
                output.clear();
                output.add(new OutputLine(BAD_SUBSECTION_NAME, OutputLine.STATUS.ERROR));
                return output;
            }
            // if there is another line and it's not FILTER line then take it as order's arguments line
            if (!commandData.isEmpty()) {
                nextLine = commandData.get(0);
                if (!nextLine.equals(FILTER_LINE)) {
                    commandData.remove(0);
                    lineCounter++;
                    section.add(nextLine);
                }
            }
            // start trying to process files according to this section
            try {
                ArrayList<String> files = SectionHandler.handleSection(section, sourceDir);
                for (String file : files) {
                    output.add(new OutputLine(file, OutputLine.STATUS.VALID));
                }
            } catch (WarningException exception) {
                switch (exception.getMessage()) {
                    // if invalid filter arguments
                    case FILTER_WARNING_ID:
                        int warningLine = section.size() == 1 ? lineCounter - 1 : lineCounter - 2;
                        output.add(new OutputLine(WARNING_MESSAGE + Integer.toString(warningLine),
                                OutputLine.STATUS.WARNING));
                        section.set(0, DEFAULT_FILTER);
                        try {
                            ArrayList<String> files = SectionHandler.handleSection(section, sourceDir);
                            for (String file : files) {
                                output.add(new OutputLine(file, OutputLine.STATUS.VALID));
                            }
                        } catch (WarningException orderException) {
                            // if both filter's and order's original arguments were invalid
                            output.add(new OutputLine(WARNING_MESSAGE + Integer.toString(lineCounter),
                                    OutputLine.STATUS.WARNING));
                            section.remove(1);
                            try {
                                ArrayList<String> files = SectionHandler.handleSection(section, sourceDir);
                                for (String file : files) {
                                    output.add(new OutputLine(file, OutputLine.STATUS.VALID));
                                }
                            } catch (WarningException e) {
                                // never gets here
                            }
                        }
                        break;
                    // if order's arguments are invalid
                    case ORDER_WARNING_ID:
                        output.add(new OutputLine(WARNING_MESSAGE + Integer.toString(lineCounter),
                                OutputLine.STATUS.WARNING));
                        section.remove(1);
                        try {
                            ArrayList<String> files = SectionHandler.handleSection(section, sourceDir);
                            for (String file : files) {
                                output.add(new OutputLine(file, OutputLine.STATUS.VALID));
                            }
                        } catch (WarningException e) {
                            // never gets here
                        }
                }
            }
        }
        // if command file ends with an unfull section
        if (commandData.size() != 0) {
            output.clear();
            output.add(new OutputLine(BAD_COMMAND_FORMAT, OutputLine.STATUS.ERROR));
            return output;
        }
        return output;
    }
}
