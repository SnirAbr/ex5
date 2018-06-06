package filesprocessing.analysys;

import filesprocessing.WarningException;
import filesprocessing.filters.FilterDecorator;
import filesprocessing.filters.FilterFactory;
import filesprocessing.orders.OrderDecorator;
import filesprocessing.orders.OrderFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

class SectionHandler {

    private final static String SPLITTER = "#";
    private final static String NEGATIVE = "NOT";
    private final static String REVERSE = "REVERSE";

    static ArrayList<String> handleSection(ArrayList<String> section, String sourceDir)
            throws WarningException {
        ArrayList<String> sectionCopy = new ArrayList<>(section);
        String[] filterCommand = sectionCopy.remove(0)
                .split(SPLITTER);
        boolean not = filterCommand[filterCommand.length - 1].equals(NEGATIVE);
        String filterType = filterCommand[0];
        if (filterCommand.length > 1) {
            if (not) {
                filterCommand = Arrays.copyOfRange(filterCommand, 1, filterCommand.length - 1);
            } else {
                filterCommand = Arrays.copyOfRange(filterCommand, 1, filterCommand.length);
            }
        } else {
            filterCommand = new String[0];
        }
        FilterDecorator filter = FilterFactory.createFilter(sourceDir, filterType, not);
        ArrayList<File> fitting_files = filter.filter(filterCommand);
        String[] orderCommand = {""};
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
        ArrayList<String> orderedNames = new ArrayList<>();
        for (File file : orderedFiles) {
            orderedNames.add(file.getName());
        }
        return orderedNames;
    }
}
