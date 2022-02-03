package graphs_strongly_connected_components_max_flow_exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class MaximumTasks {
    private static boolean[][] graph;
    private static int[] parents;
    private static int source;
    private static int sink;

    private static boolean bfs() {
        boolean[] visited = new boolean[graph.length];

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int child = graph[node].length - 1; child >= 0; --child)
                if (graph[node][child] && !visited[child]) {
                    visited[child] = true;
                    parents[child] = node;
                    queue.offer(child);
                }
        }

        return visited[sink];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int persons = Integer.parseInt(bf.readLine().substring("Persons: ".length()));
        int tasks = Integer.parseInt(bf.readLine().substring("Tasks: ".length()));
        int nodes = persons + tasks + 2;//first persons then tasks
        source = nodes - 2;//node before last
        sink = nodes - 1;//last node
        graph = new boolean[nodes][nodes];
        parents = new int[nodes];
        parents[source] = -1;
        //read task/person table
        for (int i = 0; i < persons; ++i) {
            String line = bf.readLine();
            for (int j = 0; j < tasks; ++j)
                if (line.charAt(j) == 'Y')
                    graph[i][persons + j] = true;
        }
        //add source to persons edges
        for (int i = 0; i < persons; ++i)
            graph[source][i] = true;
        //add tasks to sink edges
        for (int i = 0; i < tasks; ++i)
            graph[persons + i][sink] = true;

        //Edmonds-Karp
        while (bfs()) {
            int node = sink;
            while (node != source) {
                graph[parents[node]][node] = false;
                graph[node][parents[node]] = true;
                node = parents[node];
            }
        }

        StringBuilder result = new StringBuilder(16384);
        for (int i = 0; i < persons; ++i)
            for (int j = 0; j < tasks; ++j)
                if (graph[persons + j][i])//back edge from j-th task to i-th person, so there is flow there
                    result.append((char) ('A' + i)).append('-').append(j + 1).append('\n');//j -> j+1, renumber tasks

        System.out.println(result);

        bf.close();
    }
}