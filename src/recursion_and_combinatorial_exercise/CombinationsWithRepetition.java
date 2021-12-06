package recursion_and_combinatorial_exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CombinationsWithRepetition {
    public static int[] arr;
    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(reader.readLine());

        int k = Integer.parseInt(reader.readLine());

        arr = new int[k];

        combinations(0, 1);
    }

    private static void combinations(int index, int start) {
        if (index == arr.length) {
            printArr();
            return;
        }

        for (int i = start; i <= n; i++) {
            arr[index] = i;
            combinations(index + 1, i);
        }
    }

    private static void printArr() {
        for (int n : arr) {
            System.out.print(n + " ");
        }
        System.out.println();
    }
}
