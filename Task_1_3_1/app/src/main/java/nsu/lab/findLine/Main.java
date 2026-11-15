package nsu.lab.findLine;

import nsu.lab.findLine.impl.SearchSubstring;

public class Main {
    static public void main(String[] args){
        SearchSubstring tool = new SearchSubstring();
        System.out.println(tool.find("build/resources/main/text.txt","бра"));
    }
}
