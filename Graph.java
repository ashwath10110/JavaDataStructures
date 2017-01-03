package com.company;

import java.util.LinkedList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

/**
 * Created by ashwathb on 1/2/17.
 */
public class Graph {
}

class EdgeWeightedDigraph {

    private static final String NEWLINE = System.getProperty("line.separator");

    public int v;
    public int e;
    public LinkedList<Integer>[] adj;

    public EdgeWeightedDigraph(int v) {

        this.v = v;
        this.e = 0;

        adj = new LinkedList[v];

        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<Integer>();
        }
    }

    public void AddEdge(Edge edge) {

        adj[edge.source].add(edge.target);
        e++;
    }

    public Iterable<Integer> Adj(int v) {
        return adj[v];
    }


    public Iterable<Integer> Edges() {

        LinkedList<Integer> linkedlist = new LinkedList<Integer>();

        for (int v = 0; v < v; v++) {

            for (Integer e : adj[v]) {
                linkedlist.add(e);
            }
        }

        return linkedlist;
    }

    public String toString() {

        StringBuilder s = new StringBuilder();

        s.append(v + " vertices, " + e + " edges " + NEWLINE);

        for (int i = 0; i < v; i++) {

            s.append(String.format("%d: ", i));

            for (int w : adj[i]) {
                s.append(String.format("%d ", w));
            }

            s.append(NEWLINE);
        }

        return s.toString();
    }


}

class Edge {

    public int source;
    public int target;
    public int weight;

    public Edge(int source, int target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public String ToString() {
        return String.format("{0:d}->{1:d} {2:f}", source, target, weight);
    }

}

class BFS {

    public boolean[] marked;
    public int[] colour;
    public int s;
    public LinkedList<Integer> queue;
    public int V;

    public BFS(EdgeWeightedDigraph G, int s) {

        this.V = G.v;

        marked = new boolean[V];
        colour = new int[V];
        queue = new LinkedList<Integer>();

        this.s = s;

        bfs(G, s);
    }

    private void bfs(EdgeWeightedDigraph G, int s) {

        marked[s] = true;

        queue.add(s);

        while (queue.size() != 0) {

            int current = queue.poll();

            System.out.print(current + " ");

            Iterator<Integer> i = G.adj[current].listIterator();

            while (i.hasNext()) {

                int n = i.next();

                if (!marked[n]) {
                    marked[n] = true;
                    queue.add(n);
                }
            }
        }

    }

    public boolean isBipartite(EdgeWeightedDigraph G, int s) {

        for (int i = 0; i < G.v; i++) {
            colour[i] = -1;
        }

        colour[s] = 1;

        queue.add(s);

        while (queue.size() != 0) {

            int current = queue.poll();

            Iterator<Integer> i = G.adj[current].listIterator();

            while (i.hasNext()) {

                int n = i.next();

                if (colour[n] == -1) {
                    colour[n] = 1 - colour[current];
                    queue.add(n);
                } else if (colour[n] == colour[current]) {
                    return false;
                }

            }
        }

        return true;
    }

}

class DFS {

    public boolean[] marked;
    public int[] edgeTo;
    public int s;

    public DFS(EdgeWeightedDigraph G, int s) {

        int v = G.v;

        marked = new boolean[v];
        edgeTo = new int[v];
        this.s = s;

        dfs(G, s);
    }

    private void dfs(EdgeWeightedDigraph G, int v) {

        marked[v] = true;

        System.out.print(v + " ");

        for (int w : G.Adj(v)) {

            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public Boolean HasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> PathTo(int v) {

        if (!HasPathTo(v))
            return null;

        Stack<Integer> path = new Stack<Integer>();

        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);

        path.push(s);

        return path;
    }

}

