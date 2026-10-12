import java.util.*;

public class DiameterDFS {

    static int farthestNode;
    static int maxDistance;
    static int[] parent;

    public static Result findResult(int start, List<List<Integer>> tree) {

        boolean[] visited = new boolean[tree.size()];
        parent = new int[tree.size()];
        Arrays.fill(parent, -1);

        maxDistance = -1;
        dfs(start, -1, 0, tree, visited);

        int firstEnd = farthestNode;

        visited = new boolean[tree.size()];
        parent = new int[tree.size()];
        Arrays.fill(parent, -1);

        maxDistance = -1;
        dfs(firstEnd, -1, 0, tree, visited);

        int secondEnd = farthestNode;

        List<Integer> path = new ArrayList<>();
        int current = secondEnd;

        while (current != -1) {
            path.add(current);
            if (current == firstEnd)
                break;
            current = parent[current];
        }

        Collections.reverse(path);

        return new Result(maxDistance, path, firstEnd, secondEnd);
    }

    public static void find(int start, List<List<Integer>> tree) {
        Result result = findResult(start, tree);
        System.out.println("Diameter Path: " + result.getPath());
        System.out.println("Diameter Length: " + result.getLength() + " edges");
    }

    public static class Result {
        private final int length;
        private final List<Integer> path;
        private final int firstEnd;
        private final int secondEnd;

        Result(int length, List<Integer> path, int firstEnd, int secondEnd) {
            this.length = length;
            this.path = Collections.unmodifiableList(new ArrayList<>(path));
            this.firstEnd = firstEnd;
            this.secondEnd = secondEnd;
        }

        public int getLength() {
            return length;
        }

        public List<Integer> getPath() {
            return path;
        }

        public int getFirstEnd() {
            return firstEnd;
        }

        public int getSecondEnd() {
            return secondEnd;
        }
    }

    static void dfs(int node, int parentNode, int distance,
                    List<List<Integer>> tree, boolean[] visited) {

        visited[node] = true;
        parent[node] = parentNode;

        if (distance > maxDistance) {
            maxDistance = distance;
            farthestNode = node;
        }

        for (int next : tree.get(node)) {
            if (!visited[next]) {
                dfs(next, node, distance + 1, tree, visited);
            }
        }
    }
}