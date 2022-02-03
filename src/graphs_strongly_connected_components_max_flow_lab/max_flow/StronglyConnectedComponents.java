package graphs_strongly_connected_components_max_flow_lab.max_flow;

import java.util.*;

public class StronglyConnectedComponents {

    private static List<List<Integer>> stronglyConnectedComponents;
    private static boolean[] visited;
    private static Deque<Integer> stack = new ArrayDeque<>();
    private static List<Integer>[] reversedGraph;
    private static List<Integer>[] graph;

    
    public static List<List<Integer>> findStronglyConnectedComponents(List<Integer>[] targetGraph) {
        graph = targetGraph;
        visited = new boolean[graph.length];
        stronglyConnectedComponents = new ArrayList<>();

        for (int node = 0; node < graph.length; node++) {
            if (!visited[node]) {
                dfs(node);
            }
        }
        
        setReversedGraph();

        Arrays.fill(visited, false);

        while (!stack.isEmpty()) {
            int node = stack.pop();

            if (!visited[node]) {
                stronglyConnectedComponents.add(new ArrayList<>());
                reversedDfs(node);
            }
        }
        
        return stronglyConnectedComponents;
    }

    private static void reversedDfs(int node) {
        if (visited[node]) {
            return;
        }

        visited[node] = true;

        stronglyConnectedComponents.get(stronglyConnectedComponents.size() - 1).add(node);

        for (int child : reversedGraph[node]) {
            reversedDfs(child);
        }
    }

    @SuppressWarnings("unchecked")
    public static void setReversedGraph() {
        reversedGraph = new ArrayList[graph.length];

        for (int i = 0; i < reversedGraph.length; i++) {
            reversedGraph[i] = new ArrayList<>();
        }

        for (int node = 0; node < graph.length; node++) {
            for (int child = 0; child < graph[node].size(); child++) {
                int parent = graph[node].get(child);
                reversedGraph[parent].add(node);
            }
        }
    }

    private static void dfs(int node) {
        if (visited[node]) {
            return;
        }
        visited[node] = true;

        for (int child : graph[node]) {
            dfs(child);
        }

        stack.push(node);
    }
}
