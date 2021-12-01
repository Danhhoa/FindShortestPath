package dijkstra;

import org.jgrapht.*;
import org.jgrapht.alg.*;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.*;
import java.util.List;

public class test2 {
//    public static void main(String args[]) {

//        SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>  graph = 
//        new SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>
//        (DefaultWeightedEdge.class); 
//        graph.addVertex("vertex1");
//        graph.addVertex("vertex2");
//        graph.addVertex("vertex3");
//        graph.addVertex("vertex4");
//        graph.addVertex("vertex5");
//
//
//        DefaultWeightedEdge e1 = graph.addEdge("vertex1", "vertex2"); 
//        graph.setEdgeWeight(e1, 5); 
//
//        DefaultWeightedEdge e2 = graph.addEdge("vertex2", "vertex3"); 
//        graph.setEdgeWeight(e2, 3); 
//
//        DefaultWeightedEdge e3 = graph.addEdge("vertex4", "vertex5"); 
//        graph.setEdgeWeight(e3, 6); 
//
//        DefaultWeightedEdge e4 = graph.addEdge("vertex2", "vertex4"); 
//        graph.setEdgeWeight(e4, 2); 
//
//        DefaultWeightedEdge e5 = graph.addEdge("vertex5", "vertex4"); 
//        graph.setEdgeWeight(e5, 4); 
//
//
//        DefaultWeightedEdge e6 = graph.addEdge("vertex2", "vertex5"); 
//        graph.setEdgeWeight(e6, 9); 
//
//        DefaultWeightedEdge e7 = graph.addEdge("vertex4", "vertex1"); 
//        graph.setEdgeWeight(e7, 7); 
//
//        DefaultWeightedEdge e8 = graph.addEdge("vertex3", "vertex2"); 
//        graph.setEdgeWeight(e8, 2); 
//
//        DefaultWeightedEdge e9 = graph.addEdge("vertex1", "vertex3"); 
//        graph.setEdgeWeight(e9, 10); 
//
//        DefaultWeightedEdge e10 = graph.addEdge("vertex3", "vertex5"); 
//        graph.setEdgeWeight(e10, 1); 
//
//
//        System.out.println("Shortest path from vertex1 to vertex5:");
//        List shortest_path =   (List) DijkstraShortestPath.findPathBetween(graph, "vertex1", "vertex5");
//        System.out.println(shortest_path);

//    }
	static SimpleWeightedGraph<Integer, Double> graph;

    public static void main(String[] args) 
    {
        graph = new SimpleWeightedGraph<Integer, Double>(Double.class);

        graph.addVertex(1);
        graph.addVertex(4);
        graph.addVertex(8);
        graph.addVertex(9);

        graph.addEdge(1, 4, (double) 1);
        graph.addEdge(4, 8, (double) 2);
        graph.addEdge(8, 9, (double) 3);

        getRoute(1, 9);
    }

    public static void getRoute(Integer startVertex, Integer endVertex)
    {
        DijkstraShortestPath<Integer, Double> dijkstra = new DijkstraShortestPath<Integer, Double>(graph);
         GraphPath<Integer, Double> graphpath = dijkstra.getPath(startVertex, endVertex);
         if(graphpath == null)
         {
             System.err.println("null");
             return;
         }

         List<Integer> path = graphpath.getVertexList();
         for(Integer p : path) System.err.println(p);

    }
}