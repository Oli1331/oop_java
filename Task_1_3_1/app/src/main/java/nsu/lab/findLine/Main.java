package nsu.lab.findLine;

import nsu.lab.findLine.impl.SearchSubstring;

/**
 * Main application class.
 */
public class Main {
    /**
     * Entry point of the program.
     *
     * @param args command line arguments
     */
    static public void main(String[] args){
        SearchSubstring tool = new SearchSubstring();
        System.out.println(tool.find("build/resources/main/text.txt","бра"));
    }
}
