public class Comparison {

    public static void show(DiameterDFS.Result dfsResult,
                            DiameterDP.Result dpResult) {

        System.out.println("\n=== Diameter Results ===");
        System.out.println("Two-DFS Path  : " + formatPath(dfsResult.getPath()));
        System.out.println("Two-DFS Length: " + dfsResult.getLength() + " edges");
        System.out.println("Tree DP Path  : " + formatPath(dpResult.getPath()));
        System.out.println("Tree DP Length: " + dpResult.getLength() + " edges");

        System.out.println("\nVerification: "
                + (dfsResult.getLength() == dpResult.getLength()
                ? "Both algorithms produced the same diameter."
                : "Mismatch detected."));
    }

    public static String formatPath(java.util.List<Integer> path) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            if (i > 0) {
                result.append(" -> ");
            }
            result.append(path.get(i));
        }
        return result.toString();
    }
}