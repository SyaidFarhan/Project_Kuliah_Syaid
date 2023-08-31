import java.util.*;

class Main {
    static class Edge {
        int v;
        int capacity;
        int flow;

        public Edge(int v, int capacity) {
            this.v = v;
            this.capacity = capacity;
            this.flow = 0;
        }
    }

    public static int bfs(List<List<Edge>> graph, int[] parent, int src, int destination) {
        int n = graph.size();
        boolean[] visited = new boolean[n];
        visited[src] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(src);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (Edge edge : graph.get(node)) {
                int v = edge.v;
                int capacity = edge.capacity;
                int flow = edge.flow;

                if (!visited[v] && capacity > flow) {
                    visited[v] = true;
                    parent[v] = node;
                    queue.offer(v);

                    if (v == destination) {
                        return 1;
                    }
                }
            }
        }

        return 0;
    }

    public static int edmondsKarp(List<List<Edge>> graph, int src, int destination) {
        int n = graph.size();
        int[] parent = new int[n];
        int maxFlow = 0;

        while (bfs(graph, parent, src, destination) == 1) {
            int flowLine = Integer.MAX_VALUE;
            int v = destination;

            while (v != src) {
                int u = parent[v];
                for (Edge edge : graph.get(u)) {
                    if (edge.v == v) {
                        int capacity = edge.capacity;
                        int flow = edge.flow;
                        flowLine = Math.min(flowLine, capacity - flow);
                        break;
                    }
                }
                v = parent[v];
            }

            v = destination;
            while (v != src) {
                int u = parent[v];
                for (Edge edge : graph.get(u)) {
                    if (edge.v == v) {
                        edge.flow += flowLine;
                        break;
                    }
                }
                boolean foundReverseEdge = false;
                for (Edge edge : graph.get(v)) {
                    if (edge.v == u) {
                        edge.flow -= flowLine;
                        foundReverseEdge = true;
                        break;
                    }
                }
                if (!foundReverseEdge) {
                    graph.get(v).add(new Edge(u, flowLine));
                }
                v = parent[v];
            }

            maxFlow += flowLine;
        }

        return maxFlow;
    }

    public static void main(String[] args) {
        int S = 1;
        int T = 6;
        int[] titikMasuk = {1}; // Source node incoming edge
        int[] titikKeluar = {6}; // Destination node outgoing edge

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            graph.add(new ArrayList<>());
        }

        for (int masuk : titikMasuk) {
            graph.get(S).add(new Edge(masuk, Integer.MAX_VALUE));
            graph.get(masuk).add(new Edge(S, 0)); // Reverse edge
        }

        for (int keluar : titikKeluar) {
            graph.get(keluar).add(new Edge(T, Integer.MAX_VALUE));
            graph.get(T).add(new Edge(keluar, 0)); // Reverse edge
        }

        graph.get(1).add(new Edge(2, 16));
        graph.get(1).add(new Edge(3, 13));
        graph.get(2).add(new Edge(4, 12));
        graph.get(2).add(new Edge(3, 10));
        graph.get(3).add(new Edge(2, 4));
        graph.get(4).add(new Edge(3, 9));
        graph.get(3).add(new Edge(5, 14));
        graph.get(5).add(new Edge(4, 7));
        graph.get(4).add(new Edge(6, 20));
        graph.get(5).add(new Edge(6, 4));

        int maxFlow = edmondsKarp(graph, S, T);
        System.out.println(maxFlow);
    }
}
