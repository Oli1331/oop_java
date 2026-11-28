package nsu.lab.findLine.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Class providing functionality for substring searching in files or streams.
 */
public class SearchSubstring {

    /**
     * Default constructor.
     */
    public SearchSubstring() {
    }

    /**
     * Finds all start indices of a substring in a file.
     *
     * @param pathToFile path to the target file
     * @param substring substring to search for
     * @return list of 0-based character indices where substring occurs
     */
    public List<Long> find(String pathToFile, String substring) {
        try (BufferedReader reader = Files.newBufferedReader(Path.of(pathToFile), StandardCharsets.UTF_8)) {
            return find(reader, substring);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Finds all start indices of a substring in a Reader stream.
     *
     * @param reader input stream reader
     * @param substring substring to search for
     * @return list of 0-based character indices where substring occurs
     * @throws IOException if an I/O error occurs
     */
    public List<Long> find(Reader reader, String substring) throws IOException {
        List<Long> result = new ArrayList<>();
        if (substring == null || substring.isEmpty()) {
            return result;
        }

        int[] pi = buildPrefixFunction(substring);
        int patternSymbolLength = substring.codePointCount(0, substring.length());

        char[] buffer = new char[4096];
        int matched = 0;
        long currentSymbolIndex = 0;

        int charsRead;
        while ((charsRead = reader.read(buffer)) != -1) {
            for (int i = 0; i < charsRead; i++) {
                char ch = buffer[i];

                while (matched > 0 && ch != substring.charAt(matched)) {
                    matched = pi[matched - 1];
                }
                if (ch == substring.charAt(matched)) {
                    matched++;
                }
                if (matched == substring.length()) {
                    result.add(currentSymbolIndex - patternSymbolLength + 1);
                    matched = pi[matched - 1];
                }
                if (!Character.isHighSurrogate(ch)) {
                    currentSymbolIndex++;
                }
            }
        }

        return result;
    }

    private int[] buildPrefixFunction(String substring) {
        int[] pi = new int[substring.length()];
        int j = 0;
        for (int i = 1; i < substring.length(); i++) {
            while (j > 0 && substring.charAt(i) != substring.charAt(j)) {
                j = pi[j - 1];
            }
            if (substring.charAt(i) == substring.charAt(j)) {
                j++;
            }
            pi[i] = j;
        }
        //System.out.println("pi for - "+substring + Arrays.toString(pi));
        return pi;
    }
}
