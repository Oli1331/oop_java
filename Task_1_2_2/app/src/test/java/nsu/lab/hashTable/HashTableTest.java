package nsu.lab.hashTable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import nsu.lab.hashTable.impl.HashTable;
import nsu.lab.hashTable.impl.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class HashTableTest {

    private HashTable<String, Integer> table;

    @BeforeEach
    public void setUp() {
        table = new HashTable<>();
    }

    @Test
    public void testAddAndGet() {
        assertNull(table.add("One", 1));
        assertNull(table.add("Two", 2));

        assertEquals(1, table.get("One"));
        assertEquals(2, table.get("Two"));
    }

    @Test
    public void testAddOverwriteKey() {
        assertNull(table.add("Key", 100));
        assertEquals(100, table.add("Key", 200));
        assertEquals(200, table.get("Key"));
    }

    @ParameterizedTest
    @CsvSource({
            "apple, 10",
            "banana, 20",
            "cherry, 30"
    })
    public void testParameterizedAddAndHas(String key, Integer value) {
        table.add(key, value);
        assertTrue(table.has(key));
        assertEquals(value, table.get(key));
    }

    @ParameterizedTest
    @ValueSource(strings = {"nonExisting1", "nonExisting2", "unknown"})
    public void testHasAndGetNonExisting(String key) {
        assertFalse(table.has(key));
        assertNull(table.get(key));
    }

    @Test
    public void testCutExistingElement() {
        table.add("A", 1);
        table.add("B", 2);

        assertEquals(1, table.cut("A"));
        assertFalse(table.has("A"));
        assertNull(table.get("A"));
        assertTrue(table.has("B"));
    }

    @Test
    public void testCutNonExistingElement() {
        table.add("A", 1);
        assertNull(table.cut("B"));
        assertTrue(table.has("A"));
    }

    @Test
    public void testSetExistingAndNonExisting() {
        table.add("Key1", 10);
        assertEquals(20, table.set("Key1", 20));
        assertEquals(20, table.get("Key1"));

        assertNull(table.set("Key2", 30));
        assertFalse(table.has("Key2"));
    }

    @Test
    public void testNullKeyAndValue() {
        table.add(null, 100);
        assertTrue(table.has(null));
        assertEquals(100, table.get(null));

        table.add("NullValue", null);
        assertTrue(table.has("NullValue"));
        assertNull(table.get("NullValue"));

        assertEquals(100, table.cut(null));
        assertFalse(table.has(null));
    }

    @Test
    public void testResize() {
        int count = 50;
        for (int i = 0; i < count; i++) {
            table.add("Key" + i, i);
        }

        for (int i = 0; i < count; i++) {
            assertTrue(table.has("Key" + i));
            assertEquals(i, table.get("Key" + i));
        }
    }

    @Test
    public void testEqualsSameAndDifferent() {
        HashTable<String, Integer> table2 = new HashTable<>();

        table.add("A", 1);
        table.add("B", 2);

        table2.add("A", 1);
        table2.add("B", 2);

        assertEquals(table, table2);

        table2.add("C", 3);
        assertNotEquals(table, table2);
    }

    @Test
    public void testIterator() {
        table.add("A", 1);
        table.add("B", 2);
        table.add("C", 3);

        List<String> keys = new ArrayList<>();
        List<Integer> values = new ArrayList<>();

        for (Node<String, Integer> node : table) {
            keys.add(node.toString());
            values.add(node.toString() != null ? 1 : 0);
        }

        int itemCount = 0;
        Iterator<Node<String, Integer>> iterator = table.iterator();
        while (iterator.hasNext()) {
            Node<String, Integer> node = iterator.next();
            itemCount++;
        }

        assertEquals(3, itemCount);
    }
}
