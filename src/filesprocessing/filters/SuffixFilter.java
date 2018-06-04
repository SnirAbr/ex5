package filesprocessing.filters;

import java.io.File;
import java.util.ArrayList;

public class SuffixFilter extends Filter {

	/**
	 * Calls the Filter's constructor
	 */
	public SuffixFilter(String SourceDir) {
		super(SourceDir);
	}

	@Override
	public ArrayList<File> filter(String[] args) {
		// todo Add Exceptions
		String name = args[0];
		ArrayList<File> filteredFiles = new ArrayList<File>();
		for(File file : allFiles) {
			if(file.getName().endsWith(name)) {
				filteredFiles.add(file);
			}
		}
		return filteredFiles;
	}
}
