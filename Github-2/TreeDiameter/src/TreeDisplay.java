import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeDisplay {

    private static final int HORIZONTAL_GAP = 4;
    private static final int LEVEL_HEIGHT = 2;

    public static void print(int root, List<List<Integer>> tree) {
        Layout layout = new Layout(tree);
        layout.position(root, -1, 0);

        char[][] canvas = new char[layout.maxDepth * LEVEL_HEIGHT + 1]
                [layout.nextX + HORIZONTAL_GAP];
        for (char[] row : canvas) {
            Arrays.fill(row, ' ');
        }

        for (Edge edge : layout.edges) {
            drawConnection(canvas, edge.parent, edge.child);
        }

        for (NodePosition position : layout.positions) {
            String label = String.valueOf(position.node);
            int start = position.x - label.length() / 2;
            for (int i = 0; i < label.length(); i++) {
                canvas[position.y][start + i] = label.charAt(i);
            }
        }

        for (char[] row : canvas) {
            String line = new String(row).replaceFirst("\\s+$", "");
            if (!line.isEmpty()) {
                System.out.println(line);
            }
        }
    }

    private static void drawConnection(char[][] canvas, NodePosition parent,
                                       NodePosition child) {
        int startY = parent.y + 1;
        int endY = child.y - 1;
        int height = endY - startY;
        int width = child.x - parent.x;

        for (int row = startY; row <= endY; row++) {
            double progress = (double) (row - startY + 1) / (height + 1);
            int column = parent.x + (int) Math.round(width * progress);
            char connector = width == 0 ? '|' : width < 0 ? '/' : '\\';
            putCharacter(canvas, row, column, connector);
        }
    }

    private static void putCharacter(char[][] canvas, int row, int column,
                                     char character) {
        if (row >= 0 && row < canvas.length
                && column >= 0 && column < canvas[row].length
                && (canvas[row][column] == ' '
                || canvas[row][column] == character)) {
            canvas[row][column] = character;
        }
    }

    private static class Layout {
        private final List<List<Integer>> tree;
        private final List<NodePosition> positions = new ArrayList<>();
        private final List<Edge> edges = new ArrayList<>();
        private int nextX = 1;
        private int maxDepth;

        Layout(List<List<Integer>> tree) {
            this.tree = tree;
        }

        private NodePosition position(int node, int parent, int depth) {
            maxDepth = Math.max(maxDepth, depth);
            List<NodePosition> children = new ArrayList<>();

            for (int next : tree.get(node)) {
                if (next != parent) {
                    children.add(position(next, node, depth + 1));
                }
            }

            int x;
            if (children.isEmpty()) {
                x = nextX;
                nextX += HORIZONTAL_GAP;
            } else {
                x = (children.get(0).x
                        + children.get(children.size() - 1).x) / 2;
            }

            NodePosition current = new NodePosition(node,
                    x, depth * LEVEL_HEIGHT);
            positions.add(current);

            for (NodePosition child : children) {
                edges.add(new Edge(current, child));
            }

            return current;
        }
    }

    private static class NodePosition {
        private final int node;
        private final int x;
        private final int y;

        NodePosition(int node, int x, int y) {
            this.node = node;
            this.x = x;
            this.y = y;
        }
    }

    private static class Edge {
        private final NodePosition parent;
        private final NodePosition child;

        Edge(NodePosition parent, NodePosition child) {
            this.parent = parent;
            this.child = child;
        }
    }
}
