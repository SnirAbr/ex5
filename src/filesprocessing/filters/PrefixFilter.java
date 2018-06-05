package filesprocessing.filters;

import java.io.File;
import java.util.ArrayList;

public class PrefixFilter extends Filter {

	/**
	 * Calls the Filter's constructor
	 */
	public PrefixFilter(String SourceDir) {
		super(SourceDir);
	}

	/**
	 * Filters 'allFiles' by its prefix
	 * @param args the prefix to search for
	 * @return a filtered list
	 */
	@Override
	public ArrayList<File> filter(String[] args) {
		// todo Add Exceptions
		String prefix = args[0];
		ArrayList<File> filteredFiles = new ArrayList<File>();
		for(File file : allFiles) {
			if(file.getName().startsWith(prefix)) {
				filteredFiles.add(file);
			}
		}
		return filteredFiles;
	}
}
