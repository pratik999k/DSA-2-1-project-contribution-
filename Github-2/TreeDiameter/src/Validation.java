import java.util.*;

public class Validation {

    public static boolean isConnected(int n, List<List<Integer>> tree) {

        boolean[] visited = new boolean[n + 1];

        dfs(1, tree, visited);

        for (int i = 1; i <= n; i++) {
            if (!visited[i])
                return false;
        }

        return true;
    }

    static void dfs(int node, List<List<Integer>> tree,
                    boolean[] visited) {

        visited[node] = true;

        for (int next : tree.get(node)) {
            if (!visited[next])
                dfs(next, tree, visited);
        }
    }

    // Displays the tree in a simple graphical terminal format
    public static void displayTree(int node, int parent,
                                   List<List<Integer>> tree) {

        List<Integer> children = new ArrayList<>();

        for (int next : tree.get(node)) {
            if (next != parent) {
                children.add(next);
            }
        }

        System.out.println("        " + node);

        displayChildren(children, tree, node, "");
    }

    private static void displayChildren(List<Integer> children,
                                        List<List<Integer>> tree,
                                        int parent,
                                        String prefix) {

        if (children.isEmpty())
            return;

        if (children.size() == 1) {

            int child = children.get(0);

            System.out.println(prefix + "        |");
            System.out.println(prefix + "        " + child);

            List<Integer> nextChildren = getChildren(
                    child, parent, tree
            );

            displayChildren(
                    nextChildren,
                    tree,
                    child,
                    prefix + "        "
            );

        } else {

            System.out.println(prefix + "       / \\");

            for (int i = 0; i < children.size(); i++) {

                int child = children.get(i);

                if (i == 0) {
                    System.out.println(prefix + "      " + child);
                } else {
                    System.out.println(prefix + "          " + child);
                }
            }

            for (int child : children) {

                List<Integer> nextChildren =
                        getChildren(child, parent, tree);

                if (!nextChildren.isEmpty()) {

                    displayChildren(
                            nextChildren,
                            tree,
                            child,
                            prefix + "          "
                    );
                }
            }
        }
    }

    private static List<Integer> getChildren(
            int node,
            int parent,
            List<List<Integer>> tree) {

        List<Integer> children = new ArrayList<>();

        for (int next : tree.get(node)) {

            if (next != parent) {
                children.add(next);
            }
        }

        return children;
    }
}