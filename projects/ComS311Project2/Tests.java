package assignment7impl;

import java.util.List;

public class Tests {
	
	public static void main(String[] args) {
		
		CoffeeGraph ingredientsOrdered = new CoffeeGraph();
		
		Vertex A = new Vertex(1055);
		Vertex B = new Vertex(371);
		Vertex C = new Vertex(2874);
		Vertex D = new Vertex(2351);
		Vertex E = new Vertex(2956);
		Vertex F = new Vertex(1171);
		Vertex G = new Vertex(1208);
		Vertex YOU = new Vertex(2893);
		ingredientsOrdered.addVertex(YOU);
		ingredientsOrdered.addVertex(A);
		ingredientsOrdered.addVertex(B);
		ingredientsOrdered.addVertex(C);
		ingredientsOrdered.addVertex(D);
		ingredientsOrdered.addVertex(E);
		ingredientsOrdered.addVertex(F);
		ingredientsOrdered.addVertex(G);
		ingredientsOrdered.addEdge(1055, 371, new Edge(A, B));
		ingredientsOrdered.addEdge(1055, 2956, new Edge(A, E));
		ingredientsOrdered.addEdge(371, 2874, new Edge(B, C));
		ingredientsOrdered.addEdge(371, 2351, new Edge(B, D));
		ingredientsOrdered.addEdge(2351, 2956, new Edge(D, E));
		ingredientsOrdered.addEdge(2351, 1171, new Edge(D, F));
		ingredientsOrdered.addEdge(1055, 1208, new Edge(A, G));
		ingredientsOrdered.addEdge(371, 1208, new Edge(B, G));
		ingredientsOrdered.addEdge(2874, 1208, new Edge(C, G));
		ingredientsOrdered.addEdge(2351, 1208, new Edge(D, G));
		ingredientsOrdered.addEdge(2956, 1208, new Edge(E, G));
		ingredientsOrdered.addEdge(1171, 1208, new Edge(F, G));
		ingredientsOrdered.addEdge(2893, 1055, new Edge(YOU, A));
		ingredientsOrdered.addEdge(2893, 371, new Edge(YOU, B));
		ingredientsOrdered.addEdge(2893, 2874, new Edge(YOU, C));
		ingredientsOrdered.addEdge(2893, 2351, new Edge(YOU, D));
		ingredientsOrdered.addEdge(2893, 2956, new Edge(YOU, E));
		ingredientsOrdered.addEdge(2893, 1171, new Edge(YOU, F));
		ingredientsOrdered.addEdge(2893, 1208, new Edge(YOU, G));
		
		
		CoffeeSolverImp coffeeTest = new CoffeeSolverImp();

		
		CoffeeWeighing weigh = new CoffeeWeighing();
	
		CoffeeDijkstra dij = new CoffeeDijkstra();
		
		CoffeeGraph graph = dij.generateFullGraph();
		
		
		List<Integer> locationsToVisit = coffeeTest.sortVertices(ingredientsOrdered);
		System.out.println("Topological path: " + locationsToVisit);

		List<Integer> shortestPath = coffeeTest.shortestPath(graph, locationsToVisit, weigh);
		System.out.println("Shortest Path: " + shortestPath);
		
	}

	
}
