package graphs_bellmanFord_longest_path_in_dag_lab;

import java.util.*;
import java.util.stream.Collectors;

public class BellmanFord {

    public static int[][] graph;
    public static int[] distance;
    public static int[] prev;

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

        try {
            bellmanFord(source);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            return;
        }
        List<Integer> path = new ArrayList<>();

        path.add(destination);

        int node = prev[destination];

        while (node != -1) {
            path.add(node);
            node = prev[node];
        }

        Collections.reverse(path);

        System.out.println(path.stream().map(String::valueOf)
                .collect(Collectors.joining(" ")));

        System.out.println(distance[destination]);
    }

    private static void bellmanFord(int sourceNode) {
        // Generic Shortest Paths Algo
        // 1 Init dist and prev
        // set values to dist and prev

        distance = new int[graph.length];
        Arrays.fill(distance, Integer.MAX_VALUE);
        prev = new int[graph.length];
        Arrays.fill(prev, -1);

        distance[sourceNode] = 0;

        for (int i = 1; i < graph.length - 1; i++) {
            for (int r = 1; r < graph.length; r++) {
                for (int c = 1; c < graph[r].length; c++) {
                    int weight = graph[r][c];
                    if (weight != 0) {
                        int source = r;
                        int dest = c;
                        if (distance[source] != Integer.MAX_VALUE) {
                            int newValue = distance[source] + weight;
                            if (newValue < distance[dest]) {
                                distance[dest] = newValue;
                                prev[dest] = source;
                            }
                        }
                    }
                }
            }
        }


        for (int r = 1; r < graph.length; r++) {
            for (int c = 1; c < graph[r].length; c++) {
                int weight = graph[r][c];
                if (weight != 0) {
                    int source = r;
                    int dest = c;
                    if (distance[source] != Integer.MAX_VALUE) {
                        int newValue = distance[source] + weight;
                        if (newValue < distance[dest]) {
                            throw new IllegalStateException("Negative Cycle Detected");
                        }
                    }
                }
            }
        }
    }
}
