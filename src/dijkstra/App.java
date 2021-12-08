package dijkstra;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import org.jgrapht.Graphs;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import GUI.mainGUI;
import dijkstra.Map.MyEdge;



public class App {
	
	public static List<Vertex> path;
	public static void startDijkstra(String filename) {
		HashMap<String, String> direction = new HashMap<>();
		ArrayList<String> listDir = new ArrayList<String>();
		String from, to, setFrom, setTo;
		String line, next;
		String[] dir;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
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
		
		Map map = new Map(filename);
		System.out.println("Map lenght: " +map.map.length );
        setFrom = listDir.get(0);
        setTo = listDir.get(1);
        if (map.map != null) {
       		for (Vertex d : map.map) {
			if (d!=null) {
				System.out.println("Map: " +d.toString());
			}
			else System.err.println("NULL: " +d);
		}
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
    		 path = Dijkstra.getShortestPathTo(end);
             System.out.println("Path: " + path);
  		
	}
	}

	public static void main(String[] args) throws FileNotFoundException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainGUI frame = new mainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	}
	