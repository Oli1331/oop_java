package func;

/**
 * 
 * HeapSort - class with method for sort array
 */
public class HeapSort {
    /**
     * Method for sort
     * 
     * @param arr data for sort
     */
    public static int[] sort(int[] arr) {

        /** строим max кучу */
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapify(arr, arr.length, i);
        }
        /** достаем элементы */
        for (int i = arr.length - 1; i >= 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
        return arr;
    }

    /** Function for down element */
    public static void heapify(int[] arr, int len, int n) {
        int left = n * 2 + 1;
        int right = n * 2 + 2;
        int p = n;
        if (left < len && arr[p] < arr[left]) {
            p = left;
        }

        if (right < len && arr[p] < arr[right]) {
            p = right;
        }

        if (p != n) {
            swap(arr, p, n);
            heapify(arr, len, p);
        }
    }

    /**
     * Swap element with index i and j
     * 
     * @param arr data, where swap elements
     * @param i   first index
     * @param j   seccond index
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
