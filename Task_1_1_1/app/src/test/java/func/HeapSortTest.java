package func;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;

// import func.HeapSort;

public class HeapSortTest {

    @Test
    public void testBaseSort() {
        assertArrayEquals(HeapSort.sort(new int[] { 5, 4, 3, 2, 1 }), new int[] { 1, 2, 3, 4, 5 });
        assertArrayEquals(HeapSort.sort(new int[] { 9, 8, 7 }), new int[] { 7, 8, 9 });
        assertArrayEquals(HeapSort.sort(new int[] { 7, 8, 9 }), new int[] { 7, 8, 9 });
        assertArrayEquals(HeapSort.sort(new int[] {}), new int[] {});
    }
}
