package dijkstra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {
	public static void computePaths(Vertex source) {
		source.minDistance = 0.;
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<>();
		vertexQueue.add(source);
		source.setVisited(true);
		System.out.println("Queue:" + vertexQueue);

		while (!vertexQueue.isEmpty()) {
			Vertex currentVertex = vertexQueue.poll();
			System.out.println("currentVertex: "+currentVertex);
			for (Edge edge : currentVertex.getConnections()) {
				Vertex v = edge.getTargetVertex();
				System.out.println("target: " + v);

				if (!v.isVisited()) {
					double newDistance = currentVertex.getMinDistance() + edge.getWeight();

					if (newDistance < v.getMinDistance()) {
						vertexQueue.remove(v);
						v.setMinDistance(newDistance);
						v.setPrevious(currentVertex);
						vertexQueue.add(v);
					}

				}
			}
			currentVertex.setVisited(true);
		}
	}

	public static List<Vertex> getShortestPathTo(Vertex target) {
		List<Vertex> path = new ArrayList<Vertex>();
		for (Vertex vertex = target; vertex != null; vertex = vertex.getPrevious())
			path.add(vertex);

		Collections.reverse(path);
		return path;
	}

//	public static boolean readFile(final String fileName) throws IOException {
//		List<String> verts = new ArrayList<String>();
//		List<String> targets = new ArrayList<String>();
//		List<String> weights = new ArrayList<String>();
//		boolean result = false;
//		try {
//			BufferedReader input = new BufferedReader(new FileReader(fileName));
//			String line = "";
//
//			while (!line.equals("-1")) {
//				line = input.readLine();
//				String[] columns = line.split(" ");
//				String vertStart = columns[0];
//				String vertTarget = columns[1];
//				String weight = columns[2];
//
//				if (!verts.contains(vertStart)) {
//					verts.add(vertStart);
//				}
//				if (!verts.contains(vertTarget)) {
//					verts.add(vertTarget);
//				}
//
//				targets.add(vertTarget);
//				weights.add(weight);
//			}
//			input.close();
//
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		
//		System.out.println(verts);
//		System.out.println(targets);
//		System.out.println(weights);
//		
//		for (String vert : verts) {
//			new Vertex(vert).addNeighbour(edge);;
//		}
		/*--------------------------------------------------------*/
//	    while (input.hasNext()) {
//	    	line = line + " " + input.next();
//	    }
//	    line = line.trim();
//	    String[] graph = line.split(" ");
//	    for (int i=0; i < graph.length; i++) {
//	    	
//	    }
//	    
//	    Vertex[] verts = new Vertex[graph.length];
//	    Edge[] edges = new Edge[graph.length];
//	    Vertex v1 = new Vertex("");
//        Vertex v2 = new Vertex("");
//        Vertex source = new Vertex("");
//        int count = 0;

//	    while (input.hasNext()) {
//	        String line = input.nextLine();
//	        System.out.println(line);
//	        if (!line.equals("-1")) {
//	            // end of data
////	            if (input.hasNext()) {
////	                String sourceVert = input.next();
//////	                int weight = input.nextInt();
////	     
//////	                System.out.println("sourceVert: " + sourceVert);
//////	                System.out.println(weight);
////	                result = true;
////	            } else {
////	                System.out.println("end");
////	            }
////	        	do {
////					System.out.println("this is: " + line);
////				} while (input.hasNext());
//	        } else {
//	        	System.out.println("end read");
//	            Scanner vert = new Scanner(line);
//	            System.out.println(vert);
//
//	                String sourceName = vert.next();
//	                System.out.println(sourceName);
//	                final String targetName = vert.next();
//	                System.out.println(targetName);
//	                final int weight = vert.nextInt();
//	                System.out.println(weight);// assuming int for weight
//	                // TODO: create Vertices and Edge here
//	            
//	        }
//	    }

//		return result;
//	}

}