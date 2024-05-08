import java.util.*;

class Node {
    int x, y, f, g, h;
    Node parent;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class AStar {
    private int[][] grid;
    private int rows, cols;
    private Node start, goal;
    private Set<Node> openSet;
    private Set<Node> closedSet;

    public AStar(int[][] grid, int startX, int startY, int goalX, int goalY) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.start = new Node(startX, startY);
        this.goal = new Node(goalX, goalY);
        this.openSet = new HashSet<>();
        this.closedSet = new HashSet<>();
    }

    public List<Node> findPath() {
        openSet.add(start);
        while (!openSet.isEmpty()) {
            Node current = openSet.stream().min(Comparator.comparingInt(node -> node.f)).orElse(null);
            if (current == null)
                break;
            if (current.x == goal.x && current.y == goal.y)
                return reconstructPath(current);
            openSet.remove(current);
            closedSet.add(current);
            for (Node neighbor : getNeighbors(current)) {
                if (closedSet.contains(neighbor))
                    continue;
                int tentativeG = current.g + 1;
                if (tentativeG < neighbor.g || !openSet.contains(neighbor)) {
                    neighbor.parent = current;
                    neighbor.g = tentativeG;
                    neighbor.h = Math.abs(neighbor.x - goal.x) + Math.abs(neighbor.y - goal.y);
                    neighbor.f = neighbor.g + neighbor.h;
                    openSet.add(neighbor);
                }
            }
        }
        return null;
    }

    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        for (int[] dir : directions) {
            int newX = node.x + dir[0];
            int newY = node.y + dir[1];
            if (isValid(newX, newY) && grid[newX][newY] != 1) {
                neighbors.add(new Node(newX, newY));
            }
        }
        return neighbors;
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    private List<Node> reconstructPath(Node current) {
        List<Node> path = new ArrayList<>();
        while (current != null) {
            path.add(current);
            current = current.parent;
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        int[][] grid = {
                { 0, 0, 0, 0, 0 },
                { 0, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0 },
                { 0, 0, 1, 1, 0 },
                { 0, 0, 0, 0, 0 }
        };
        AStar astar = new AStar(grid, 0, 0, 4, 4);
        List<Node> path = astar.findPath();
        if (path != null) {
            for (Node node : path) {
                System.out.println("(" + node.x + ", " + node.y + ")");
            }
        } else {
            System.out.println("No path found.");
        }
    }
}
