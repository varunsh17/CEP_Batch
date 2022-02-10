import java.util.ArrayList;

public class questions {

    public static class Edge {
        int v = 0;
        int wt = 0;

        public Edge(int v, int wt) {
            this.v = v;
            this.wt = wt;
        }

        @Override

        public String toString() {
            return "(" + v + "," + wt + ") ";
        }
    }

    public static void main(String[] args) {
        int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 0 }, { 4, 2 } };
        boolean cycle = cycleDetection(5, edges);
        System.out.println(cycle);
        ArrayList<Edge>[] graph = SpanningTree(5, edges);
        display(5, graph);
    }

    // CYCLE DETECTION
    public static boolean cycleDetection(int n, int[][] edges) {
        dsu uf = new dsu(n);
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int val = uf.union(u, v);
            if (val == -1) {
                return true;
            }
        }
        return false;
    }

    // SPANNING TREE

    public static ArrayList<Edge>[] SpanningTree(int n, int[][] edges) {
        ArrayList<Edge>[] graph = new ArrayList[n];
        init(graph, n);
        dsu uf = new dsu(n);
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int val = uf.union(u, v);
            if (val != -1) {
                addEdge(graph, u, v, 0);
            }
        }
        return graph;
    }

    public static void init(ArrayList<Edge>[] graph, int n) {
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int wt) {
        graph[u].add(new Edge(v, wt));
        graph[v].add(new Edge(u, wt));
    }

    public static void display(int n, ArrayList<Edge>[] graph) {
        for (int i = 0; i < n; i++) {
            System.out.print(i + "-> ");
            for (Edge e : graph[i]) {
                // System.out.print("(" + e.v + "," + e.wt + ") ");
                System.out.print(e);
            }
            System.out.println();
        }
    }

    // GET CONNECTED COMPONENTS

    public static int gcc(int n, int[][] edges) {
        dsu uf = new dsu(n);
        int compo = n;
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int val = uf.union(u, v);
            if (val != -1) {
                compo--;
            }
        }
        return compo;
    }
}