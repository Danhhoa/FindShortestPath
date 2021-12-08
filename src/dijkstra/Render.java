package dijkstra;

import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;

import dijkstra.RenderMap.MyEdge;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.util.mxConstants;



public class Render extends JFrame
{
	public static List<Vertex> shortestPath = App.path;
	public static ArrayList<String> listNodes;
	public static Object[] arrV = null;
	public static ArrayList<Object> obj ;
	public static HashMap<String, Object> hm;
	
    public static void RenderMap(String filename) throws NumberFormatException, IOException
    {
    	createAndShowGUI(filename);
        
    }
    
    public static boolean contains(String name) {
		for (Object v : arrV) {
			if (v != null && v.toString().compareTo(name) == 0)
				return true;
		}
		return false;
	}

	public static Object getVertexWithName(String name) {
		for (Object v : arrV) {
			if (v != null && v.toString().compareTo(name) == 0)
				return v;
		}
		return null;
	}

	public static Object[] addVert(int n, Object[] arrV2, Vertex x) {
		int i;
		Object newMap[] = new Vertex[n + 1];
		for (i = 0; i < n; i++) {
			newMap[i] = arrV2[i];
			newMap[n] = x;
		}
		return newMap;

	}
    
    private static void createAndShowGUI(String filename) throws NumberFormatException, IOException {
    	JFrame frame = new JFrame("Đồ thị đường đi ngắn nhất");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setBounds(0,0,800,400);
        mxGraph graph = buildGraph(filename);
        mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
        layout.setOrientation(SwingConstants.WEST);
        layout.execute(graph.getDefaultParent());
        
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        
        frame.getContentPane().add(graphComponent);
        frame.setVisible(true);
    	
    	
//
//        mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
//        layout.execute(graph.getDefaultParent());

	}
    
    public static void startDraw(String filename) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					createAndShowGUI(filename);
				} catch (NumberFormatException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings("unlikely-arg-type")
	private static mxGraph buildGraph(String filename) throws NumberFormatException, IOException {
		mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        mxStylesheet stylesheet = graph.getStylesheet();
        Hashtable<String, Object> style = new Hashtable<>();
        style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
        style.put(mxConstants.STYLE_FONTCOLOR, "#774400");
        style.put(mxConstants.STYLE_FILLCOLOR, "#FFFFFF");
        style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
        //style.put(mxConstants.STYLE_ROUNDED, "true");
        stylesheet.putCellStyle("ROUNDED", style);

        graph.getModel().beginUpdate();
        try
        {
			System.out.println("PAHT:" +shortestPath);
			HashMap<String, String> shortestHM = new HashMap<String, String>();
			for (int i = 0; i < shortestPath.size()-1; i++) {
				shortestHM.put(shortestPath.get(i).toString(), shortestPath.get(i+1).toString());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			listNodes = new ArrayList<>();
			hm = new HashMap<>();
			int vertices = Integer.valueOf(br.readLine());
			if (vertices <= 0) {
				System.out.println("Hãy điền tổng số đỉnh của đồ thị vào dòng đầu tiên của file ");
//				return null;
			}
			String line;
			Object v;
			arrV = new Object[vertices];
			while (!(line = br.readLine()).equals("-1")) {
				String[] tokens = line.split("\\s+");
				System.out.println("XÉT " + line);
				if (!listNodes.contains(tokens[0])) {
					v = graph.insertVertex(parent, null, tokens[0], 20, 20, 50, 50,"ROUNDED");
					listNodes.add(tokens[0]);
					hm.put(tokens[0], v);
				}

				if (!listNodes.contains(tokens[1])) {
					v = graph.insertVertex(parent, null, tokens[1], 240, 150, 50, 50, "ROUNDED");
					hm.put(tokens[1], v);
					listNodes.add(tokens[1]);
					
						if (shortestHM.containsKey(tokens[0]) && shortestHM.get(tokens[0]).equals(tokens[1])) {
							
							graph.insertEdge(parent, null, tokens[2], hm.get(tokens[0]), hm.get(tokens[1]),"defaultEdge;strokeColor=red");
						} else {
							graph.insertEdge(parent, null, tokens[2], hm.get(tokens[0]), hm.get(tokens[1]));
						}

				} else {
					if (shortestHM.containsKey(tokens[0]) && shortestHM.get(tokens[0]).equals(tokens[1])) {
						
						graph.insertEdge(parent, null, tokens[2], hm.get(tokens[0]), hm.get(tokens[1]),"defaultEdge;strokeColor=red");
					} else {
						graph.insertEdge(parent, null, tokens[2], hm.get(tokens[0]), hm.get(tokens[1]));
					}
					
//					for (int i = 0; i < shortestPath.size()-1; i++) {
//						System.out.println("shortest: " + i + shortestPath.get(i) + shortestPath.get(i+1));
//						if (tokens[0].equals(shortestPath.get(i)) && tokens[1].equals(shortestPath.get(i+1))) {
//							graph.insertEdge(parent, null, tokens[2], hm.get(tokens[0]), hm.get(tokens[1]), "strokeColor=red" );
//						} else {
//							graph.insertEdge(parent, null, tokens[2], hm.get(tokens[0]), hm.get(tokens[1]));
//						}
//					}	
					
				}

			
//			Object v1 =  graph.insertVertex(parent, null, "A", 0, 0, 20, 20, "ROUNDED");
//            Object v2 =  graph.insertVertex(parent, null, "B", 0, 0, 20, 20, "ROUNDED");
//            Object v3 =  graph.insertVertex(parent, null, "C", 0, 0, 20, 20, "ROUNDED");
//            Object v4 =  graph.insertVertex(parent, null, "D", 0, 0, 20, 20, "ROUNDED");
//            
//            graph.insertEdge(parent, null, "10", v3, v4);
//            graph.insertEdge(parent, null, "10", v2, v3);
//            graph.insertEdge(parent, null, "15", v1, v2, "strokeColor=red");
            
        }
	}
        finally
        {
            graph.getModel().endUpdate();
        }
		return graph;
	}

}