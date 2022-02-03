package graphs_strongly_connected_components_max_flow_lab.max_flow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArticulationPoints {

    public static List<Integer>[] graph;
    public static List<Integer> points;
    public static boolean[] visited;
    public static int[] parents;
    public static int[] depths;
    public static int[] lowpoints;

    public static List<Integer> findArticulationPoints(List<Integer>[] targetGraph) {
        graph = targetGraph;
        points = new ArrayList<>();
        visited = new boolean[graph.length];
        parents = new int[graph.length];
        depths = new int[graph.length];
        lowpoints = new int[graph.length];

        Arrays.fill(parents, -1);

        discoverArticulationPoints(0, 1);

        return points;
    }

    private static void discoverArticulationPoints(int node, int depth) {
        visited[node] = true;
        depths[node] = depth;
        lowpoints[node] = depth;

        int children = 0;

        boolean isArticulationPoint = false;

        for (int child : graph[node]) {
            if (!visited[child]) {
                parents[child] = node;
                children++;
                discoverArticulationPoints(child, depth + 1);
                if (lowpoints[child] >= depth) {
                    isArticulationPoint = true;
                }
                lowpoints[node] = Math.min(lowpoints[node], lowpoints[child]);
            } else if (parents[node] != child) {
                lowpoints[node] = Math.min(lowpoints[node], depths[child]);
            }
        }

        if ((parents[node] == -1 && children > 1) ||
                (parents[node] != -1 && isArticulationPoint)) {
            points.add(node);
        }
    }
}
