import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;


import java.util.concurrent.TimeUnit;

public class Assignment01Exercise01Test {

	public class Point{
		private float x;
		private float y;

		Point(float x, float y){
			this.x = x;
			this.y = y;
		}

		public float getX(){return this.x;}
		public float getY(){return this.y;}
	}

	/**************************************************************************************************************
	 * ************************************
	 * Test MC
	 *
	 * ************************************
	 *************************************************************************************************************/


	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testMCwith3rects() {
		// area of enclosing rectangle is 150
		// area of embedded rectangles is 52
		// result is: 150-52=98

		Rectangle rect1 = new Rectangle(0.0f,0.0f,4.0f,4.0f);
		Rectangle rect2 = new Rectangle(6.0f,3.0f,6.0f,4.0f);
		Rectangle rect3 = new Rectangle(10.0f,5.0f,4.0f,4.0f);
		Rectangle [] rects = new Rectangle[] {rect1, rect2, rect3};

		MonteCarlo mc = new MonteCarlo(15.0f,10.0f, rects);

		double area = mc.area(1000000);
		System.out.println("area(10000000) = "+area);
		assertTrue((area >= 97.5f && area <= 98.5f),
				"MonteCarlo.area(10000000) with the enclosing rectangle 15.0x10.0 "+
				"and the following \n\tembedded rectangles failed (area should be +/- 0.5 to the result 98 but your result is "+area+"): "+printRectangleData(rects));
	}


	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testMCwith2Rects() {
		// area of enclosing rectangle is 50
		// area of embedded rectangles is 2+12=14
		// result is: 50-14=36

		// embedded rectangle 1 is at position (0,0) with a size of 1x2
		Rectangle rect1 = new Rectangle(0.0f,0.0f,1.0f,2.0f);
		// embedded rectangle 2 is at position (7,1) with a size of 3x4
		Rectangle rect2 = new Rectangle(7.0f,1.0f,3.0f,4.0f);
		Rectangle [] rects = new Rectangle[] {rect1, rect2};

		MonteCarlo mc = new MonteCarlo(10.0f, 5.0f, rects);

		double area = mc.area(10000);
		// for 10k random points the estimated area should already be close to correct result of 36
		assertTrue(area > 30 && area < 40,
				"MonteCarlo.area() according to the provided unit test failed!");
	}

	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testInsideRectBorderline1Y() {
		Rectangle rect1 = new Rectangle(0.0f,0.0f,4.0f,4.0f);
		Rectangle [] rects = new Rectangle[] {};

		MonteCarlo mc = new MonteCarlo(15.0f,10.0f, rects);

		Point pt = new Point(1.0f, 4.0f);

		assertTrue(mc.inside(rect1, pt.getX(), pt.getY()),
				"MonteCarlo.inside() with point (x="+pt.getX()+"/y="+pt.getY()+") and the rectangle "
						+printRectangleData(rect1)+ " returned wrong result!");
	}

	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testInsideRectBorderline2Y() {
		Rectangle rect1 = new Rectangle(0.0f,0.0f,4.0f,4.0f);
		Rectangle [] rects = new Rectangle[] {};

		MonteCarlo mc = new MonteCarlo(15.0f,10.0f, rects);

		Point pt = new Point(1.0f, 0.0f);

		assertTrue(mc.inside(rect1, pt.getX(), pt.getY()),
				"MonteCarlo.inside() with point (x="+pt.getX()+"/y="+pt.getY()+") and the rectangle "
						+printRectangleData(rect1)+ " returned wrong result!");
	}

	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testInsideRectBorderline3Y() {
		Rectangle rect1 = new Rectangle(0.0f,0.0f,4.0f,4.0f);
		Rectangle [] rects = new Rectangle[] {};

		MonteCarlo mc = new MonteCarlo(15.0f,10.0f, rects);

		Point pt = new Point(1.0f, 4.01f);

		assertFalse(mc.inside(rect1, pt.getX(), pt.getY()),
				"MonteCarlo.inside() with point (x="+pt.getX()+"/y="+pt.getY()+") and the rectangle "
						+printRectangleData(rect1)+ " returned wrong result!");
	}

	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testInsideRectBorderline4Y() {
		Rectangle rect1 = new Rectangle(0.0f,0.0f,4.0f,4.0f);
		Rectangle [] rects = new Rectangle[] {};

		MonteCarlo mc = new MonteCarlo(15.0f,10.0f, rects);

		Point pt = new Point(1.0f, -0.01f);

		assertFalse(mc.inside(rect1, pt.getX(), pt.getY()),
				"MonteCarlo.inside() with point (x="+pt.getX()+"/y="+pt.getY()+") and the rectangle "
						+printRectangleData(rect1)+ " returned wrong result!");
	}

	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testInsideRectBorderline1X() {
		Rectangle rect1 = new Rectangle(0.0f,0.0f,4.0f,4.0f);
		Rectangle [] rects = new Rectangle[] {};

		MonteCarlo mc = new MonteCarlo(15.0f,10.0f, rects);

		Point pt = new Point(0.0f, 3.0f);

		assertTrue(mc.inside(rect1, pt.getX(), pt.getY()),
				"MonteCarlo.inside() with point (x="+pt.getX()+"/y="+pt.getY()+") and the rectangle "
						+printRectangleData(rect1)+ " returned wrong result!");
	}

	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testInsideRectBorderline2X() {
		Rectangle rect1 = new Rectangle(0.0f,0.0f,4.0f,4.0f);
		Rectangle [] rects = new Rectangle[] {};

		MonteCarlo mc = new MonteCarlo(15.0f,10.0f, rects);

		Point pt = new Point(4.0f, 3.0f);

		assertTrue(mc.inside(rect1, pt.getX(), pt.getY()),
				"MonteCarlo.inside() with point (x="+pt.getX()+"/y="+pt.getY()+") and the rectangle "
						+printRectangleData(rect1)+ " returned wrong result!");
	}

	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testInsideRectBorderline3X() {
		Rectangle rect1 = new Rectangle(0.0f,0.0f,4.0f,4.0f);
		Rectangle [] rects = new Rectangle[] {};

		MonteCarlo mc = new MonteCarlo(15.0f,10.0f, rects);

		Point pt = new Point(4.01f, 3.0f);

		assertFalse(mc.inside(rect1, pt.getX(), pt.getY()),
				"MonteCarlo.inside() with point (x="+pt.getX()+"/y="+pt.getY()+") and the rectangle "
						+printRectangleData(rect1)+ " returned wrong result!");
	}

	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testInsideRectBorderline4X() {
		Rectangle rect1 = new Rectangle(0.0f,0.0f,4.0f,4.0f);
		Rectangle [] rects = new Rectangle[] {};

		MonteCarlo mc = new MonteCarlo(15.0f,10.0f, rects);

		Point pt = new Point(-0.01f, 3.0f);

		assertFalse(mc.inside(rect1, pt.getX(), pt.getY()),
				"MonteCarlo.inside() with point (x="+pt.getX()+"/y="+pt.getY()+") and the rectangle "
						+printRectangleData(rect1)+ " returned wrong result!");
	}

	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testMCwith0Rects1Shot() {
		// area of enclosing rectangle is 50
		// area of embedded rectangles is 0

		Rectangle [] rects = new Rectangle[] {};

		MonteCarlo mc = new MonteCarlo(10.0f, 5.0f, rects);

		double area = mc.area(1);
		assertEquals((int)area, 50, "MonteCarlo.area(1) with area 10.0x5.0 and NO embedded rectangles returned wrong result: ");
	}

	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testMCwith0Rects100Shot() {
		// area of enclosing rectangle is 50
		// area of embedded rectangles is 0

		Rectangle [] rects = new Rectangle[] {};

		MonteCarlo mc = new MonteCarlo(10.0f, 5.0f, rects);

		double area = mc.area(100);
		assertEquals((int)area, 50, "MonteCarlo.area(100) with area 10.0x5.0 and NO embedded rectangles returned wrong result: ");
	}

	@Test
	@Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
	public void testMCwithMaxEmbeddedRects() {
		// area of enclosing rectangle is 150
		// area of embedded rectangles is 150

		Rectangle rect1 = new Rectangle(6.0f,3.0f,6.0f,4.0f);
		Rectangle rect2 = new Rectangle(10.0f,5.0f,4.0f,4.0f);
		Rectangle rect3 = new Rectangle(0.0f,0.0f,15.0f,10.0f);
		Rectangle [] rects = new Rectangle[] {rect1, rect2, rect3};

		MonteCarlo mc = new MonteCarlo(15.0f,10.0f, rects);

		double area = mc.area(10000000);

		assertTrue(area <= 0.5f, "MonteCarlo.area(10000000) with the enclosing rectangle 15.0x10.0 "+
				"and the following \n\tembedded rectangles failed (area should be <=0.5 but your result is "+area+"): "+printRectangleData(rects)
		);
	}



	/*************************************
	 *
	 * private methods
	 *
	 */
	private String printRectangleData(Rectangle rect) {
		StringBuilder sb = new StringBuilder();

		sb.append(" (x=");
		sb.append(rect.originX+"; y="+rect.originY+";");
		sb.append(" length=");
		sb.append(rect.length+"; width="+rect.width+")");


		return sb.toString();
	}

	private String printRectangleData(Rectangle [] rects) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < rects.length; i++) {
			sb.append("\n\tRectangle "+(i+1)+ "(x=");
			sb.append(rects[i].originX+"; y="+rects[i].originY+";");
			sb.append(" length=");
			sb.append(rects[i].length+"; width="+rects[i].width+")");
		}

		return sb.toString();
	}
}

