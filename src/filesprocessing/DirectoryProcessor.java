package filesprocessing;

import filesprocessing.analysys.Parser;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * the class with the main function, gets the directory of the files and the command file and processes the
 * files according to the command file
 */
public class DirectoryProcessor {

    /* string of error when not given 2 parameters */
    private static final String NUM_ARGS_ERR = "ERROR: the number of arguments is different than 2";

    /**
     * processes the files according to the command file
     * @param args 2 arguments directory of files and command file
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println(NUM_ARGS_ERR);
            return;
        }
        ArrayList<OutputLine> output = Parser.processFiles(args[0], args[1]);
        for (OutputLine line : output) {
            switch (line.getStatus()) {
                case ERROR:
                    printError(line);
                    return;
                case WARNING:
                    printWarning(line);
                    break;
                case VALID:
                    System.out.println(line);
            }
        }
    }

    private static void printError(OutputLine err) {
        System.err.println(err);
    }

    private static void printWarning(OutputLine warning) {
        System.err.println(warning);
    }
}
