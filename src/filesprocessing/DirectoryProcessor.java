package filesprocessing;

import filesprocessing.analysys.Parser;

public class DirectoryProcessor {
    public static void main(String[] args) {
        System.out.println(Parser.processFiles(args[0], args[1]));
    }
}
