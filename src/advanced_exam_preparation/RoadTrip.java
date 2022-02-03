package advanced_exam_preparation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RoadTrip {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] values = Arrays.stream(reader.readLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] capacities = Arrays.stream(reader.readLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int trunkCapacity = Integer.parseInt(reader.readLine());

        int[][] dp = new int[values.length + 1][trunkCapacity + 1];

        for (int i = 1; i <= values.length; i++) {
            for (int j = 1; j <= trunkCapacity; j++) {
                if (capacities[i - 1] <= j) {
                    dp[i][j] = Math.max(values[i - 1] + dp[i - 1][j - capacities[i - 1]],
                            dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println("Maximum value: " + dp[values.length][trunkCapacity]);
    }
}

