package searching_sorting_and_greedy_algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class SetCover {

    static BufferedReader reader;

    static int[] universe;

    static List<int[]> sets;

    static List<int[]> chosenSets;

    public static void main(String[] args) throws IOException {
        init();
        chosenSets = chooseSets(sets, universe);
        print();
    }

    public static List<int[]> chooseSets(List<int[]> sets, int[] universe) {

        List<int[]> chosen = new ArrayList<>();

        Set<Integer> universeAsSet = stream(universe).boxed().collect(Collectors.toSet());

        while (!universeAsSet.isEmpty()) {
            int containedElementsCount = 0;
            int[] chosenSet = sets.get(0);
            for (int[] set : sets) {
                int count = 0;
                for (int element : set) {
                    if (universeAsSet.contains(element)) {
                        count++;
                    }
                }

                if (containedElementsCount < count) {
                    containedElementsCount = count;
                    chosenSet = set;
                }
            }
            chosen.add(chosenSet);
            stream(chosenSet).forEach(universeAsSet::remove);
        }

        return chosen;
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Sets to take (%d):%n", chosenSets.size()));
        for (int[] set : chosenSets) {
            sb.append("{ ");
            sb.append(Arrays.toString(set).replaceAll("\\[|]", ""));
            sb.append(" }").append(System.lineSeparator());
        }
        System.out.println(sb);
    }

    private static void init() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        universe = stream(reader.readLine().substring(10).split(", "))
                .mapToInt(Integer::parseInt).toArray();
        chosenSets = new ArrayList<>();
        sets = new ArrayList<>();
        int numberOfSets = Integer.parseInt(reader.readLine().substring(16));
        for (int i = 0; i < numberOfSets; i++) {
            sets.add(stream(reader.readLine().split(", "))
                    .mapToInt(Integer::parseInt).toArray());
        }
    }
}
