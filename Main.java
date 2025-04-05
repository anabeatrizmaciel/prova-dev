import java.util.*;

public class Main {

    /**
     * The function below will:
     * - Define the input set A with elements
     * - Call the getSubSets function to compute all subsets of A
     * - Print each subset returned by the function in a formatted list
     */

    public static Set<Set<Integer>> getSubSets(Set<Integer> inputSet) {
        Set<Set<Integer>> result = new HashSet<>();
        List<Integer> inputList = new ArrayList<>(inputSet);
        generateSubsets(inputList, 0, new HashSet<>(), result);
        return result;
    }

    private static void generateSubsets(List<Integer> input, int index, Set<Integer> current, Set<Set<Integer>> result) {
        if (index == input.size()) {
            result.add(new HashSet<>(current));
            return;
        }

        generateSubsets(input, index + 1, current, result);

        current.add(input.get(index));
        generateSubsets(input, index + 1, current, result);
        current.remove(input.get(index));
    }

    public static void main(String[] args) {
        Set<Integer> A = new HashSet<>();
        A.add(1);
        A.add(2);
        A.add(3);

        Set<Set<Integer>> allSubsets = getSubSets(A);

        // Formatting output
        List<List<Integer>> formatted = new ArrayList<>();
        for (Set<Integer> subset : allSubsets) {
            List<Integer> list = new ArrayList<>(subset);
            Collections.sort(list);
            formatted.add(list);
        }

        formatted.sort((a, b) -> {
            if (a.size() != b.size()) return Integer.compare(b.size(), a.size());
            for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
                int cmp = Integer.compare(a.get(i), b.get(i));
                if (cmp != 0) return cmp;
            }
            return Integer.compare(a.size(), b.size());
        });

        System.out.print("[");
        for (int i = 0; i < formatted.size(); i++) {
            System.out.print(formatted.get(i));
            if (i < formatted.size() - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
