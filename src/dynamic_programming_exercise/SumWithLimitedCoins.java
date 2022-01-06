package dynamic_programming_exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SumWithLimitedCoins {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] array = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        int target = Integer.parseInt(br.readLine());

        System.out.println(calcSum(array, target));

    }

    private static int calcSum(int[] coins, int target) {
        Map<Integer, Integer> result = new HashMap<>();

        int answer = 0;

        result.put(0, 0);

        for (int currentCoin : coins) {
            for (Integer number : new ArrayList<>(result.keySet())) {
                int newSum = currentCoin + number;

                if (newSum == target) {
                    answer++;
                }

                if (!result.containsKey(newSum)) {
                    result.put(newSum, number);
                }
            }
        }

        return answer;
    }
}
