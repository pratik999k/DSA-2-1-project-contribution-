import java.util.*;

public class DiameterDP {

    static int diameter;
    private static List<Integer> bestPath;

    public static Result findResult(int start, List<List<Integer>> tree) {

        diameter = 0;
        bestPath = new ArrayList<>(Collections.singletonList(start));

        dfs(start, -1, tree);

        return new Result(diameter, bestPath);
    }

    public static void find(int start, List<List<Integer>> tree) {
        Result result = findResult(start, tree);
        System.out.println("DP Diameter Length: " + result.getLength() + " edges");
        System.out.println("DP Diameter Path: " + result.getPath());
    }

    private static DownwardPath dfs(int node, int parent,
                                    List<List<Integer>> tree) {

        DownwardPath longest = new DownwardPath(0,
                new ArrayList<>(Collections.singletonList(node)));
        DownwardPath secondLongest = new DownwardPath(0,
                new ArrayList<>());

        for (int next : tree.get(node)) {

            if (next == parent)
                continue;

            DownwardPath childPath = dfs(next, node, tree);
            DownwardPath path = new DownwardPath(childPath.length + 1,
                    prepend(node, childPath.path));

            if (path.length > longest.length) {
                secondLongest = longest;
                longest = path;
            } else if (path.length > secondLongest.length) {
                secondLongest = path;
            }
        }

        int throughNode = longest.length + secondLongest.length;
        if (throughNode > diameter) {
            diameter = throughNode;
            bestPath = joinPaths(longest.path, node, secondLongest.path);
        }

        return longest;
    }

    private static List<Integer> prepend(int node, List<Integer> path) {
        List<Integer> result = new ArrayList<>();
        result.add(node);
        result.addAll(path);
        return result;
    }

    private static List<Integer> joinPaths(List<Integer> firstPath, int node,
                                           List<Integer> secondPath) {
        List<Integer> result = new ArrayList<>();
        for (int i = firstPath.size() - 1; i >= 1; i--) {
            result.add(firstPath.get(i));
        }
        result.add(node);
        for (int i = 1; i < secondPath.size(); i++) {
            result.add(secondPath.get(i));
        }
        return result;
    }

    public static class Result {
        private final int length;
        private final List<Integer> path;

        Result(int length, List<Integer> path) {
            this.length = length;
            this.path = Collections.unmodifiableList(new ArrayList<>(path));
        }

        public int getLength() {
            return length;
        }

        public List<Integer> getPath() {
            return path;
        }
    }

    private static class DownwardPath {
        private final int length;
        private final List<Integer> path;

        DownwardPath(int length, List<Integer> path) {
            this.length = length;
            this.path = path;
        }
    }
}