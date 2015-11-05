package assignment7impl;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author Alex Dana
 *
 *This class is used for edges in our graph implementation. It contains all necassary information
 *to create detailed edges between vertices.
 */
public class Edge {
	
	static AtomicInteger edgeIdAssigner = new AtomicInteger();//Used to assign unique ids to all edges on creation
	
	private int edgeId;
	private Vertex destination;
	private Vertex source;
	
	/**
	 * Constructor used to create an edge object. 
	 * @param source the source vertex of the edge
	 * @param destination the destination vertex of the edge
	 */
	public Edge(Vertex source, Vertex destination) {
		this.setEdgeId(edgeIdAssigner.incrementAndGet());
		this.destination = destination;
		this.source = source;
	}
	
	/**
	 * Used to get the edges id
	 * @return the id of the current edge
	 */
	public int getEdgeId() {
		return edgeId;
	}

	/**
	 * Used to set the edges id (typically not used)
	 * @param edgeId
	 */
	public void setEdgeId(int edgeId) {
		this.edgeId = edgeId;
	}
	
	/**
	 * Used to retrieve the destination vertex of the edge. Useful for traversing
	 * the graph.
	 * @return the destination vertex of the edge
	 */
	public Vertex getDestination() {
		return destination;
	}

	/**
	 * Used to retrieve the source vertex of the edge.
	 * @return source vertex of edge
	 */
	public Vertex getSource() {
		return source;
	}

	/**
	 * Used to set the source vertex of an edge
	 * @param source
	 */
	public void setSource(Vertex source) {
		this.source = source;
	}


	
}
