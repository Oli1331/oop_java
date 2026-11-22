package nsu.lab.findLine.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class SearchSubstringTest {

    private final SearchSubstring finder = new SearchSubstring();

    @Test
    void basicTest() throws IOException {
        List<Long> result = finder.find(new StringReader("абракадабра"), "бра");
        assertEquals(List.of(1L, 8L), result);
    }

    @Test
    void overlappingTest() throws IOException {
        List<Long> result = finder.find(new StringReader("абабаба"), "аба");
        assertEquals(List.of(0L, 2L, 4L), result);
    }

    @Test
    void bufferBoundaryTest() throws IOException {
        String text = "a".repeat(4095) + "АБАБАБГ";
        List<Long> result = finder.find(new StringReader(text), "АБАБГ");
        assertEquals(List.of(4097L), result);
    }

    @Test
    void emptySubstringTest() throws IOException {
        String text = "(●'◡'●)";
        List<Long> result = finder.find(new StringReader(text), "");
        assertTrue(result.isEmpty());
    }

    @Test
    void EmojiTest() throws IOException {
        List<Long> result = finder.find(new StringReader("🥝бра🍊"), "бра");
        assertEquals(List.of(1L), result);
    }

    @Test
    void largeGeneratedFileTest(@TempDir Path tempDir) throws IOException {
        Path bigFile = tempDir.resolve("big.txt");

        String chunk = "a".repeat(100);
        try (BufferedWriter writer = Files.newBufferedWriter(bigFile, StandardCharsets.UTF_8)) {
            for (int i = 0; i < 100_000; i++) {
                writer.write(chunk);
            }
            writer.write("секрет");
        }

        List<Long> result = finder.find(bigFile.toString(), "секрет");
        assertEquals(List.of(10_000_000L), result);
    }
}