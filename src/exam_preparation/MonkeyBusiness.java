package exam_preparation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MonkeyBusiness {

    public static int n;
    public static int solutions = 0;
    public static int[] expression;
    public static StringBuilder out = new StringBuilder();
    public static int[] numbers;

    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(reader.readLine());

        expression = new int[n];
        numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = i + 1;
        }

        combinationsWithoutRep(0);

        out.append("Total Solutions: ").append(solutions);
        System.out.println(out.toString());
    }

    private static void combinationsWithoutRep(int index) {
        if (index >= n) {
            printSolution();
        } else {
            expression[index] = numbers[index];
            combinationsWithoutRep(index + 1);
            expression[index] = -numbers[index];
            combinationsWithoutRep(index + 1);
        }
    }

    private static void printSolution() {
        int sum = Arrays.stream(expression).sum();
        if (sum == 0) {
            solutions++;
            for (int value : expression) {
                String numberAsStr = value > 0 ? "+" + value : String.valueOf(value);
                out.append(numberAsStr).append(" ");
            }
            out.append(System.lineSeparator());
        }
    }
}

