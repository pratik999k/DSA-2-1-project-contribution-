import java.util.*;

public class Input {

    public static List<List<Integer>> createTree(int n, Scanner sc) {

        if (n < 1) {
            throw new IllegalArgumentException("Number of nodes must be at least 1.");
        }

        List<List<Integer>> tree = new ArrayList<>();

        for (int i = 0; i <= n; i++)
            tree.add(new ArrayList<>());

        System.out.println("Enter " + (n - 1) + " edges:");

        for (int i = 0; i < n - 1; i++) {

            int u = sc.nextInt();
            int v = sc.nextInt();

            if (u < 1 || u > n || v < 1 || v > n || u == v) {
                throw new IllegalArgumentException("Each edge must connect two different valid nodes.");
            }

            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        return tree;
    }
}