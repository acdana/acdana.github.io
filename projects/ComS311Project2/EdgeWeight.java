package assignment7impl;

/**
 * 
 * @author Alex Dana
 * This class is used to store Edge Weight data conveniently
 */
public class EdgeWeight {
	private int sourceId;
	private int destId;
	private double weight;
	
	public EdgeWeight(int sourceId, int destId, double weight) {
		this.sourceId = sourceId;
		this.destId = destId;
		this.weight = weight;
	}
	
	/**
	 * Used to get the source vertex id of the edge
	 * @return the sourceId of the EdgeWeight
	 */
	public int getSourceId() {
		return sourceId;
	}

	/**
	 * Used to get the destination vertex id of the edge
	 * @return the sourceId of the EdgeWeight
	 */
	public int getDestId() {
		return destId;
	}

	/**
	 * Gets the weight of the edgeweight
	 * @return the weight of the edgeweight
	 */
	public double getWeight() {
		return weight;
	}

}
