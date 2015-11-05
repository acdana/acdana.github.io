package assignment7impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import assignment7.Weighing;

public class CoffeeWeighing implements Weighing<Edge> {

	
	private ArrayList<EdgeWeight> weights = new ArrayList<EdgeWeight>(); //Used to store our weights
	
	/**
	 * On creation of our CoffeeWeighing object we automatically call getLocations
	 * to store weights for later use with the weight funtction.
	 */
	public CoffeeWeighing() {
		getLocations();
	}
	
	@Override
	/**
	 * This function is used to calculate the weight of an edge
	 * @param edge the edge that we want the weight of
	 * @return a double representing the weight of an edge between two vertices
	 */
	public double weight(Edge edge) {
		for(EdgeWeight weight : weights) {
			if(weight.getSourceId() == edge.getSource().getId() && weight.getDestId() == edge.getDestination().getId()) {
				return weight.getWeight();
			}
		}
		
		return -999;
	}
	
	/**
	 * This function is used to retrieve all weighing data from the AmesData.txt file.
	 * A new edge weight is created for each pair of vertices containing an edge.
	 */
	private void getLocations() {	
			
		File file = new File("AmesData.txt");
		try {
			Scanner scanner = new Scanner(file);
			
			while(scanner.hasNext()) {
				String rawOutput = scanner.nextLine();
				if(rawOutput.equals("EDGES: 15452")) break;
			}
			
			while(scanner.hasNext()) {
				String rawOutput = scanner.nextLine();
				String[] split = rawOutput.split(",");
				weights.add(new EdgeWeight(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Double.parseDouble(split[2])));
			}
			scanner.close();
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}




}
