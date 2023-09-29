package com.dev.graph;

import java.util.ArrayList;

public class TargenAlgorithm {

    static class Edge {
        int src;
        int dest;

        public Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    public static void main(String[] args){
        int v = 6;
        ArrayList<Edge>[] graph = new ArrayList[v];

        createGraph(graph);
        getAP(graph, v);
    }

    public static void dfs(ArrayList<Edge>[] graph, int cur, int[] dt, int[] low, int par, boolean[] visited, int time, boolean[] ap){
        visited[cur] = true;
        dt[cur] = low[cur] = ++time;
        int children = 0;

        for(int i = 0; i < graph[cur].size(); i++){
            Edge e = graph[cur].get(i);
            int n = e.dest;

            if(n == par){
                continue;
            }

            if(visited[n]){
                low[cur] = Math.min(low[cur], dt[n]);
            }
            if(!visited[n]){
                dfs(graph, e.dest, dt, low, cur, visited, time, ap);
                low[cur] = Math.min(low[cur], low[n]);
                if(dt[cur] <= low[n] && par != -1){
                    ap[cur] = true;
                }
                children++;
            }
        }

        if(par == -1 && children > 1){
            ap[cur] = true;
        }
    }

    public static void getAP(ArrayList<Edge>[] graph, int v){
        int[] dt = new int[v];
        int[] low = new int[v];
        boolean[] visited = new boolean[v];
        int time = 0;
        boolean[] ap = new boolean[v];

        for(int i = 0; i < v; i++){
            if(!visited[i]){
                dfs(graph, i, dt, low, -1, visited, time, ap);
            }
        }

        for(int i = 0; i < v; i++){
            if(ap[i]){
                System.out.println("articulation point " + i);
            }
        }

    }

    public static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 3));

        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 2));

        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 1));
        
        graph[3].add(new Edge(3, 0));
        graph[3].add(new Edge(3, 4));

        graph[4].add(new Edge(4, 3));
    }
    
}
