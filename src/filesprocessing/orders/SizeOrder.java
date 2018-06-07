package filesprocessing.orders;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * order class regarding the files' sizes
 */
class SizeOrder extends Order {

    /**
     * Order 'allFiles' by absolute path
     *
     * @param files list of files that needs to be ordered
     * @return the ordered list
     */
    @Override
    public ArrayList<File> order(ArrayList<File> files) {
        ArrayList<File> orderedList = new ArrayList<File>(files);
        Collections.sort(orderedList, (o1, o2) -> {
            long firstSize = o1.length();
            long secondSize = o2.length();
            if (firstSize == secondSize) {
                String firstPath = o1.getAbsolutePath();
                String secondPath = o2.getAbsolutePath();
                return firstPath.compareTo(secondPath) < 0 ? -1 : 1;
            }
            return firstSize < secondSize ? -1 : 1;
        });
        return orderedList;
    }

}
