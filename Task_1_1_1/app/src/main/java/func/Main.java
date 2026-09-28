package func;

import java.util.Scanner;

/**
 * Class for test my_compile.
 */
public class Main {
    /**
     * Base method
     * 
     * @param args command line arguments.
     */
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }
        HeapSort.sort(arr);
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        scan.close();
    }
}
