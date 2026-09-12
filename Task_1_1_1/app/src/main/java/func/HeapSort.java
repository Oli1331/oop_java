package func;

public class HeapSort {

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

    /** функция спуска элементов кучи */ 
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

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
