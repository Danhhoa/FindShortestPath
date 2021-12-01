package dijkstra;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.Console;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultUndirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.swing.mxGraphComponent;


public class RenderMap {
	private static final long serialVersionUID = 2202072534703043194L;

    private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);

    private JGraphXAdapter<String, MyEdge> jgxAdapter;
    
    public static ArrayList<String> listNodes;
    
    
    public static void createAndShowGui(String filename) {
        JFrame frame = new JFrame("DemoGraph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SimpleDirectedWeightedGraph<String, MyEdge> g;
		try {
			g = buildGraph(filename);
			JGraphXAdapter<String, MyEdge> graphAdapter = new JGraphXAdapter<String, MyEdge>(g);
			   mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
		        layout.execute(graphAdapter.getDefaultParent());

		        frame.add(new mxGraphComponent(graphAdapter));

		        frame.setSize(400,400);
		        frame.setLocationByPlatform(true);
		        frame.setVisible(true);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

     
    }
    
    public static class MyEdge extends DefaultWeightedEdge {
        @Override
        public String toString() {
            return String.valueOf(getWeight());
        }
    }
    
    public static boolean contains(String name) {
		for (String node : listNodes) {
			if (node != null && node.toString().compareTo(name) == 0)
				return true;
		}
		return false;
	}

	public static String getNodeWithName(String name) {
		for (String node : listNodes) {
			if (node != null && node.toString().compareTo(name) == 0)
				return node;
		}
		return null;
	}
    
    public static SimpleDirectedWeightedGraph<String, MyEdge> buildGraph(String filename) throws NumberFormatException, IOException {
    	SimpleDirectedWeightedGraph<String, MyEdge> g = new SimpleDirectedWeightedGraph<String, MyEdge>(MyEdge.class);
    	 try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			listNodes = new ArrayList<>();
			int vertices = Integer.valueOf(br.readLine());
			if (vertices <= 0) {
				System.out.println("Hãy điền tổng số đỉnh của đồ thị vào dòng đầu tiên của file ");
				return null;
			}
			String line;
			int i = 0; // counter used to add vertices to map array
			while (!(line = br.readLine()).equals("-1")) {
				System.out.println(line);
				String[] tokens = line.split("\\s+");
				
				if (!listNodes.contains(tokens[0])) {
					listNodes.add(tokens[0]);
					g.addVertex(tokens[0]);
				}
				if (!listNodes.contains(tokens[1])) {
					listNodes.add(tokens[1]);
					g.addVertex(tokens[1]);
					MyEdge e = g.addEdge(tokens[0], tokens[1]);
					g.setEdgeWeight(e, Double.valueOf(tokens[2]));
				}
				
				else {
					MyEdge e = g.addEdge(tokens[0], tokens[1]);
					g.setEdgeWeight(e, Double.valueOf(tokens[2]));
				}
				
				}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	for (String i : listNodes) {
    		System.out.println("Node: "+i);
    	}
    	for (Vertex y : App.path) {
			System.out.println("Path: " +y);
		}
		return g;
    	
    	
    }
    public static void startDraw(String filename) {
    	 SwingUtilities.invokeLater(new Runnable() {
             public void run() {
                 createAndShowGui(filename);
             }
         });
    }
    
    
    
}
