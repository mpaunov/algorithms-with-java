package dynamic_programming_advanced_exercise;

import java.util.*;
import java.util.stream.Collectors;

public class ZigzagMatrix {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = Integer.parseInt(scanner.nextLine());
        int cols = Integer.parseInt(scanner.nextLine());

        int[][] matrix = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            matrix[row] = Arrays.stream(scanner.nextLine().split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int[][] maxDp = new int[rows][cols];
        int[][] prevs = new int[rows][cols];

        for (int row = 1; row < rows; row++) {
            maxDp[row][0] = matrix[row][0];
        }

        for (int col = 1; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                int prevMax = 0;

                if (col % 2 != 0) {
                    for (int i = row + 1; i < rows; i++) {
                        if (maxDp[i][col - 1] > prevMax) {
                            prevMax = maxDp[i][col - 1];
                            prevs[row][col] = i;
                        }
                    }
                } else {
                    for (int i = 0; i < row; i++) {
                        if (maxDp[i][col - 1] > prevMax) {
                            prevMax = maxDp[i][col - 1];
                            prevs[row][col] = i;
                        }
                    }
                }
                maxDp[row][col] = prevMax + matrix[row][col];
            }
        }

        List<Integer> result = new ArrayList<>();

        int index = cols - 1;
        int rowIndex = 0;

        int max = -1;

        for (int row = 0; row < maxDp.length; row++) {
            if (maxDp[row][index] > max) {
                rowIndex = row;
                max = maxDp[row][index];
            }
        }

        while (index >= 0) {
            result.add(matrix[rowIndex][index]);
            rowIndex = prevs[rowIndex][index];
            index--;
        }

        Collections.reverse(result);

        System.out.println(result.stream().mapToInt(e -> e).sum() + " = " + result.stream().map(String::valueOf)
                .collect(Collectors.joining(" + ")));


    }
}

