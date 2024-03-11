import java.util.Random;

public class MonteCarlo implements IMonteCarlo {
	//Exercise 1.a
	private final float length, width;
	private final Rectangle[] rectangles;
	private Random random = new Random();

	/*
	 * Constructor
	 * @param length - length of the enclosing rectangle
	 * @param width - width of the enclosing rectangle
	 * @param embeddedRectangles[] - array that contains the embedded rectangles  
	 * @throws IllegalArgumentException if embeddedRectangles is null.
	 */
	public MonteCarlo(float length, float width, Rectangle[] embeddedRectangles) throws IllegalArgumentException{

		if(embeddedRectangles.equals(null)) {
			throw new IllegalArgumentException();
		}else{
			this.rectangles = embeddedRectangles;
		}
		this.length = length;
		this.width = width;
	}
	
	/**
	 * Method to calculate/estimate the area of the enclosing rectangle, which is not covered by
	 * the embedded rectangles.
	 * 
	 * @param numOfShots - Number of generated random points whose location (inside/outside) is analyzed. Must be greater than 0.
	 * @throws IllegalArgumentException if numOfShots is not greater than 0.
	 */
	public float area(int numOfShots) throws IllegalArgumentException{
		if(numOfShots <= 0){
			throw new IllegalArgumentException();
		}
		//numOfShots > 0
		int hits = 0;

		for(int i = 0; i < numOfShots; i++){
			float x = random.nextFloat()*length;
			float y = random.nextFloat()*width;

			for(Rectangle rectangle: rectangles){
				if(inside(rectangle, x ,y)){
					hits++;
					break;
				}
			}
		}
		float totalArea = length * width;
		return (totalArea - (totalArea * ((float) hits/numOfShots)));
	}
	
	/**
	 * Method to determine if a given point is inside the given rectangle.
	 * 
	 * @param rect - Given rectangle 
	 * @param x,y  - Coordinates of the point to check
	 * @throws IllegalArgumentException if rect is null.
	 */
	public boolean inside(Rectangle rect, float x, float y) throws IllegalArgumentException{
		if(rect.equals(null)){
			throw new IllegalArgumentException();
		}else{
			return x >= rect.originX && x <= (rect.originX + rect.length) &&
					y >= rect.originY && y <= (rect.originY + rect.width);
		}
	}
	
	public static void main(String[] args) {
		//Exercise 1.b
		//enclosing rectangle: 100 x 30 -> embedded rectangle 50 x 30 (half of the enclosing rectangle)
		Rectangle bigRect = new Rectangle(0,0, 50, 30);
		Rectangle [] rects = new Rectangle[] {bigRect};
		MonteCarlo mc = new MonteCarlo(100.0f,30.0f, rects);

		float area;
		area = mc.area(10);
		System.out.println("The area of the enclosing rectangle 100.0 x 30.0 with 10 shots is: " + area);
		mc.area(100);
		System.out.println("The area of the enclosing rectangle 100.0 x 30.0 with 100 shots is: " + area);
		mc.area(1000);
		System.out.println("The area of the enclosing rectangle 100.0 x 30.0 with 1000 shots is: " + area);
		mc.area(100000);
		System.out.println("The area of the enclosing rectangle 100.0 x 30.0 with 100000 shots is: " + area);

	}
}
