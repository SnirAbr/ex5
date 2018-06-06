package filesprocessing.analysys;

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

    static ArrayList<String> handleSection(ArrayList<String> section, String sourceDir) {
        ArrayList<String> output = new ArrayList<String>();
        String[] filterCommand = section.remove(0)
                .split(SPLITTER);
        boolean not = filterCommand[filterCommand.length - 1].equals(NEGATIVE);
        String filterType = filterCommand[0];
        if (not) {
            filterCommand = Arrays.copyOfRange(filterCommand, 1, filterCommand.length - 1);
        } else {
            filterCommand = Arrays.copyOfRange(filterCommand, 1, filterCommand.length);
        }
        FilterDecorator filter = FilterFactory.createFilter(sourceDir, filterType, not);
        ArrayList<File> fitting_files = filter.filter(filterCommand);
        String[] orderCommand = {""};
        if (!section.isEmpty()) {
            orderCommand = section.remove(0)
                    .split(SPLITTER);
        }
        boolean reverse = orderCommand[filterCommand.length - 1].equals(REVERSE);
        String orderType = orderCommand[0];
        if (reverse) {
            orderCommand = Arrays.copyOfRange(orderCommand, 1, orderCommand.length - 1);
        } else {
            orderCommand = Arrays.copyOfRange(orderCommand, 1, orderCommand.length);
        }
        OrderDecorator orderer = OrderFactory.createOrder(fitting_files,orderType,reverse);
        ArrayList<File> orderedFiles =orderer.order(orderCommand);
        ArrayList<String> orderedNames = new ArrayList<>();
        for(File file:orderedFiles){
            orderedNames.add(file.getName());
        }
        return orderedNames;
    }
}
