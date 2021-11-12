package dijkstra;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class App {

	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<String> listDir = new ArrayList<String>();
		HashMap<String, String> direction = new HashMap<>();
		String from, to, setFrom, setTo;
		String line, next;
		String filename = "./src/data/input.txt";
		String[] dir;
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
		try {
			while ((line = br.readLine()) != null) {
				if (line.equals("-1")) {
					while ((next = br.readLine()) != null) {
							dir = next.split(" ");
							direction.put(dir[0], dir[1]);
					}
					for (String i : direction.keySet()) {
						if (i.equalsIgnoreCase("from")) {
							from = direction.get(i);
							listDir.add(from);
						} else if (i.equalsIgnoreCase("to")) {
							to = direction.get(i);
							listDir.add(to);
						}
						else {
							System.err.println("error in format input at:" +filename);
						}
						
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
			// setup map
	        Map map = new Map(filename);
	        setFrom = listDir.get(0);
	        setTo = listDir.get(1);
	        if (map.map != null) {
//	        	for (Vertex d : map.map) {
//	        		System.out.println("Map: " + d.toString());
//	        	}
	            Vertex start = map.getVertexWithName(setFrom);

	            Vertex end = map.getVertexWithName(setTo);
	      
	            // Make sure point was able to be found in map, else return
	            if (start == null) {
	                System.out.println("Starting point with name " + setFrom + " could not be found in map, please check the" +
	                                           " name specified in arguments or check the map file and try again.");
	                return;
	            }
	            if (end == null) {
	                System.out.println("Destination point with name " + setTo + " could not be found in map, please check " +
	                                           "the" + " name specified in arguments or make sure point exists in map " +
	                                           "file" + " and try again.");
	                return;
	            }
	            Dijkstra.computePaths(start); // run Dijkstra
	    		System.out.println("Distance to " + end + ": " + end.minDistance);
	            List<Vertex> path = Dijkstra.getShortestPathTo(end);
	            System.out.println("Path: " + path);
	            
	            
//	            System.out.println("    Distance = " + end.minDistance + " miles");
//	            List<Vertex> path = Dijkstra.getShortestPathTo(end);
//	          System.out.println("Path: " + path);
		}
		
//		  Vertex A = new Vertex("A");
//        Vertex B = new Vertex("B");
//        Vertex C = new Vertex("C");
//        Vertex D = new Vertex("D");
//        Vertex E = new Vertex("E");
//       
//        // set the edges and weight
//        A.addNeighbour(new Edge(B, 5));
//        A.addNeighbour(new Edge(C, 3));
//        B.addNeighbour(new Edge(D, 3));
//        B.addNeighbour(new Edge(E, 4));
//        B.addNeighbour(new Edge(C, 4));
//        
//        C.addNeighbour(new Edge(E, 6));
//        C.addNeighbour(new Edge(D, 5));
//        C.addNeighbour(new Edge(B, 2));
//        E.addNeighbour(new Edge(D, 2));
//		Dijkstra.computePaths(A); // run Dijkstra
//		System.out.println("Distance to " + C + ": " + C.minDistance);
//        List<Vertex> path = Dijkstra.getShortestPathTo(C);
//        System.out.println("Path: " + path);

	}
}