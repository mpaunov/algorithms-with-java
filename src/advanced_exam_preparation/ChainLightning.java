package advanced_exam_preparation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ChainLightning {

    public static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public static List<Edge>[] graph;
    public static boolean[] visited;
    public static int[] damage;
    public static int max = Integer.MIN_VALUE;

    public static Map<Integer, List<Integer>> forest = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodes = Integer.parseInt(reader.readLine());
        int edges = Integer.parseInt(reader.readLine());
        int hitsCount = Integer.parseInt(reader.readLine());

        visited = new boolean[nodes];
        graph = new ArrayList[nodes];
        damage = new int[nodes];

        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = tokens[0];
            int to = tokens[1];
            int weight = tokens[2];

            Edge edge = new Edge(from, to, weight);

            if (graph[from] == null) {
                graph[from] = new ArrayList<>();
            }
            if (graph[to] == null) {
                graph[to] = new ArrayList<>();
            }

            graph[from].add(edge);
            graph[to].add(edge);
        }

        for (int i = 0; i < nodes; i++) {
            if (!visited[i]) {
                msf(i);
            }
        }

        for (int i = 0; i < hitsCount; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int parent = tokens[0];
            int power = tokens[1];

            damageNodes(parent, parent, power);
        }

        System.out.println(max);
    }

    private static void damageNodes(int parent, int next, int power) {

        if (power < 1) {
            return;
        }

        damage[parent] += power;
        if (max < damage[parent]) {
            max = damage[parent];
        }
        if (forest.get(parent) != null) {
            for (int child : forest.get(parent)) {
                if (child != next) {
                    damageNodes(child, parent, power / 2);
                }
            }
        }
    }

    private static void msf(int node) {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        visitNodes(node, queue);

        while (!queue.isEmpty()) {
            Edge minEdge = queue.poll();

            int from = minEdge.from;
            int to = minEdge.to;

            if (visited[from] && visited[to]) {
                continue;
            }

            forest.putIfAbsent(from, new ArrayList<>());
            forest.putIfAbsent(to, new ArrayList<>());

            forest.get(from).add(to);
            forest.get(to).add(from);

            if (!visited[from]) {
                visitNodes(from, queue);
            } else {
                visitNodes(to, queue);
            }
        }
    }

    private static void visitNodes(int node, PriorityQueue<Edge> queue) {

        visited[node] = true;
        if (graph[node] != null) {
            for (Edge edge : graph[node]) {
                int nextNode = node == edge.from ? edge.to : edge.from;
                if (!visited[nextNode]) {
                    queue.offer(edge);
                }
            }
        }
    }
}

