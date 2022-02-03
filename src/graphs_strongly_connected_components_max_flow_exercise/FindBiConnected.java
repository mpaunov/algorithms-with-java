package graphs_strongly_connected_components_max_flow_exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FindBiConnected {
    private static int[][] graph;
    private static int[] lengths;
    private static int[] depths;
    private static int[] lowPoints;
    private static int[] parents;

    private static List<List<Integer>> result;

    private static void dfs(int node, int depth, List<Integer> component) {
        depths[node] = depth;
        lowPoints[node] = depth;
        for (int childIndex = 0; childIndex < lengths[node]; ++childIndex) {
            int child = graph[node][childIndex];
            if (depths[child] == 0) {//depth == 0 <=> child is not visited
                parents[child] = node;
                List<Integer> childComponent = new ArrayList<>();
                dfs(child, depth + 1, childComponent);
                if (lowPoints[child] >= depths[node] || parents[node] == -1) {
                    childComponent.add(node);
                    result.add(childComponent);
                } else {
                    component.addAll(childComponent);
                }
                lowPoints[node] = Math.min(lowPoints[node], lowPoints[child]);
            } else if (child != parents[node])
                lowPoints[node] = Math.min(lowPoints[node], depths[child]);
        }
        component.add(node);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int nodes = Integer.parseInt(bf.readLine().substring("Nodes: ".length()));
        int edges = Integer.parseInt(bf.readLine().substring("Edges: ".length()));
        graph = new int[nodes][nodes];
        lengths = new int[nodes];
        for (int i = 0; i < edges; ++i) {
            String[] tokens = bf.readLine().split(" ");
            int startNode = Integer.parseInt(tokens[0]);
            int endNode = Integer.parseInt(tokens[1]);
            graph[startNode][lengths[startNode]++] = endNode;
            graph[endNode][lengths[endNode]++] = startNode;
        }
        depths = new int[nodes];
        lowPoints = new int[nodes];
        parents = new int[nodes];
        parents[0] = -1;
        result = new ArrayList<>(nodes);
        dfs(0, 1, new ArrayList<>(nodes));

        System.out.println("Number of bi-connected components: " + result.size());

        bf.close();
    }
}