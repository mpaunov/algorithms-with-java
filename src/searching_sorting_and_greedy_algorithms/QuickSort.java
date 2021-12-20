package searching_sorting_and_greedy_algorithms;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class QuickSort {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int[] arr = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        quickSort(arr, 0, arr.length - 1);

        String output = Arrays.stream(arr)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println(output);
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int index = partition(arr, left, right);
        quickSort(arr, left, index - 1);
        quickSort(arr, index + 1, right);
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, right);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
