package graph_theory_traversal_and_shortest_paths_exercise;

import java.util.*;

public class CyclesInGraph {

    public static Map<String, List<String>> graph = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();

        String source = null;

        while (!line.equals("End")) {

            String[] tokens = line.split("-");

            if (source == null) {
                source = tokens[0];
            }

            graph.putIfAbsent(tokens[0], new ArrayList<>());

            graph.get(tokens[0]).add(tokens[1]);

            line = scanner.nextLine();
        }

        Set<String> visited = new HashSet<>();
        Set<String> cycles = new HashSet<>();
        String out = "";
        try {

            for (String s : graph.keySet()) {
                if (!visited.contains(s)) {
                    dfs(s, visited, cycles);
                    out = "Acyclic: Yes";
                }
            }

        } catch (IllegalStateException ex) {
            out = ex.getMessage();
        }
        System.out.println(out);
    }

    private static void dfs(String source, Set<String> visited, Set<String> cycles) {
        if (cycles.contains(source)) {
            throw new IllegalStateException("Acyclic: No");
        }
        if (visited.contains(source)) {
            return;
        }
        cycles.add(source);
        visited.add(source);
        if (graph.get(source) == null) {
            return;
        }
        for (String child : graph.get(source)) {
            dfs(child, visited, cycles);
        }
        cycles.remove(source);
    }
}

