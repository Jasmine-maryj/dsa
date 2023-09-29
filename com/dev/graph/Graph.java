package com.dev.graph;

import java.util.ArrayList;

public class Graph {

    //Edge class
    static class Edge{
        int src;
        int dest;
        int weight;

        public Edge(int s, int d){
            this.src = s;
            this.dest = d;
        }

        public Edge(int s, int d, int w){
            this.src = s;
            this.dest = d;
            this.weight = w;
        }
    }

    public static void main(String[] args){
        int v = 4;

        ArrayList<Edge>[] graph = new ArrayList[v];

        // createGraph(graph);
        createWeightedUndirectedGraph(graph);
        printNeighbors(graph, 2);
    }

    

    public static void createWeightedUndirectedGraph(ArrayList<Edge>[] graph){
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0, 2, 2));

        graph[1].add(new Edge(1, 2, 10));
        graph[1].add(new Edge(1, 3, 0));

        graph[2].add(new Edge(2, 0, 2));
        graph[2].add(new Edge(2, 1, 10));
        graph[2].add(new Edge(2, 3, -1));

        graph[3].add(new Edge(3, 1, 0));
        graph[3].add(new Edge(3, 2, -1));
    }

    public static void createGraph(ArrayList<Edge>[] graph){
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0, 2));

        graph[1].add(new Edge(1, 2));
        graph[1].add(new Edge(1, 3));

        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 1));
        graph[2].add(new Edge(2, 3));

        graph[3].add(new Edge(3, 1));
        graph[3].add(new Edge(3, 2));
    }

    public static void printNeighbors(ArrayList<Edge>[] graph, int vertex){
        for(int i = 0; i < graph[vertex].size(); i++){
            Edge e = graph[vertex].get(i);
            System.out.println(e.src + "-> " + e.dest + "-> " + e.weight);
        }
    }
}
