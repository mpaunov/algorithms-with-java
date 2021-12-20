package graph_theory_traversal_and_shortest_paths_exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Salaries {

    public static List<List<Integer>> graph = new ArrayList<>();

    public static long[] salaries;
    public static boolean[] visited;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int employees = Integer.parseInt(scanner.nextLine());

        salaries = new long[employees];
        visited = new boolean[employees];

        int[] managersCount = new int[employees];

        for (int i = 0; i < employees; i++) {
            graph.add(new ArrayList<>());
            String line = scanner.nextLine();

            for (int emp = 0; emp < line.length(); emp++) {
                char letter = line.charAt(emp);
                if (letter == 'Y') {
                    managersCount[emp]++;
                    graph.get(i).add(emp);
                }
            }
        }

        List<Integer> sources = new ArrayList<>();

        for (int i = 0; i < managersCount.length; i++) {
            if (managersCount[i] == 0) {
                sources.add(i);
            }
        }

        for (Integer source : sources) {
            dfs(source);
        }


        long sum = Arrays.stream(salaries)
                .sum();

        System.out.println(sum);
    }

    private static void dfs(int node) {
        if (visited[node]) {
            return;
        }

        visited[node] = true;

        for (Integer child : graph.get(node)) {
            if (!visited[child]) {
                dfs(child);
                long sum = graph.get(child).stream()
                        .mapToLong(c -> salaries[c])
                        .sum();

                salaries[child] = sum == 0 ? 1 : sum;
            }
        }

        long sum = graph.get(node).stream()
                .mapToLong(c -> salaries[c])
                .sum();

        salaries[node] = sum == 0 ? 1 : sum;
    }
}

