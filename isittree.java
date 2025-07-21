import java.util.*;

public class IsItTree {

    static class Graph {
        int nodes;
        List<List<Integer>> adj;

        Graph(int n) {
            this.nodes = n;
            adj = new ArrayList<>();
            for (int i = 0; i < n; i++)
                adj.add(new ArrayList<>());
        }

        void addEdge(int u, int v) {
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean isCyclic(int v, boolean[] visited, int parent) {
            visited[v] = true;
            for (int neighbor : adj.get(v)) {
                if (!visited[neighbor]) {
                    if (isCyclic(neighbor, visited, v))
                        return true;
                } else if (neighbor != parent) {
                    return true; // found a cycle
                }
            }
            return false;
        }

        boolean isConnected(boolean[] visited) {
            for (boolean v : visited) {
                if (!v) return false;
            }
            return true;
        }

        boolean isTree() {
            boolean[] visited = new boolean[nodes];

            if (isCyclic(0, visited, -1)) return false;

            return isConnected(visited);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nodes = sc.nextInt();
        int edges = sc.nextInt(); 

        if (edges != nodes - 1) {
            System.out.println("No");
            return;
        }

        Graph g = new Graph(nodes);
        for (int i = 0; i < edges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            g.addEdge(u, v);
        }

        System.out.println(g.isTree() ? "Yes" : "No");
    }
}
