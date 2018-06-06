package filesprocessing;

import filesprocessing.analysys.Parser;

import java.util.ArrayList;
import java.util.Arrays;

public class DirectoryProcessor {

    private static final String NUM_ARGS_ERR = "ERROR: the number of arguments is different than 2";

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
