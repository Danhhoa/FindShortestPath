package dijkstra;

import javax.swing.JFrame;
import java.util.*;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.io.*;
import org.jgrapht.ext.*;
import org.jgrapht.traverse.*;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.util.mxConstants;
import com.mxgraph.model.*;



public class HelloWorld extends JFrame
{
    private JGraphXAdapter<String, DefaultEdge> jgxAdapter;

    public HelloWorld()
    {
        super("Hello world!");

        Graph<String, DefaultEdge> graph =
            new DefaultDirectedGraph<>(DefaultEdge.class);

        graph.addVertex("hello");
        graph.addVertex("world");
        graph.addVertex("blarg");

        graph.addEdge("hello", "world");
        graph.addEdge("hello", "blarg");

        jgxAdapter = new JGraphXAdapter(graph);

        mxStylesheet stylesheet = jgxAdapter.getStylesheet();
        Hashtable<String, Object> style = new Hashtable<>();
        style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
        style.put(mxConstants.STYLE_FONTCOLOR, "#774400");
        style.put(mxConstants.STYLE_FILLCOLOR, "green");
        style.put(mxConstants.STYLE_STROKECOLOR, "red");
        stylesheet.putCellStyle("ROUNDED", style);

        new mxHierarchicalLayout(jgxAdapter).execute(jgxAdapter.getDefaultParent());        

        jgxAdapter.getModel().beginUpdate();

        try
        {
            HashMap<String, mxICell> cells = jgxAdapter.getVertexToCellMap();

            for(mxICell cell : cells.values())
            {
                cell.setStyle("strokeColor=red;fillColor=white;");
            }
        }
        finally
        {
            jgxAdapter.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(jgxAdapter);
        getContentPane().add(graphComponent);
    }

    public static void main (String[] args)
    {
        HelloWorld frame = new HelloWorld();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 320);
        frame.setVisible(true);
    }
}
