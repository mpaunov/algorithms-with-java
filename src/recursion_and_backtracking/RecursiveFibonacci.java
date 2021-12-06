package recursion_and_backtracking;

import java.util.Scanner;

public class RecursiveFibonacci {
    public static long[] memory;

    public static void main(String[] args) {
        int n = Integer.parseInt(new Scanner(System.in).nextLine());

        memory = new long[n + 1];

        long fib = fib(n);

        System.out.println(fib);
    }

    private static long fib(int n) {
        if (n <= 2) {
            return 1;
        }

        if (memory[n] != 0) {
            return memory[n];
        }

        return  memory[n] = fib(n - 1) + fib(n - 2);
    }
}
