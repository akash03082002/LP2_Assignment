import  java.util.*;

public class PrimAlgorithm {
    public static void primMST(int[][] graph) {
        int V = graph.length;

        int[] parent = new int[V]; // Array to store constructed MST
        int[] key = new int[V]; // Key values used to pick minimum weight edge in cut
        boolean[] mstSet = new boolean[V]; // To represent set of vertices included in MST

        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0; // Start with first vertex
        parent[0] = -1; // First node is always root of MST

        for (int count = 0; count < V - 1; count++) {
            int u = -1;
            // Pick the minimum key vertex from the set of vertices not yet included in MST
            for (int v = 0; v < V; v++)
                if (!mstSet[v] && (u == -1 || key[v] < key[u]))
                    u = v;

            mstSet[u] = true;

            // Update key value and parent index of the adjacent vertices of the picked
            // vertex
            // Consider only those vertices which are not yet included in MST
            for (int v = 0; v < V; v++)
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
        }

        // Print the constructed MST
        printMST(parent, graph);
    }

    public static void printMST(int[] parent, int[][] graph) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < graph.length; i++)
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
    }

    public static void main(String[] args) {
        int [][] graph = new int[][] {
                { 0, 2, 0, 6, 0 },
                { 2, 0, 3, 8, 5 },
                { 0, 3, 0, 0, 7 },
                { 6, 8, 0, 0, 9 },
                { 0, 5, 7, 9, 0 }
        };

        primMST(graph);
    }
}
