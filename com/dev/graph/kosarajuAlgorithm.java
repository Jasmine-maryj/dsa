package com.dev.graph;

import java.util.ArrayList;
import java.util.Stack;

import com.dev.stack.stack;

public class kosarajuAlgorithm {

    static class Edge {
        int src;
        int dest;

        public Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    public static void main(String[] args) {
        int v = 5;
        ArrayList<Edge>[] graph = new ArrayList[v];

        createGraph(graph);
        kosarajuAlgorithm(graph, v);
    }

    public static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0, 2));
        graph[0].add(new Edge(0, 3));
        graph[1].add(new Edge(1, 0));
        graph[2].add(new Edge(2, 1));
        graph[3].add(new Edge(3, 4));
    }

    public static void topologicalSort(ArrayList<Edge>[] graph, int cur, boolean[] visited, Stack<Integer> stack) {
        visited[cur] = true;

        for (int i = 0; i < graph[cur].size(); i++) {
            Edge e = graph[cur].get(i);
            if (!visited[e.dest]) {
                topologicalSort(graph, e.dest, visited, stack);
            }
        }

        stack.push(cur);
    }

    public static void dfs(ArrayList<Edge>[] graph, int cur, boolean[] visited) {
        visited[cur] = true;

        System.out.print(cur + " ");

        for (int i = 0; i < graph[cur].size(); i++) {
            Edge e = graph[cur].get(i);
            if (!visited[e.dest]) {
                dfs(graph, e.dest, visited);
            }
        }
    }

    public static void kosarajuAlgorithm(ArrayList<Edge>[] graph, int v) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[v];

        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                topologicalSort(graph, i, visited, stack);
            }
        }

        ArrayList<Edge>[] transpose = new ArrayList[v];
        for (int i = 0; i < graph.length; i++) {
            transpose[i] = new ArrayList<Edge>();
        }

        for (int i = 0; i < v; i++) {
            visited[i] = false;
            for (int j = 0; j < graph[i].size(); j++) {
                Edge e = graph[i].get(j);
                transpose[e.dest].add(new Edge(e.dest, e.src));
            }
        }

        while (!stack.empty()) {
            int c = stack.pop();
            if (!visited[c]) {
                dfs(transpose, c, visited);
                System.out.println();
            }
        }

    }

}
