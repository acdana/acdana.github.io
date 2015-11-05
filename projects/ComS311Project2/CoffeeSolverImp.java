package assignment7impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import assignment7.CoffeeSolver;
import assignment7.Graph;
import assignment7.Weighing;

public class CoffeeSolverImp implements CoffeeSolver<Vertex,Edge> {

	@Override
	/**
	 * Sorts vertices in a topological fashion using the topologicalDFS function
	 * @param graph the graph to topologically sort
	 * @return a list of integers representing a topological sort
	 */
	public List<Integer> sortVertices(Graph<Vertex, Edge> graph) {
		List<Vertex> vertices = ((CoffeeGraph) graph).getVertexList();
		ArrayList<Integer> sortedVertices;
		boolean hasCycle = true;
		for(Vertex vertex : vertices) {
			if(vertex.numInEdges() == 0) {
				hasCycle = false;
			}
		}
		
		if(hasCycle == false) {
			sortedVertices = topologicalDFS((ArrayList<Vertex>)((CoffeeGraph) graph).getVertexList());
			for(int i = 0; i < sortedVertices.size() / 2; i++) {
			    int temp = sortedVertices.get(i);
			    sortedVertices.set(i, sortedVertices.get(sortedVertices.size()-i-1));
			    sortedVertices.set(sortedVertices.size()-i-1, temp);
			}
			return sortedVertices;
		}
		else {
			return null;
		}
	}


	@Override
	/**
	 * Computes the shortest path that visits all the locations in the graph in the order specified.
	 * @param graph the graph which we are to traverse
	 * @param locations the locations that must be visited
	 * @param weigh the weighing to use for computing the shortest path
	 */
	public List<Integer> shortestPath(Graph<Vertex, Edge> graph, List<Integer> locations, Weighing<Edge> weigh) {
		CoffeeDijkstra coffeeDij = new CoffeeDijkstra();
		coffeeDij.setGraph(graph);
		coffeeDij.setWeighing(weigh);
		
		coffeeDij.setStart(locations.get(0));
		List<Integer> path = new ArrayList<Integer>();
		for(int i = 0; i < locations.size()-1; i++) {
			coffeeDij.setStart(locations.get(i));
			List<Integer> tempList = coffeeDij.getPath(locations.get(i+1));
			if(i != 0 && tempList.size() > 0) {
				tempList.remove(0);
			}
			path.addAll(tempList);
		}
		return path;
	}

	@Override
	/**
	 * This function computes all valid topological sorts of a graph
	 * @param graph the graph to topologically sort
	 * @return a collection of valid topological sorts in a list form
	 */
	public Collection<List<Integer>> generateValidSortS(Graph<Vertex, Edge> graph) {
		ArrayList<Vertex> ourVertexList = (ArrayList<Vertex>)((CoffeeGraph) graph).getVertexList();

		ArrayList<ArrayList<Vertex>> combinations = permute(ourVertexList);	
		
		Collection<List<Integer>> validSorts = new ArrayList<List<Integer>>();
		
		//For each permutation of the vertices compute the topological sort and add it to a list of lists to return (if it isn't already in the list of lists)
		for(ArrayList<Vertex> list : combinations) {
			
			for(Vertex vertex : (ArrayList<Vertex>)((CoffeeGraph) graph).getVertexList()) {
				vertex.setColor(0);
			}
			
			ArrayList<Integer> tempList = topologicalDFS(list);
			for(int i = 0; i < tempList.size() / 2; i++) {
			    int temp = tempList.get(i);
			    tempList.set(i, tempList.get(tempList.size()-i-1));
			    tempList.set(tempList.size()-i-1, temp);
			}
			if(!validSorts.contains(tempList)) {
				validSorts.add(tempList);
			}
		}
		
		return validSorts;
	}

	/**
	 * This is a modified DFS used to topologically sort our vertices.
	 * @param ourVertexList is the list of vertices that need to be sorted topologically
	 */
	private ArrayList<Integer> topologicalDFS(ArrayList<Vertex> ourVertexList) {
		ArrayList<Integer> topList = new ArrayList<Integer>();
		while(topList.size() != ourVertexList.size()) {
			
			//We iterate through every vertex in the list to topologically sort
			for(Vertex vertex : ourVertexList) {
			
				//We find vertices that have no edges out and add them first to the list
				if(vertex.getEdgeSet().size() == 0 && vertex.getColor() == 0 && !topList.contains(vertex.getId())) {
					vertex.setColor(1);
					topList.add(vertex.getId());
				} 
				//otherwise we check if the target vertices are all colored
				//if not we color them. if so we add the source vertex to our list
				else {
					boolean isColor = true;
					for(Edge edge : vertex.getEdgeSet()) {
						if(edge.getDestination().getColor() == 0) {
							isColor = false;
							break;
						}
					}
					if(isColor == true) {
						if(!topList.contains(vertex.getId())) {
							vertex.setColor(1);
							topList.add(vertex.getId());
						}
					}
				}
			}
		
		}
		return topList;
		
	}
	
	
	/**
	 * This function is used to get all permutations of our vertex list used for
	 * topological sorting. This allows our algorithm to catch all possible topological sorts
	 * to truly find the shortest path available.
	 * 
	 * @param arr The array of vertices of the topological list
	 * @return A list of a list of vertices, each representing a permutation of arr
	 */
	private ArrayList<ArrayList<Vertex>> permute(ArrayList<Vertex> arr){
		ArrayList<ArrayList<Vertex>> listOfArrays = new ArrayList<ArrayList<Vertex>>();
		for(int i = 0; i < arr.size(); i++) {
        	ArrayList<Vertex> temp = new ArrayList<Vertex>();
        	for(int j = 0; j < arr.size(); j++) {
        		temp.add(arr.get(j));
        	}
        	Vertex tempVertex = temp.get(0);
        	temp.set(0, temp.get(i));
        	temp.set(i, tempVertex);
        	listOfArrays.add(temp);
    		
        }
		return listOfArrays;
    }
	
	
	
}
