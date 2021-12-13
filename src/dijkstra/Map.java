package dijkstra;

import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jgrapht.Graphs;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.model.mxICell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.handler.mxCellTracker;
import com.mxgraph.swing.util.mxSwingConstants;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;



public class Map {
	public static Vertex[] map = null;
	static SimpleDirectedWeightedGraph<Vertex, MyEdge> g;

	private static final long serialVersionUID = 2202072534703043194L;
	private static final Color DEFAULT_BG_COLOR = Color.decode("#FAFBFF");

	private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);

	private JGraphXAdapter<String, MyEdge> jgxAdapter;

	public static ArrayList<String> listNodes;
	public ArrayList<String> listVertAdded; 
	static MyEdge e;
	
	
	public static void createAndShowGui(String filename) {
		JFrame frame = new JFrame("DemoGraph");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			JGraphXAdapter<Vertex, MyEdge> graphAdapter = new JGraphXAdapter<Vertex, MyEdge>(g);
			
			mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
			layout.execute(graphAdapter.getDefaultParent());
			
			mxGraphComponent myGraphComponent = new mxGraphComponent(graphAdapter);
//			mxCellTracker trackColor = new mxCellTracker(myGraphComponent, Color.RED);
			System.out.println("MYGRAPHCOMPONENT: " + myGraphComponent);
			frame.add(myGraphComponent);
//			frame.setBackground(DEFAULT_BG_COLOR);
			frame.setSize(400, 400);
			frame.setLocationByPlatform(true);
			frame.setVisible(true);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	public static void startDraw(String filename) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGui(filename);
			}
		});
	}

	public Map(String filename) {
		map = createMap(filename);
	}

	public boolean contains(String name) {
		for (Vertex v : map) {
			if (v != null && v.toString().compareTo(name) == 0)
				return true;
		}
		return false;
	}

	public Vertex getVertexWithName(String name) {
		for (Vertex v : map) {
			if (v != null && v.toString().compareTo(name) == 0)
				return v;
		}
		return null;
	}

	public static Vertex[] addVert(int n, Vertex arr[], Vertex x) {
		int i;
		Vertex newMap[] = new Vertex[n + 1];
		for (i = 0; i < n; i++) {
			newMap[i] = arr[i];
			newMap[n] = x;
		}
		return newMap;

	}

	public static class MyEdge extends DefaultWeightedEdge {
		@Override
		public String toString() {
			return String.valueOf(getWeight());
		}
		
		public Vertex getTarget() {
			return getTarget();
		}
	}

	private Vertex[] createMap(String mapFile) {
		try {
			listVertAdded = new ArrayList<>();
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(mapFile)));
			g = new SimpleDirectedWeightedGraph<>(MyEdge.class);
			int vertices = Integer.valueOf(br.readLine());
			if (vertices <= 0) {
				System.out.println("Vertex amount from map file is less than or equal to 0. Must have two or more "
						+ "points to construct a map.");
				return null;
			}
			String line;
			int i = 0; // counter used to add vertices to map array
			map = new Vertex[vertices]; // initialize new empty map with length using vertex count

			while (!(line = br.readLine()).equals("-1")) {
				String[] tokens = line.split("\\s+");
				System.out.println("XÉT " + line);
				// add a vertex for each city only once each
				if (!contains(tokens[0])) {
					// if vertex doesn't exist in map, add it
					map[i] = new Vertex(tokens[0]);
						listVertAdded.add(tokens[0]);
					// add an edge to new vertex
					Vertex edge = getVertexWithName(tokens[1]);

					// if edge vertex doesn't exist in map yet, create the edge vertex and connect
					// it to vertex
					if (edge == null) {
						// add edges to each vertex using second and third value from file
						Vertex newEdge = new Vertex(tokens[1]);
						if (newEdge!=null) {
							map = addVert(map.length, map, newEdge);
						}
						
						// add each vertex to map
						map[i].addNeighbour(new Edge(newEdge, Double.valueOf(tokens[2])));
							listVertAdded.add(tokens[1]);
							
	
					} else {
						map[i].addNeighbour(new Edge(edge, Double.valueOf(tokens[2])));
					
					}
					// increment i only after vertex is done being added to map
					i++;
				} else {
					Vertex vertex = getVertexWithName(tokens[0]);
					// try to get vertex matching edge to be added
					Vertex edge = getVertexWithName(tokens[1]);
					// if edge vertex doesn't exist in map yet, create the edge vertex and connect
					// it to vertex
					if (edge == null) {
						Vertex newEdge = new Vertex(tokens[1]);
						if (newEdge != null) {
							map = addVert(map.length, map, newEdge);
						}
						
						vertex.addNeighbour(new Edge(newEdge, Double.valueOf(tokens[2])));
						
						if (newEdge != null) {
							listVertAdded.add(tokens[1]);
						}

					} else {
						vertex.addNeighbour(new Edge(edge, Double.valueOf(tokens[2])));
						
					}
				}
				if (i > vertices) {
					System.out.println("Additional vertices were found in the map file that were counted in the total"
							+ "amount of vertices. Please check the value from line in the map file "
							+ "and try again or the path finding may not be accurate.");
					return null;
				}
				

			}
			
		} catch (Exception e) {
			if (e instanceof FileNotFoundException) {
				System.out.println("Map file with name " + mapFile + " could not be found. Please make sure file "
						+ "is in the same directory as program or check file name again.");
				return null;
			} else if (e instanceof NumberFormatException) {
				System.out.println("The first value in the map file must be an integer specifying the amount of"
						+ " vertices in the map. Please check map file line 1 and try again.");
				return null;
			}
		}
		return map;
	}
}
