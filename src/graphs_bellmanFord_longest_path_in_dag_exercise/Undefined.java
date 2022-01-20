package graphs_bellmanFord_longest_path_in_dag_exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Undefined {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nodes = Integer.parseInt(scanner.nextLine());
        int edgesCount = Integer.parseInt(scanner.nextLine());

        int[][] graph = new int[nodes + 1][nodes + 1];
        List<int[]> edges = new ArrayList<>();

        for (int i = 0; i < edgesCount; i++) {
            int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            graph[tokens[0]][tokens[1]] = tokens[2];
            edges.add(new int[]{tokens[0], tokens[1]});
        }

        int[] distances = new int[nodes + 1];


        Arrays.fill(distances, Integer.MAX_VALUE);

        int[] prev = new int[nodes + 1];

        Arrays.fill(prev, -1);

        int source = Integer.parseInt(scanner.nextLine());

        distances[source] = 0;

        int destination = Integer.parseInt(scanner.nextLine());

        boolean hasNegativeCycle = false;

        for (int i = 0; i < nodes - 1; i++) {
            for (int[] edge : edges) {
                int from = edge[0];
                int to = edge[1];
                if (distances[from] != Integer.MAX_VALUE) {
                    int newDistance = distances[from] + graph[from][to];
                    if (newDistance < distances[to]) {
                        distances[to] = newDistance;
                        prev[to] = from;
                    }
                }
            }
        }

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            if (distances[from] != Integer.MAX_VALUE) {
                int newDistance = distances[from] + graph[from][to];
                if (newDistance < distances[to]) {
                    hasNegativeCycle = true;
                    break;
                }
            }
        }

        if (hasNegativeCycle) {
            System.out.println("Undefined");
        } else {
            List<Integer> path = new ArrayList<>();

            path.add(destination);

            int node = prev[destination];

            while (node != -1) {
                path.add(node);
                node = prev[node];
            }

            for (int i = path.size() - 1; i >= 0; i--) {
                System.out.print(path.get(i) + " ");
            }
            System.out.println();
            System.out.println(distances[destination]);
        }
    }
}