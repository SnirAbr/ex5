package filesprocessing.analysys;

import filesprocessing.WarningException;
import filesprocessing.filters.FilterDecorator;
import filesprocessing.filters.FilterFactory;
import filesprocessing.orders.OrderDecorator;
import filesprocessing.orders.OrderFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * this class takes a section from the command file and breaks it down
 * it gets the files fitting to the command and orders them accordingly
 */
class SectionHandler {

    /* the character splitting parameters is the subsections part */
    private final static String SPLITTER = "#";
    /* the parameter indicating if we need all files but the ones matching to the other parameters */
    private final static String NEGATIVE = "NOT";
    /* the parameter indicating if we need to reverse the order of the files */
    private final static String REVERSE = "REVERSE";

    /**
     * @param section   has the filter argument for the files and can have the order arguments
     * @param sourceDir the directory of the files
     * @return returns the fitting files in order according to this section's commands
     * @throws WarningException in case of invalid arguments of some sort
     */
    static ArrayList<String> handleSection(ArrayList<String> section, String sourceDir)
            throws WarningException {
        ArrayList<String> sectionCopy = new ArrayList<>(section);
        // should get the filter command line arguments splitted by '#'
        String[] filterCommand = sectionCopy.remove(0)
                .split(SPLITTER);
        // checks if the filter has a not on the conditions given by the other arguments
        boolean not = filterCommand[filterCommand.length - 1].equals(NEGATIVE);
        String filterType = filterCommand[0];
        // reduces the command arguments to be without the filter type and the not parameter if given
        if (filterCommand.length > 1) {
            if (not) {
                filterCommand = Arrays.copyOfRange(filterCommand, 1, filterCommand.length - 1);
            } else {
                filterCommand = Arrays.copyOfRange(filterCommand, 1, filterCommand.length);
            }
        } else {
            filterCommand = new String[0];
        }
        // filters the right files from all the files
        FilterDecorator filter = FilterFactory.createFilter(sourceDir, filterType, not);
        ArrayList<File> fitting_files = filter.filter(filterCommand);
        String[] orderCommand = {""};
        // orders the files
        if (!sectionCopy.isEmpty()) {
            orderCommand = sectionCopy.remove(0)
                    .split(SPLITTER);
        }
        boolean reverse = false;
        if (orderCommand.length != 1) {
            reverse = orderCommand[orderCommand.length - 1].equals(REVERSE);
        }
        String orderType = orderCommand[0];
        OrderDecorator orderer = OrderFactory.createOrder(orderType, reverse);
        ArrayList<File> orderedFiles = orderer.order(fitting_files);
        return filesToStrings(orderedFiles);
    }

    /**
     * @param files array list of files
     * @return array list of strings of the names of input file array list
     */
    private static ArrayList<String> filesToStrings(ArrayList<File> files) {
        ArrayList<String> orderedNames = new ArrayList<>();
        for (File file : files) {
            orderedNames.add(file.getName());
        }
        return orderedNames;
    }
}
