package assignment7impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import assignment7.Dijkstra;
import assignment7.Graph;
import assignment7.Weighing;

/**
 * 
 * @author Alex Dana
 *
 *This class is the implementation of the Dijkstra interface given with Vertex and Edge classes being
 *used for implementation.
 */
public class CoffeeDijkstra implements Dijkstra<Vertex,Edge> {

	private Graph<Vertex,Edge> startGraph; //graph to use for calculations
	private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	private int startId;
	private Weighing<Edge> weighing;
	public boolean isComputed = false; //used to determine if Dijsktras algorithm has been performed for the given startId and graph
	
	
	
	@Override
	/**
	 * this function sets the graph to be used with Dijkstras algorithm
	 */
	public void setGraph(Graph<Vertex, Edge> graph) {
		this.startGraph = graph;
	}
	
	@Override
	/**
	 * This function sets the startId and sets isComputed to false to let our program know that
	 * Dijkstras will need to be recomputed
	 */
	public void setStart(int startId) throws IllegalArgumentException, IllegalStateException {
		this.startId = startId;
		isComputed = false;
	}
	
	@Override
	/**
	 * Sets the weighing to be used for Dijkstras
	 * @param weighing the weighing to be set
	 */
	public void setWeighing(Weighing<Edge> weighing) {
		this.weighing = weighing;
	}
	
	@Override
	/**
	 * Computes the shortest path from the startId given to all other vertices
	 */
	public void computeShortestPath() throws IllegalStateException {
		vertices = new ArrayList<Vertex>();
		isComputed = true;
		Vertex currentVertex = startGraph.getData(startId);
		PriorityQueue<Vertex> vertexList = new PriorityQueue<Vertex>();
		vertexList.add(currentVertex);
		currentVertex.setDistance(0);
		
		//Here we reset the vertex colors after every calculation
		for(Vertex vertex : (ArrayList<Vertex>)((CoffeeGraph) startGraph).getVertexList()) {
			vertex.setColor(0);
		}
		
		//Basic implementation of Dijkstras algorithm.
		while(!vertexList.isEmpty()) {
			currentVertex = vertexList.poll();
			if(currentVertex.getColor() == 0) {
			currentVertex.setColor(1);
			for(Edge edge : currentVertex.getEdgeSet()) {
			
				Vertex tempVertex = edge.getDestination();
				double tempDistance = weighing.weight(edge);
				double totalDistance = currentVertex.getDistance() + tempDistance;
				if(totalDistance < tempVertex.getDistance()) {
					vertexList.remove(tempVertex);
					tempVertex.setDistance(totalDistance);
					tempVertex.setPrevious(currentVertex);
					vertexList.add(tempVertex);
					if(!vertices.contains(tempVertex)) {
						vertices.add(tempVertex);
					}
				}
				
			}
			}
		}
		
	}
	
	@Override
	/**
	 * Function used to get the path from our start id to the given end id
	 * @param endId the id of the vertex we want a path to
	 * @return a list of integers that represents the path to take
	 */
	public List<Integer> getPath(int endId) throws IllegalArgumentException, IllegalStateException {
		if(isComputed == false) {
			initializeGraph(this.startGraph);
			this.computeShortestPath();
		}

		List<Integer> path = new ArrayList<Integer>();
		//We simply loop through to find the endId and then follow its previous vertices to the start vertex
		for(Vertex vertex : vertices) {
			if(vertex.getId() == endId) {
				while(vertex != null && vertex.getId() != startId) {
					path.add(vertex.getId());
					vertex = vertex.getPrevious();
				}
				path.add(vertex.getId());
				break;
			}
		}
		
		//Since the path was traversed backwards we must reverse it before returning it
		for(int i = 0; i < path.size() / 2; i++) {
		    int temp = path.get(i);
		    path.set(i, path.get(path.size()-i-1));
		    path.set(path.size()-i-1, temp);
		}
		return path;
	}
	
	@Override
	/**
	 * Function to get the cost of traversing from the startId to the endId given
	 * @param endId the vertex id of the vertex we want the cost of
	 * @return a double representing the cost to get from the startId to the endId
	 */
	public double getCost(int endId) throws IllegalArgumentException, IllegalStateException {
		if(isComputed == false) {
			initializeGraph(this.startGraph);
			this.computeShortestPath();
		}
		for(Vertex vertex : vertices) {
			if(vertex.getId() == endId) {
				return vertex.getDistance();
			}
		}
		return 0;
	}
	
	
	/**
	 * This method is used to generate the full Ames graph  based on the AmesData.txt data.
	 * @return A graph containing all vertices and edges of the AmesData.txt file.
	 */
	public CoffeeGraph generateFullGraph() {

		CoffeeGraph graph = new CoffeeGraph();
		File file = new File("AmesData.txt");
		
		try {
			Scanner scanner = new Scanner(file);
			if(scanner.hasNext()) scanner.nextLine(); //Skip the initial line with the # of vertices
			
			//We iterate through the first section of the file to add all vertices
			while(scanner.hasNext()) {
				String rawOutput = scanner.nextLine();
				if(rawOutput.equals("EDGES: 15452")) break;
				String[] split = rawOutput.split(",");
				Vertex vertex = new Vertex(Integer.parseInt(split[0]));
				graph.addVertex(vertex);
			}
			List<Vertex> tempVertexList = graph.getVertexList();
			
			//We then iterate through and add the given edges
			while(scanner.hasNext()) {
				String rawOutput = scanner.nextLine();
				String[] split = rawOutput.split(",");

				Vertex sourceVertex = tempVertexList.get(Integer.parseInt(split[0]));
				Vertex destinationVertex = tempVertexList.get(Integer.parseInt(split[1]));

				Edge edge = new Edge(sourceVertex, destinationVertex);
				graph.addEdge(Integer.parseInt(split[0]), Integer.parseInt(split[1]), edge);
			}

			scanner.close();
			return graph;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}		
		
	}
	
	/**
	 * Helper function that resets the distances of vertices for the next call of
	 * Dijkstras algorithm
	 * @param graph the graph to reinitialize
	 */
	private void initializeGraph(Graph<Vertex, Edge> graph) {
		for(Vertex vertex : (ArrayList<Vertex>)((CoffeeGraph) graph).getVertexList()) {
			vertex.setDistance(999999999);
		}
	}


}
