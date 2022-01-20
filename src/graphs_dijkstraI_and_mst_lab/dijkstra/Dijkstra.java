package graphs_dijkstraI_and_mst_lab.dijkstra;

import java.util.*;

public class Dijkstra {

    public static List<Integer> dijkstraAlgorithm(int[][] graph, int sourceNode, int destinationNode) {

        int[] distances = new int[graph.length];
        int[] prev = new int[graph.length];

        boolean[] visited = new boolean[graph.length];

        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);

        distances[sourceNode] = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(node -> distances[node]));

        queue.offer(sourceNode);

        while (!queue.isEmpty()) {
            int parent = queue.poll();
            visited[parent] = true;
            int[] children = graph[parent];

            for (int childNode = 0; childNode < children.length; childNode++) {
                if (children[childNode] != 0 && !visited[childNode]) {
                    queue.offer(childNode);

                    int newDistance = distances[parent] + graph[parent][childNode];

                    if (newDistance < distances[childNode]) {
                        distances[childNode] = newDistance;
                        prev[childNode] = parent;
                    }
                }
            }
        }

        List<Integer> path = new ArrayList<>();

        path.add(destinationNode);

        int n = prev[destinationNode];

        while (n != -1) {
            path.add(n);
            n = prev[n];
        }

        Collections.reverse(path);

        return path.size() == 1 ? null : path;
    }
}
