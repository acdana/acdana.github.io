package assignment7impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import assignment7.Graph;

public class CoffeeGraph implements Graph<Vertex, Edge> {

	private int vertexID;
	private int edgeID;
	private List<Vertex> vertexList;
	private List<Edge> edgeList;

	
	public CoffeeGraph() {
		vertexID = 0;
		edgeID = 0;
		this.vertexList = new ArrayList<Vertex>();
		this.edgeList = new ArrayList<Edge>();
	}
	
	@Override
	/**
	 * Adds a vertex to the graph
	 * @param v the vertex to add
	 * @return the generated unique id of the vertex
	 */
	public int addVertex(Vertex v) {
		v.setGraphAssignedId(vertexID);
		vertexList.add(v);
		vertexID++;
		return vertexID-1;
	}

	@Override
	/**
	 * Adds an edge to the graph
	 * @param srcID the id of the source vertex
	 * @param targetID the id of the target vertex
	 * @param attr the edge object associated with the edge
	 * @return the unique edge id generated
	 */
	public int addEdge(int srcID, int targetID, Edge attr) throws IllegalArgumentException {
		if(srcID == targetID) {
			return -999;
		}
		
		Vertex sourceVertex = null;
		Vertex targetVertex = null;
		for(Vertex vertex : vertexList) {
			if(vertex.getId() == targetID) {
				targetVertex = vertex;
			}
			else if(vertex.getId() == srcID) {
				sourceVertex = vertex;
			}
		}

		targetVertex.addInEdge(attr);
		sourceVertex.addEdge(attr);
		edgeList.add(attr);
		edgeID++;
		return edgeID-1;
	}

	@Override
	/**
	 * Used to get a set of integers representing the vertices of the graph
	 * @return a set of integers representing the vertices of the graph
	 */
	public Set<Integer> getVertices() {
		Set<Integer> vertexSet = new HashSet<Integer>();
		for(Vertex vertex : vertexList) {
			vertexSet.add(vertex.getId());
		}
		return vertexSet;
	}

	@Override
	/**
	 * Used to get a set of integers representing the edges of the graph
	 * @param a set of integers representing the edges of the graph
	 */
	public Set<Integer> getEdges() {
		Set<Integer> edgeSet = new HashSet<Integer>();
		for(Edge edge : edgeList) {
			edgeSet.add(edge.getEdgeId());
		}
		return edgeSet;
	}

	@Override
	/**
	 * Used to get the Edge object associated with the given edge id
	 * @param id the id of the edge to get
	 * @return the edge associated with the given id
	 */
	public Edge getAttribute(int id) throws IllegalArgumentException {
		if(id >= 0) {
			return edgeList.get(id);
		}
		throw new IllegalArgumentException();
	}

	@Override
	/**
	 * Used to get the Vertex object associated with the given vertex id
	 * @param id the id of the vertex to get
	 * @return the vertex associated with the given id
	 */
	public Vertex getData(int id) throws IllegalArgumentException {
		if(id >= 0) {
			return vertexList.get(id);
		}
		throw new IllegalArgumentException();
	}

	@Override
	/**
	 * Used to get the source vertex id of the edge with the given id
	 * @param id the id of the edge to get the source of
	 * @return the id of the source vertex
	 */
	public int getSource(int id) throws IllegalArgumentException {
		if(id >= 0) {
			return edgeList.get(id).getSource().getId();
		}
		throw new IllegalArgumentException();
	}

	@Override
	/**
	 * Used to get the target vertex id of the edge with the given id
	 * @param id the id of the edge to get the target of
	 * @return the id of the target vertex
	 */
	public int getTarget(int id) throws IllegalArgumentException {
		if(id >= 0) {
			return edgeList.get(id).getDestination().getId();
		}
		throw new IllegalArgumentException();
	}

	@Override
	/**
	 * Gets the edge set of a given vertex id
	 * @param id the id of the vertex to get the edge set of
	 * @return a set of integers representing edge ids
	 */
	public Set<Integer> getEdgesOf(int id) throws IllegalArgumentException {
		if(id >= 0) {
			return  vertexList.get(id).getRawEdgeSet();
		}
		throw new IllegalArgumentException();
	}
	
	/**
	 * Gets the list of vertices associated with the graph
	 * @return list of Vertex objects of the graph
	 */
	public List<Vertex> getVertexList() {
		return vertexList;
	}
	
	/**
	 * Gets the list of edges associated with the graph
	 * @return list of Edge objects of the graph
	 */
	public List<Edge> getEdgeList() {
		return edgeList;
	}
	

}




