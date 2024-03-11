
public interface IMonteCarlo {
	/**
	 * Method to calculate/estimate the area of the enclosing rectangle, which is not covered by
	 * the embedded rectangles.
	 * 
	 * @param numOfShots - Number of generated random points whose location (inside/outside) is analyzed. Must be greater than 0.
	 * @throws IllegalArgumentException if numOfShots is not greater than 0.
	 */
	public float area(int numOfShots);
	
	/**
	 * Method to determine if a given point is inside the given rectangle.
	 * 
	 * @param rect - Given rectangle 
	 * @param x,y  - Coordinates of the point to check
	 * @throws IllegalArgumentException if rect is null.
	 */
	public boolean inside(Rectangle rect, float x, float y);
}
