package recursion_and_combinatorial_exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectedAreasInMatrix {
    public static char[][] matrix;
    public static List<int[]> areas;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int r = Integer.parseInt(scanner.nextLine());
        int c = Integer.parseInt(scanner.nextLine());

        matrix = new char[r][c];

        for (int i = 0; i < r; i++) {
            matrix[i] = scanner.nextLine().toCharArray();
        }

        areas = new ArrayList<>();

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == '-') {
                    areas.add(new int[]{row, col, 0});
                    findAreas(row, col);
                }
            }
        }

        System.out.println("Total areas found: " + areas.size());

        AtomicInteger counter = new AtomicInteger(1);

        areas.stream()
                .sorted((f, s) -> Integer.compare(s[2], f[2]))
                .forEach(a -> {
                    System.out.println("Area #" + counter.getAndIncrement()
                            + " at (" + a[0] + ", " + a[1] + "), size: " + a[2]);
                });

    }

    private static void findAreas(int row, int col) {
        if (isOutOfBounds(row, col) || isNotTraversal(row, col)) {
            return;
        }

        matrix[row][col] = 'V';

        areas.get(areas.size() - 1)[2]++;

        findAreas(row + 1, col);
        findAreas(row, col + 1);
        findAreas(row - 1, col);
        findAreas(row, col - 1);
    }

    private static boolean isNotTraversal(int row, int col) {
        return matrix[row][col] == 'V' || matrix[row][col] == '*';
    }

    private static boolean isOutOfBounds(int row, int col) {
        return row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length;
    }
}
