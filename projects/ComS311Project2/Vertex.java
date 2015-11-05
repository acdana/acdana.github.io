package assignment7impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Vertex implements Comparable<Vertex> {
	private int id;
	private int graphAssignedId;
	List<Edge> edges;
	List<Edge> inEdges;
	private int color;
	private double distance;
	private Vertex previous;
	
	/**
	 * This constructor for Vertex creates a basic vertex with the given id
	 * @param id the vertex id to give this vertex
	 */
	public Vertex(int id) {
		this.id = id;
		color = 0;
		this.edges = new ArrayList<Edge>();
		this.inEdges = new ArrayList<Edge>();
		distance = 999999999;
	}
	
	/**
	 * Gets the vertex id
	 * @return vertex id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Adds an edge to the vertex
	 * @param e edge to add to the vertex
	 */
	public void addEdge(Edge e) {
		if(!edges.contains(e)) {
			edges.add(e);
		}
	}
	
	/**
	 * Removes an edge on the vertex
	 * @param e the edge to remove from the vertex
	 */
	public void removeEdge(Edge e) {
		edges.remove(e);
	}

	/**
	 * Function to get edge ids that are leaving this vertex
	 * @return The set of edge ids of the vertex
	 */
	public Set<Integer> getRawEdgeSet() {
		Set<Integer> edgeSet = new HashSet<Integer>();
		for(Edge edge : edges) {
			edgeSet.add(edge.getEdgeId());
		}
		return edgeSet;
	}
	
	/**
	 * Gets the number of edges into this vertex
	 * @return number of in edges
	 */
	public int numInEdges() {
		return inEdges.size();
	}
	
	/**
	 * Adds an edge into the vertex
	 * @param inEdge edge to add in
	 */
	public void addInEdge(Edge inEdge) {
		inEdges.add(inEdge);
	}
	
	/**
	 * Gets the list of edges of a vertex
	 * @return The list of edges of the vertex
	 */
	public List<Edge> getEdgeSet() {
		return edges;
	}
	
	/**
	 * Gets the color of the vertex in an int form
	 * @return color of vertex
	 */
	public int getColor() {
		return color;
	}
	
	/**
	 * Sets the color of the vertex
	 * @param newColor color to set
	 */
	public void setColor(int newColor) {
		color = newColor;
	}

	/**
	 * Gets the id of the vertex assignmed by the graph on creation
	 * @return the graph assigned id
	 */
	public int getGraphAssignedId() {
		return graphAssignedId;
	}

	/**
	 * Sets the id of the vertex given by the graph
	 * @param graphAssignedId id to assign
	 */
	public void setGraphAssignedId(int graphAssignedId) {
		this.graphAssignedId = graphAssignedId;
	}

	/**
	 * Gets the distance of a vertex (used by Dijsktras algorithm)
	 * @return the vertex distance/weight
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * Sets the distance/weight of a vertex (used for Dijsktras algorithm)
	 * @param distance distance to assign
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}

	/**
	 * Gets the previous vertex of this vertex (for Dijsktras path)
	 * @return previous vertex from Dijkstras getpath
	 */
	public Vertex getPrevious() {
		return previous;
	}

	/**
	 * Sets the previous vertex of this vertex
	 * @param previous The previous vertex to assign
	 */
	public void setPrevious(Vertex previous) {
		this.previous = previous;
	}

	/**
	 * Method used for comparing two vertices based on id
	 * @return an int representing the difference of two vertices
	 */
	@Override
	public int compareTo(Vertex o) {
		if(this.getId() < o.getId()) {
			return -1;
		}
		if(this.getId() > o.getId()) {
			return 1;
		}
		return 0;
	}
	
}
