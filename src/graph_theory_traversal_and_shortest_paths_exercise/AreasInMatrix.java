package graph_theory_traversal_and_shortest_paths_exercise;

import java.util.*;

public class AreasInMatrix {

    public static class Edge {
        int[] source;
        int[] dest;

        Edge(int sRow, int sCol) {
            this.source = new int[]{sRow, sCol};
        }

        public void setDest(int row, int col) {
            this.dest = new int[]{row, col};
        }
    }

    public static List<Edge> graph = new ArrayList<>();

    public static char[][] matrix;
    public static boolean[][] visited;
    public static boolean[] visitedNode;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = Integer.parseInt(scanner.nextLine());

        matrix = new char[rows][];
        visited = new boolean[rows][];

        for (int i = 0; i < rows; i++) {
            matrix[i] = scanner.nextLine().toCharArray();
            visited[i] = new boolean[matrix[i].length];
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (!visited[i][j]) {
                    dfs(i, j, matrix[i][j]);
                }
            }
        }

        visitedNode = new boolean[graph.size()];

        Map<Character, Integer> areas = new TreeMap<>();

        for (int i = 0; i < graph.size(); i++) {
            if (!visitedNode[i]) {
                Edge edge = graph.get(i);
                char key = matrix[edge.source[0]][edge.source[1]];
                areas.putIfAbsent(key, 0);
                areas.put(key, areas.get(key) + 1);
                bfs(i);
            }
        }

        System.out.println("Areas: " + areas.values().stream().mapToInt(e -> e).sum());

        for (Map.Entry<Character, Integer> entry : areas.entrySet()) {
            System.out.println("Letter '" + entry.getKey() + "' -> " + entry.getValue());
        }
    }

    private static void bfs(int source) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(source);

        visitedNode[source] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            Edge edge = graph.get(node);
            if (edge.dest != null) {
                visitedNode[node + 1] = true;
                queue.offer(node + 1);
            }
        }
    }

    private static void dfs(int row, int col, char areaSymbol) {
        visited[row][col] = true;

        Edge edge = new Edge(row, col);
        graph.add(edge);
        if (isInBounds(row, col + 1) && !visited[row][col + 1] && matrix[row][col + 1] == areaSymbol) {
            graph.get(graph.size() - 1).setDest(row, col + 1);
            dfs(row, col + 1, areaSymbol);
        }
        if (isInBounds(row, col - 1) && !visited[row][col - 1] && matrix[row][col - 1] == areaSymbol) {
            graph.get(graph.size() - 1).setDest(row, col - 1);
            dfs(row, col - 1, areaSymbol);
        }
        if (isInBounds(row + 1, col) && !visited[row + 1][col] && matrix[row + 1][col] == areaSymbol) {
            graph.get(graph.size() - 1).setDest(row + 1, col);
            dfs(row + 1, col, areaSymbol);
        }
        if (isInBounds(row - 1, col) && !visited[row - 1][col] && matrix[row - 1][col] == areaSymbol) {
            graph.get(graph.size() - 1).setDest(row - 1, col);
            dfs(row - 1, col, areaSymbol);
        }
    }

    private static boolean isInBounds(int row, int col) {
        return !isOutOfBounds(row, col);
    }

    private static boolean isOutOfBounds(int row, int col) {
        return row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length;
    }
}
