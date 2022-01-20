package graphs_bellmanFord_longest_path_in_dag_lab;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class LongestPath {

    public static int[][] graph;
    public static int[] distance;
    public static boolean[] visited;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nodes = Integer.parseInt(scanner.nextLine());
        int edges = Integer.parseInt(scanner.nextLine());

        graph = new int[nodes + 1][nodes + 1];

        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int source = tokens[0];
            int destination = tokens[1];
            int weight = tokens[2];

            graph[source][destination] = weight;
        }

        int source = Integer.parseInt(scanner.nextLine());
        int destination = Integer.parseInt(scanner.nextLine());

        distance = new int[graph.length];
        visited = new boolean[graph.length];

        ArrayDeque<Integer> sorted = new ArrayDeque<>();

        for (int i = 1; i < graph.length; i++) {
            topologicalSort(i, sorted);
        }

        while (!sorted.isEmpty()) {
            int node = sorted.pop();
            for (int i = 1; i < graph[node].length; i++) {
                int weight = graph[node][i];
                if (weight != 0) {
                    if (distance[node] + weight > distance[i]) {
                        distance[i] = distance[node] + weight;
                    }
                }
            }
        }

        System.out.println(distance[destination]);
    }

    private static void topologicalSort(int node, ArrayDeque<Integer> sorted) {

        if (visited[node]) {
            return;
        }
        visited[node] = true;

        for (int i = 1; i < graph[node].length; i++) {
            if (graph[node][i] != 0) {
                topologicalSort(i, sorted);
            }
        }

        sorted.push(node);
    }
}
