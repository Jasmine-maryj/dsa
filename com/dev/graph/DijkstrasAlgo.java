package com.dev.graph;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class DijkstrasAlgo {
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

    public static void createGraph(ArrayList<Edge>[] graph){
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0, 1, 2));
        graph[0].add(new Edge(0, 2, 4));

        graph[1].add(new Edge(1, 2, 1));
        graph[1].add(new Edge(1, 3, 7));

        graph[2].add(new Edge(2, 4, 3));

        graph[3].add(new Edge(3, 5, 1));

        graph[4].add(new Edge(4, 3, 2));
        graph[4].add(new Edge(4, 5, 5));
    }

    public static class Pair implements Comparable<Pair>{
        int node;
        int dis;

        public Pair(int n, int d){
            this.node = n;
            this.dis = d;
        }

        @Override
        public int compareTo(Pair pair){
            return this.dis - pair.dis;
        }
    }

    public static void dijkstrasAlgo(ArrayList<Edge>[] graph, int src, int V){
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        int[] distance = new int[V];
        for(int i = 0; i < V; i++){
            if(i != src){
                distance[i] = Integer.MAX_VALUE;
            }
        }

        boolean[] visited = new boolean[V];

        pq.add(new Pair(0, 0));

        while (!pq.isEmpty()){
            Pair cp = pq.remove();
            if(!visited[cp.node]){
                visited[cp.node] = true;
                for(int i = 0; i < graph[cp.node].size(); i++){
                    Edge e = graph[cp.node].get(i);
                    int u = e.src;
                    int v = e.dest;
                    if(distance[u] + e.wt < distance[v]){
                        distance[v] = distance[u] + e.wt;
                        pq.add(new Pair(v, distance[v]));
                    }
                }
            }
        }
        for( int i = 0; i < V; i++){
            System.out.print(distance[i] + " ");
        }
        System.out.println();

    }


    public static void main(String[] args){
        int v = 6;
        ArrayList<Edge>[] graph = new ArrayList[v];
        createGraph(graph);
        dijkstrasAlgo(graph, 0, v);
    }


}
