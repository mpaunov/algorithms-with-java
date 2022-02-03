package dynamic_programming_advanced_lab;

import java.util.Scanner;

public class LCS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String first = scanner.nextLine();
        String second = scanner.nextLine();

        int[][] dp = new int[first.length() + 1][second.length() + 1];

        for (int rowIndex = 1; rowIndex <= first.length(); rowIndex++) {
            for (int colIndex = 1; colIndex <= second.length(); colIndex++) {
                if (first.charAt(rowIndex - 1) == second.charAt(colIndex - 1)) {
                    dp[rowIndex][colIndex] =
                            dp[rowIndex - 1][colIndex - 1] + 1;
                } else {
                    dp[rowIndex][colIndex] = Math.max(
                            dp[rowIndex - 1][colIndex], dp[rowIndex][colIndex - 1]
                    );
                }
            }
        }

        System.out.println(dp[first.length()][second.length()]);
    }
}
