package com.dev.graph;

import java.util.ArrayList;
import java.util.Stack;


public class TopologicalSort {

    static class Edge{
        int src;
        int dest;

        public Edge(int s, int d){
            this.src = s;
            this.dest = d;
        }
    }

    public static void main(String[] args){
        int v = 6;
        ArrayList<Edge>[] graph = new ArrayList[v];

        createGraph(graph);
        topologicalSort(graph, v);

    }

    public static void topologicalSortUtil(ArrayList<Edge>[] graph, int cur, boolean[] visited, Stack<Integer> stack){
        visited[cur] = true;

        for(int i = 0; i < graph[cur].size(); i++){
            Edge e = graph[cur].get(i);
            if(!visited[e.dest]){
                topologicalSortUtil(graph, e.dest, visited, stack);
            }
        }
        stack.push(cur);
    }

    public static void topologicalSort(ArrayList<Edge>[] graph, int v){
        boolean[] visited = new boolean[v];
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < v; i++){
            if(visited[i] == false){
                topologicalSortUtil(graph, i, visited, stack);
            }
        }

        while(!stack.empty()){
            System.out.print(stack.pop() +" ");
        }
    }

    public static void createGraph(ArrayList<Edge>[] graph){
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<Edge>();
        }


        graph[2].add(new Edge(2, 3));

        graph[3].add(new Edge(3, 1));

        graph[4].add(new Edge(4, 0));
        graph[4].add(new Edge(4, 1));

        graph[5].add(new Edge(5, 0));
        graph[5].add(new Edge(5, 2));
    }
    
}
