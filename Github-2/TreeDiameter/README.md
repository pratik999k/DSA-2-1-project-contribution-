# Tree Network Analysis System

## Problem

Given an undirected tree, this project calculates its diameter using two independent algorithms:

1. The two-DFS diameter algorithm
2. Tree dynamic programming

The program validates the tree, displays basic analysis, prints a connected tree view in the terminal, reconstructs diameter paths, compares the two results, and demonstrates network applications of the diameter. An optional Swing view is also retained in `TreeDisplay.showGraphical`.

## Input and Representation

Enter the number of nodes `N`, followed by `N - 1` edges. Nodes are numbered from `1` to `N`.
The tree is stored as an adjacency list, and every undirected edge is stored in both directions.

## Program Flow

1. Read and construct the tree.
2. Validate connectivity.
3. Display node degrees, leaf count, and height from node 1.
4. Print the tree structure in the terminal with branch connectors.
5. Calculate the diameter with two DFS traversals.
6. Calculate the diameter with tree DP.
7. Compare the actual lengths and report whether they match.
8. Interpret the diameter as a longest communication route and identify central server node(s).

## Algorithms

### Two DFS

The first DFS finds a farthest endpoint from node 1. A second DFS starts at that endpoint and finds the opposite endpoint. The second distance is the diameter. Parent information reconstructs the path.

### Tree DP

One post-order DFS calculates the two greatest downward heights at every node. Their sum is the longest path passing through that node. The maximum over all nodes is the diameter. The same downward paths are combined to reconstruct a path.

Both algorithms run in `O(N)` time and use `O(N)` auxiliary space.

## Applications

The tree can represent a computer, transport, or organizational network. The diameter gives the maximum number of links between two endpoints, which represents a worst-case communication route. The center node, or the two center nodes when the diameter has odd length, are useful candidates for placing a central server or relay.

## Sample Input

```text
7
1 2
1 3
2 4
2 5
5 6
6 7
```

For this exact input, the maximum diameter is **5 edges**, for example:

```text
[3, 1, 2, 5, 6, 7]
```

The path `[4, 2, 5, 6, 7]` has four edges and is a valid long path, but it is not the diameter because node 3 extends the path by one edge.

## Compile and Run

From the `src` directory:

```text
javac *.java
java Main
```

`TestCases.java` contains checks for chain, star, balanced, one-node, and two-node trees. Its `run()` method can be called from a temporary test harness if needed; `Main.java` remains the single application entry point.