package filesprocessing.orders;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

class AbsOrder extends Order {

	/**
	 * Order 'allFiles' by absolute path
	 * @param files list of files that needs to be ordered
	 * @return the ordered list
	 */
	@Override
	public ArrayList<File> order(ArrayList<File> files) {
		ArrayList<File> orderedList = new ArrayList<File>(files);
		Collections.sort(orderedList, new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				String firstPath = o1.getAbsolutePath();
				String secondPath = o2.getAbsolutePath();
				if(firstPath.equals(secondPath)) {
					return 0;
				}
				return firstPath.compareTo(secondPath) < 0 ? -1 : 1;
			}
		});
		return orderedList;
	}


}
