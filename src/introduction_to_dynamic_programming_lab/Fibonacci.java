package introduction_to_dynamic_programming_lab;

import java.util.Scanner;

public class Fibonacci {
    public static long[] dp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        dp = new long[n + 1];

        long fib = calcFib(n);

        System.out.println(fib);
    }

    private static long calcFib(int n) {
        if (n <= 2) {
            return 1;
        }

        if (dp[n] != 0) {
            return dp[n];
        }

        return dp[n] = calcFib(n - 1) + calcFib(n - 2);
    }
}
