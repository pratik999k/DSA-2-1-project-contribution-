import java.util.*;

public class TreeAnalysis {

    public static void analyze(int n, List<List<Integer>> tree) {
        int leaves = 0;
        for (int i = 1; i <= n; i++) {
            int degree = tree.get(i).size();
            System.out.println("Node " + i + " - Degree: " + degree);

            if (degree == 1 || (n == 1 && degree == 0))
                leaves++;
        }
        System.out.println("Leaf Nodes: " + leaves);
        boolean[] visited = new boolean[n + 1];
        int height = findHeight(1, tree, visited);
        System.out.println("Tree Height: " + height);
    }
    static int findHeight(int node, List<List<Integer>> tree,
        boolean[] visited) {
        visited[node] = true;
        int height = 0;
        for (int next : tree.get(node)) {
            if (!visited[next])
                height = Math.max(height,
                        1 + findHeight(next, tree, visited));
        }
        return height;
    }
}