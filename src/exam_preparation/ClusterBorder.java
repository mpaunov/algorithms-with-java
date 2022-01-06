package exam_preparation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClusterBorder {
    public static void main(String[] args) throws IOException {

        var reader = new BufferedReader(new InputStreamReader(System.in));

        int[] singleShipTime = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] pairShipTime = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] dp = new int[singleShipTime.length + 1];

        dp[1] = singleShipTime[0];

        for (int i = 2; i <= singleShipTime.length; i++) {
            dp[i] = Math.min(dp[i - 1] + singleShipTime[i - 1], dp[i - 2] + pairShipTime[i - 2]);
        }

        System.out.println("Optimal Time: " + dp[singleShipTime.length]);

        List<String> out = new ArrayList<>();

        for (int i = dp.length - 1; i > 0; i--) {
            int timeDiffForLatestTwo = dp[i] - dp[i - 1];
            if (timeDiffForLatestTwo == singleShipTime[i - 1]) {
                out.add("Single " + i);
            } else {
                out.add("Pair of " + (i - 1) + " and " + i);
                i--;
            }
        }

        for (int i = out.size() - 1; i >= 0; i--) {
            System.out.println(out.get(i));
        }
    }
}

