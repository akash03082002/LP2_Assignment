import java.util.Scanner;
import java.util.Stack;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

class Graph {
    private int vertices;
    private List<List<Integer>> adjList;

    public Graph(int n) {
        vertices = n;
        adjList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adjList.add(new LinkedList<>());
        }
    }

    public void addEdge(int x, int y) {
        adjList.get(x).add(y);
        adjList.get(y).add(x);
    }

    public void dfs(int start) {
        boolean[] visited = new boolean[vertices];
        Stack<Integer> stack = new Stack<>();

        stack.push(start);
        visited[start] = true;

        while (!stack.isEmpty()) {
            int b = stack.pop();
            System.out.print(b + " ");

            for (int i : adjList.get(b)) {
                if (!visited[i]) {
                    visited[i] = true;
                    stack.push(i);
                }
            }
        }
    }

    public void bfs(int start) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int a = queue.poll();
            System.out.print(a + " ");

            for (int i : adjList.get(a)) {
                if (!visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }
}

public class Graph2 {
    public static void main(String[] args) {
        int v, e;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices & edges of graph :");
        v = sc.nextInt();
        e = sc.nextInt();
        Graph graph = new Graph(v);

        for (int i = 0; i < e; i++) {
            System.out.print("\nFor edge " + (i + 1) + ": Enter first and last vertices of edge:- ");
            int x = sc.nextInt();
            int y = sc.nextInt();
            graph.addEdge(x, y);

        }
        System.out.println("\n\t---DFS(Depth First Search)----");
        graph.dfs(0);
        System.out.println("\n\t---BFS(Breadth First Search)---");
        graph.bfs(0);
    }
}
