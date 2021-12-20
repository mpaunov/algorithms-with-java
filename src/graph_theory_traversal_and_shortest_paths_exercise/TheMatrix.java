package graph_theory_traversal_and_shortest_paths_exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TheMatrix {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] dimensions = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int row = dimensions[0];
        int col = dimensions[1];

        char[][] matrix = new char[row][col];

        for (int i = 0; i < row; i++) {
            String[] line = reader.readLine().split("\\s+");
            for (int j = 0; j < line.length; j++) {
                matrix[i][j] = line[j].charAt(0);
            }
        }

        char fillChar = reader.readLine().charAt(0);

        int[] coordinates = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int rowIndex = coordinates[0];
        int colIndex = coordinates[1];

        char toBeReplaced = matrix[rowIndex][colIndex];

        replaceCharacters(matrix, fillChar, toBeReplaced, rowIndex, colIndex);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    private static void replaceCharacters(char[][] matrix, char fillChar, char toBeReplaced, int row, int col) {
        if (isOutOfBounds(matrix, row, col) || matrix[row][col] != toBeReplaced) {
            return;
        }
        matrix[row][col] = fillChar;

        replaceCharacters(matrix, fillChar, toBeReplaced, row + 1, col);
        replaceCharacters(matrix, fillChar, toBeReplaced, row - 1, col);
        replaceCharacters(matrix, fillChar, toBeReplaced, row, col + 1);
        replaceCharacters(matrix, fillChar, toBeReplaced, row, col - 1);

    }

    private static boolean isOutOfBounds(char[][] matrix, int row, int col) {
        return row < 0 || col < 0 || row > matrix.length - 1 || col > matrix[0].length - 1;
    }
}

