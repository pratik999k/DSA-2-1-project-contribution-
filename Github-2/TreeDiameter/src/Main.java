import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("      TREE NETWORK ANALYSIS SYSTEM");
        System.out.println("========================================");

        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        List<List<Integer>> tree;

        try {
            tree = Input.createTree(n, sc);
        } catch (IllegalArgumentException exception) {
            System.out.println("Invalid input: " + exception.getMessage());
            sc.close();
            return;
        }

        // Tree Validation
        if (!Validation.isConnected(n, tree)) {
            System.out.println("\nInvalid Tree: Tree is not connected.");
            sc.close();
            return;
        }

        System.out.println("\nTree is valid.");

        // Tree Structure
        System.out.println("\n=== Tree Structure ===");
        TreeDisplay.print(1, tree);

        // Tree Analysis
        System.out.println("\n=== Tree Analysis ===");
        TreeAnalysis.analyze(n, tree);

        // Two-DFS Diameter
        DiameterDFS.Result dfsResult =
                DiameterDFS.findResult(1, tree);

        // Tree DP Diameter
        DiameterDP.Result dpResult =
                DiameterDP.findResult(1, tree);

        // Compare and verify both algorithms
        System.out.println("\n=== Diameter Results ===");
        Comparison.show(dfsResult, dpResult);

        // Network Application
        System.out.println("\n=== Network Applications ===");
        TreeApplications.show(dfsResult.getPath());

        sc.close();
    }
}