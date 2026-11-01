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

/**
 * Tests for {@link HashTable}.
 */
public class HashTableTest {

    private HashTable<String, Integer> table;

    /**
     * Setup test fixture before each test method.
     */
    @BeforeEach
    public void replaceUp() {
        table = new HashTable<>();
    }

    /**
     * Test adding and retrieving elements.
     */
    @Test
    public void testPutAndGet() {
        assertNull(table.put("One", 1));
        assertNull(table.put("Two", 2));

        assertEquals(1, table.get("One"));
        assertEquals(2, table.get("Two"));
    }

    /**
     * Test overwriting value of an existing key.
     */
    @Test
    public void testPutOverwriteKey() {
        assertNull(table.put("Key", 100));
        assertEquals(100, table.put("Key", 200));
        assertEquals(200, table.get("Key"));
    }

    /**
     * Parameterized test for put and containsKey methods.
     *
     * @param key   test key.
     * @param value test value.
     */
    @ParameterizedTest
    @CsvSource({
            "apple, 10",
            "banana, 20",
            "cherry, 30"
    })
    public void testParameterizedPutAndContainsKey(String key, Integer value) {
        table.put(key, value);
        assertTrue(table.containsKey(key));
        assertEquals(value, table.get(key));
    }

    /**
     * Parameterized test for checking non-existing elements.
     *
     * @param key test key.
     */
    @ParameterizedTest
    @ValueSource(strings = {"nonExisting1", "nonExisting2", "unknown"})
    public void testContainsKeyAndGetNonExisting(String key) {
        assertFalse(table.containsKey(key));
        assertNull(table.get(key));
    }

    /**
     * Test removing existing element.
     */
    @Test
    public void testRemoveExistingElement() {
        table.put("A", 1);
        table.put("B", 2);

        assertEquals(1, table.remove("A"));
        assertFalse(table.containsKey("A"));
        assertNull(table.get("A"));
        assertTrue(table.containsKey("B"));
    }

    /**
     * Test removing non-existing element.
     */
    @Test
    public void testRemoveNonExistingElement() {
        table.put("A", 1);
        assertNull(table.remove("B"));
        assertTrue(table.containsKey("A"));
    }

    /**
     * Test setting existing and non-existing elements.
     */
    @Test
    public void testReplaceExistingAndNonExisting() {
        table.put("Key1", 10);
        assertEquals(20, table.replace("Key1", 20));
        assertEquals(20, table.get("Key1"));

        assertNull(table.replace("Key2", 30));
        assertFalse(table.containsKey("Key2"));
    }

    /**
     * Test working with null key and null value.
     */
    @Test
    public void testNullKeyAndValue() {
        table.put(null, 100);
        assertTrue(table.containsKey(null));
        assertEquals(100, table.get(null));

        table.put("NullValue", null);
        assertTrue(table.containsKey("NullValue"));
        assertNull(table.get("NullValue"));

        assertEquals(100, table.remove(null));
        assertFalse(table.containsKey(null));
    }

    /**
     * Test table resize functionality.
     */
    @Test
    public void testResize() {
        int count = 50;
        for (int i = 0; i < count; i++) {
            table.put("Key" + i, i);
        }

        for (int i = 0; i < count; i++) {
            assertTrue(table.containsKey("Key" + i));
            assertEquals(i, table.get("Key" + i));
        }
    }

    /**
     * Test equals method on equal and non-equal tables.
     */
    @Test
    public void testEqualsSameAndDifferent() {
        HashTable<String, Integer> table2 = new HashTable<>();

        table.put("A", 1);
        table.put("B", 2);

        table2.put("A", 1);
        table2.put("B", 2);

        assertEquals(table, table2);

        table2.put("C", 3);
        assertNotEquals(table, table2);
    }

    /**
     * Test iterator implementation.
     */
    @Test
    public void testIterator() {
        table.put("A", 1);
        table.put("B", 2);
        table.put("C", 3);

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
