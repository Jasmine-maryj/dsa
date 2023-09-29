package com.dev.graph;

import java.util.ArrayList;

public class CycleDFS {

    static class Edge{
        int src;
        int dest;

        public Edge(int s, int d){
            this.src = s;
            this.dest = d;
        }
    }

    public static void main(String[] args){
        int v = 4;
        ArrayList<Edge>[] graph = new ArrayList[v];

        boolean[] visited = new boolean[v];
        boolean[] recStack = new boolean[v];
        createGraph(graph);

        for(int i = 0; i < v; i++){
            if(!visited[i]){
                boolean result = isCyclePresent(graph, visited, recStack, 0);
                if(result){
                    System.out.println(result);
                    break;
                }     

            }
        }

        System.out.println(isCyclePresent(graph, new boolean[v], new boolean[v], 0));
        
    }

    public static void createGraph(ArrayList<Edge>[] graph){
        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0, 2));

        graph[1].add(new Edge(1, 0));

        graph[2].add(new Edge(2, 3));

        graph[3].add(new Edge(3, 0));
    }

    public static boolean isCyclePresent(ArrayList<Edge>[] graph, boolean[] visited, boolean[] recStack, int cur){
        visited[cur] = true;
        recStack[cur] = true;

        for(int i = 0; i < graph[cur].size(); i++){
            Edge e = graph[cur].get(i);
            if(recStack[e.dest]){
                return true;
            }
            if(!visited[e.dest]){
                if(isCyclePresent(graph, visited, recStack, e.dest)){
                    return true;
                }
            }
        }
        recStack[cur] = false;

        return false;
    }
    
}
