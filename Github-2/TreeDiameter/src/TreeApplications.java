import java.util.List;

public class TreeApplications {

    public static void show(List<Integer> diameterPath) {
        int diameter = diameterPath.size() - 1;
        int middle = diameterPath.size() / 2;

        System.out.println("\n=== Network Applications ===");
        System.out.println("Longest communication route: "
                + Comparison.formatPath(diameterPath));
        System.out.println("Maximum communication hops: " + diameter);
        System.out.println("Route endpoints: " + diameterPath.get(0)
                + " and " + diameterPath.get(diameterPath.size() - 1));

        if (diameter % 2 == 0) {
            System.out.println("Suggested central server node: "
                    + diameterPath.get(middle));
        } else {
            System.out.println("Suggested central server nodes: "
                    + diameterPath.get(middle - 1) + " and "
                    + diameterPath.get(middle));
        }
    }
}