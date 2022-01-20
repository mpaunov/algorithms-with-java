package graphs_bellmanFord_longest_path_in_dag_exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class BigTrip {

    public static List<int[]> edges = new ArrayList<>();
    public static int[][] graph;

    public static boolean[] visited;

    public static void main(String[] args) throws IOException {

        var reader = new BufferedReader(new InputStreamReader(System.in));

        int nodes = Integer.parseInt(reader.readLine());
        int e = Integer.parseInt(reader.readLine());

        graph = new int[nodes + 1][nodes + 1];


        for (int i = 0; i < e; i++) {
            int[] nextLine = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int source = nextLine[0];
            int dest = nextLine[1];
            int weight = nextLine[2];

            graph[source][dest] = weight;
            edges.add(new int[]{source, dest});

        }

        int source = Integer.parseInt(reader.readLine());
        int destination = Integer.parseInt(reader.readLine());

        visited = new boolean[nodes + 1];
        Deque<Integer> sortedNodes = new ArrayDeque<>();

        for (int i = 1; i < graph.length; i++) {
            topSortDfs(i, sortedNodes);
        }

        int[] distances = new int[nodes + 1];
        Arrays.fill(distances, Integer.MIN_VALUE);
        distances[source] = 0;

        int[] prev = new int[nodes + 1];
        Arrays.fill(prev, -1);

        for (Integer sortedNode : sortedNodes) {
            for (int[] edge : edges) {
                int from = edge[0];
                int to = edge[1];
                int weight = graph[from][to];
                if (weight != 0) {
                    if (distances[from] + weight > distances[to]) {
                        distances[to] = distances[from] + weight;
                        prev[to] = from;
                    }
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        path.add(destination);
        int current = prev[destination];
        while (current != -1) {
            path.add(current);
            current = prev[current];
        }
        Collections.reverse(path);
        System.out.println(distances[destination]);

        System.out.println(path.stream().map(String::valueOf).collect(Collectors.joining(" ")));

    }

    private static void topSortDfs(int node, Deque<Integer> sortedNodes) {

        if (visited[node]) {
            return;
        }
        visited[node] = true;
        for (int i = 1; i < graph[node].length; i++) {
            if (graph[node][i] != 0) {
                topSortDfs(i, sortedNodes);
            }
        }

        sortedNodes.push(node);
    }
}
