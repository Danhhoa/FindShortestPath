package dijkstra;

import com.mxgraph.layout.*;
import com.mxgraph.swing.*;
import org.jgrapht.*;
import org.jgrapht.demo.JGraphXAdapterDemo;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;

import javax.swing.*;
import java.awt.*;

/**
 * A demo applet that shows how to use JGraphX to visualize JGraphT graphs. Applet based on
 * JGraphAdapterDemo.
 *
 */
public class test
    extends
    JApplet
{
    private static final long serialVersionUID = 2202072534703043194L;

    private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);

    private JGraphXAdapter<String, MyEdge> jgxAdapter;

    /**
     * An alternative starting point for this demo, to also allow running this applet as an
     * application.
     *
     * @param args command line arguments
     */
    private static void createAndShowGui() {
        JFrame frame = new JFrame("DemoGraph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultUndirectedWeightedGraph<String, MyEdge> g = buildGraph();
        JGraphXAdapter<String, MyEdge> graphAdapter = new JGraphXAdapter<String, MyEdge>(g);

        mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());

        frame.add(new mxGraphComponent(graphAdapter));

        frame.setSize(400,400);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
    
    public static class MyEdge extends DefaultWeightedEdge {
        @Override
        public String toString() {
            return String.valueOf(getWeight());
        }
    }
    
    public static  DefaultUndirectedWeightedGraph<String, MyEdge> buildGraph() {
        DefaultUndirectedWeightedGraph<String, MyEdge> g = 
            new DefaultUndirectedWeightedGraph<String, MyEdge>(MyEdge.class);

        String x1 = "x1";
        String x2 = "x2";
        String x3 = "x3";

        g.addVertex(x1);
        g.addVertex(x2);
        g.addVertex(x3);

        MyEdge e = g.addEdge(x1, x2);
        g.setEdgeWeight(e, 1);
        e = g.addEdge(x2, x3);
        g.setEdgeWeight(e, 2);

        e = g.addEdge(x3, x1);
        g.setEdgeWeight(e, 3);

        return g;
    }
    
    public static void main(String[] args)
    {
    	 SwingUtilities.invokeLater(new Runnable() {
             public void run() {
                 createAndShowGui();
             }
         });
    }

//    @Override
//    public void init()
//    {
//        // create a JGraphT graph
//        DefaultUndirectedWeightedGraph<String, DefaultWeightedEdge> g =
//            new DefaultUndirectedWeightedGraph<>(DefaultWeightedEdge.class);
//
//        // create a visualization using JGraph, via an adapter
//        jgxAdapter = new JGraphXAdapter<>(g);
//
//        setPreferredSize(DEFAULT_SIZE);
//        mxGraphComponent component = new mxGraphComponent(jgxAdapter);
//        component.setConnectable(false);
//        component.getGraph().setAllowDanglingEdges(false);
//        getContentPane().add(component);
//        resize(DEFAULT_SIZE);
//
//        String v1 = "v1";
//        String v2 = "v2";
//        String v3 = "v3";
//        String v4 = "v4";
//
//        // add some sample data (graph manipulated via JGraphX)
//        g.addVertex(v1);
//        g.addVertex(v2);
//        g.addVertex(v3);
//        g.addVertex(v4);
//
//        DefaultWeightedEdge e1 = g.addEdge(v1, v2);
//        g.setEdgeWeight(e1, 1);
//        DefaultWeightedEdge e2 = g.addEdge(v2, v3);
//        g.setEdgeWeight(e2, 3);
//        DefaultWeightedEdge e3 = g.addEdge(v3, v1);
//        g.setEdgeWeight(e3, 2);
//        DefaultWeightedEdge e4 = g.addEdge(v4, v3);
//        g.setEdgeWeight(e4, 5);
//        
//        // positioning via jgraphx layouts
//        mxCircleLayout layout = new mxCircleLayout(jgxAdapter);
//
//        // center the circle
//        int radius = 100;
//        layout.setX0((DEFAULT_SIZE.width / 2.0) - radius);
//        layout.setY0((DEFAULT_SIZE.height / 2.0) - radius);
//        layout.setRadius(radius);
//        layout.setMoveCircle(true);
//
//        layout.execute(jgxAdapter.getDefaultParent());
//        // that's all there is to it!...
//    }
}
