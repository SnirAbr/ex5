package filesprocessing.orders;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class TypeOrder extends Order {

	/**
	 * Order 'allFiles' by absolute path
	 * @param files list of files that needs to be ordered
	 * @return the ordered list
	 */
	@Override
	public ArrayList<File> order(ArrayList<File> files) {
		ArrayList<File> orderedList = new ArrayList<File>(files);
		Collections.sort(orderedList, (o1, o2) -> {
			String firstType = getFileExtension(o1);
			String secondType = getFileExtension(o2);
			if(firstType.equals(secondType)) {
				String firstPath = o1.getAbsolutePath();
				String secondPath = o2.getAbsolutePath();
				return firstPath.compareTo(secondPath) < 0 ? -1 : 1;
			}
			return firstType.compareTo(secondType) < 0 ? -1 : 1;
		});
		return orderedList;
	}

	/**
	 * Finds the extension of a file
	 * @param file the file to get the extension of
	 * @return the extension of the file
	 */
	private String getFileExtension(File file) {
		String absPath = file.getAbsolutePath();
		int periodPosition = absPath.lastIndexOf('.');
		return periodPosition != -1 ? absPath.substring(periodPosition + 1) : "";
	}

}
