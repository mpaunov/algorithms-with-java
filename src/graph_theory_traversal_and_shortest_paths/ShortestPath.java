package graph_theory_traversal_and_shortest_paths;

import java.util.*;

// This file also contains some additional methods
public class ShortestPath {

    public static boolean[] visited;
    public static int[] prevNodes;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        int edges = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < edges; i++) {
            int[] paths = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            graph.get(paths[0]).add(paths[1]);
        }

        int source = Integer.parseInt(scanner.nextLine());
        int destination = Integer.parseInt(scanner.nextLine());

        visited = new boolean[n + 1];
        prevNodes = new int[n + 1];

        Arrays.fill(prevNodes, -1);

        bfs(graph, source, destination);

        List<Integer> path = new ArrayList<>();

        path.add(destination);

        int prevNode = prevNodes[destination];

        while (prevNode != -1) {
            path.add(prevNode);
            prevNode = prevNodes[prevNode];
        }

        System.out.println("Shortest path length is: " + (path.size() - 1));

        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }
    }

    private static void bfs(List<List<Integer>> graph, int source, int destination) {

        Deque<Integer> queue = new ArrayDeque<>();

        queue.offer(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (node == destination) {
                System.out.println();
                return;
            }
            for (int child : graph.get(node)) {
                if (!visited[child]) {
                    visited[child] = true;
                    prevNodes[child] = node;
                    queue.offer(child);
                }
            }
        }

    }

    public static List<Deque<Integer>> getConnectedComponents(List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        List<Deque<Integer>> components = new ArrayList<>();

        for (int start = 0; start < graph.size(); start++) {
            if (!visited[start]) {
                components.add(new ArrayDeque<>());
                bfs(start, components, graph, visited);
            }
        }

        return components;
    }

    private static void bfs(int start, List<Deque<Integer>> components, List<List<Integer>> graph, boolean[] visited) {
        Deque<Integer> queue = new ArrayDeque<>();
        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            components.get(components.size() - 1).offer(node);

            for (int child : graph.get(node)) {
                if (!visited[child]) {
                    visited[child] = true;
                    queue.offer(child);
                }
            }
        }
    }

    private static void dfs(int node, List<Deque<Integer>> components, List<List<Integer>> graph, boolean[] visited) {
        if (!visited[node]) {
            visited[node] = true;
            for (int child : graph.get(node)) {
                dfs(child, components, graph, visited);
            }
            components.get(components.size() - 1).offer(node);
        }
    }

    public static List<String> topSort(Map<String, List<String>> graph) {

        List<String> sorted = new ArrayList<>();

        Set<String> visited = new HashSet<>();
        Set<String> detectCycles = new HashSet<>();

        for (Map.Entry<String, List<String>> node : graph.entrySet()) {
            dfs(node.getKey(), visited, graph, sorted, detectCycles);
        }

        Collections.reverse(sorted);

        return sorted;
    }

    public static void dfs(String key, Set<String> visited, Map<String, List<String>> graph, List<String> sorted, Set<String> detectCycles) {
        if (detectCycles.contains(key)) {
            throw new IllegalArgumentException();
        }
        if (!visited.contains(key)) {
            visited.add(key);
            detectCycles.add(key);
            for (String child : graph.get(key)) {
                dfs(child, visited, graph, sorted, detectCycles);
            }
            detectCycles.remove(key);
            sorted.add(key);
        }
    }
}

