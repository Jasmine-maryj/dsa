package com.dev.graph;

import java.util.ArrayList;

public class Bridge {
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
        getBridge(graph, v);
    }

    public static void dfs(ArrayList<Edge>[] graph, int cur, boolean[] visited, int[] dt, int[] low, int time, int par){
        visited[cur] = true;

        dt[cur] = low[cur] = ++time;

        for(int i = 0; i < graph[cur].size(); i++){
            Edge e = graph[cur].get(i);
            if(e.dest == par){
                continue;
            }
            if(!visited[e.dest]){
                dfs(graph, e.dest, visited, dt, low, time, cur);
                low[cur] = Math.min(low[cur], low[e.dest]);
                if(dt[cur] < low[e.dest]){
                    System.out.println("Bridge is :" + cur + " ======== " + e.dest);
                }
            }
            if(visited[e.dest]){
                low[cur] = Math.min(low[cur], dt[e.dest]);
            }
        }
    }

    public static void getBridge(ArrayList<Edge>[] graph, int v){
        int[] dt = new int[v];
        int[] low = new int[v];
        int time = 0;
        boolean[] visited = new boolean[v];
        for(int i = 0; i < v; i++){
            if(!visited[i]){
                dfs(graph, i, visited, dt, low, time, -1);
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
        // graph[3].add(new Edge(3, 5));

        graph[4].add(new Edge(4, 3));
        // graph[4].add(new Edge(4, 5));

        // graph[5].add(new Edge(5, 3));
        // graph[5].add(new Edge(5, 4));
    }
}
