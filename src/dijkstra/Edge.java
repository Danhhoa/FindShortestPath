package dijkstra;

import org.jgrapht.graph.DefaultWeightedEdge;

public class Edge extends DefaultWeightedEdge {
	private Vertex targetVertex;
	private double weight;
	public Edge(Vertex targetVertex, double weight) {
		this.targetVertex = targetVertex;
		this.weight = weight;
	}
	public Vertex getTargetVertex() {
		return targetVertex;
	}
	public void setTargetVertex(Vertex targetVertex) {
		this.targetVertex = targetVertex;
	}
	public double getEdgeWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString () {
		return String.valueOf(getWeight());
		
	}
	
	
	
}
