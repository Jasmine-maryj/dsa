package com.dev.graph;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.lang.Comparable;

public class PrimsAlgorithm {
    static class Edge{
        int src;
        int dest;
        int wt;

        public Edge(int s, int d, int w){
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }

    public static void main(String[] args){
        int v = 4;
        ArrayList<Edge>[] graph = new ArrayList[v];

        createGraph(graph);
        primsAlgorithm(graph, v);
    }

    public static class Pair implements Comparable<Pair>{
        int node; 
        int cost;

        public Pair(int n, int c){
            this.node = n;
            this.cost = c;
        }

        @Override
        public int compareTo(Pair p){
            return this.cost - p.cost;
        }
    }

    public static void primsAlgorithm(ArrayList<Edge>[] graph, int V){
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[V];

        int cost = 0;

        pq.add(new Pair(0, 0));
        
        while(!pq.isEmpty()){
            Pair cp = pq.remove();
            if(!visited[cp.node]){
                visited[cp.node] = true;
                cost += cp.cost;

                for(int i= 0; i < graph[cp.node].size(); i++){
                    Edge e = graph[cp.node].get(i);
                    if(!visited[e.dest]){
                        pq.add(new Pair(e.dest, e.wt));
                    }
                }
            }
        }

        System.out.println("Minimum cost of mst " + cost);
        
    }

    public static void createGraph(ArrayList<Edge>[] graph){
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0, 1, 10));
        graph[0].add(new Edge(0, 2, 15));
        graph[0].add(new Edge(0, 3, 30));

        graph[1].add(new Edge(1, 0, 10));
        graph[1].add(new Edge(1, 3, 40));

        graph[2].add(new Edge(2, 0, 15));
        graph[2].add(new Edge(2, 3, 50));

        graph[3].add(new Edge(3, 0, 30));
        graph[3].add(new Edge(3, 1, 40));
        graph[3].add(new Edge(3, 2, 50));

    }
    
}

