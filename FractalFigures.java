package FractalFigures;

import edu.princeton.cs.introcs.StdDraw;
import java.awt.Color;

public class FractalFigures {
		
	public static void drawFractalSquares(int grad) {
		int amount = 3; // amount of squares in a row
		double scale = Math.pow(amount, grad);

		StdDraw.setXscale(0, scale); // prepare board
		StdDraw.setYscale(0, scale);
		StdDraw.setPenColor(Color.GRAY);
		
		drawSquareSimple(grad, amount, 0.0, 0.0, scale);

	}

	private static void drawSquareSimple(int grad, int amount, double posX, double posY, double step) {

		for (double i = posX; i < posX + step; i += step / amount) { // step represents the amount of space between squares at current grad

			for (double j = posY; j < posY + step; j += step / amount) {
				
				if ((i - posX + j - posY) % 2 == 0) {
					if (grad == 1) StdDraw.filledSquare(0.5 + i, 0.5 + j, 0.5);
					else drawSquareSimple(grad - 1, amount, i, j, step / amount);
				}
			}
		}
	}
	
	public static void drawFiboCurve(int grad)
	{
		StdDraw.setXscale(0, getFiboNumber(grad) + getFiboNumber(grad - 1)); // prepare board
		StdDraw.setYscale(0, getFiboNumber(grad) + getFiboNumber(grad - 1));
		StdDraw.setPenColor(Color.GRAY);
		StdDraw.setPenRadius(0.005);
		drawFiboPart(grad, getFiboNumber(grad), 0, 0);
	}
	
	
	private static void drawFiboPart(int amount, int x, int y, int dir)
	{
		int num = getFiboNumber(amount);
		int prevNum = getFiboNumber(amount - 2);
		
		drawCurve(x, y, num, dir); // draws curve with center at x, y, radius is current fibonacci number
		
		if (dir == 0) y += prevNum; // shift center to the next position
		else if (dir == 1) x += prevNum;
		else if (dir == 2) y -= prevNum;
		else if (dir == 3) x -= prevNum;
		
		if(amount - 2 > 0) drawFiboPart(amount - 1, x, y, (dir + 1) % 4);
		
	}
	
	private static void drawCurve(double x, double y, double r, double dir)
	{
		double step = 2*Math.PI/50;
		double shift = 0.0;
		
		if(dir == 0) shift = Math.PI / 2 * 1.0; // right up
		else if(dir == 1) shift = Math.PI / 2 * 0.0; // left up
		else if (dir == 2) shift = Math.PI / 2 * 3.0; // left down
		else if (dir == 3) shift = Math.PI / 2 * 2.0; // right down
		
		for(double i = shift + step; i < Math.PI / 2 + shift + step; i += step)
		{
			// set color calue according to position on the curve
			int colValue = dir == 0 || dir == 1 ? (int)(Math.toDegrees(i) * 250.0 / 360.0) : 255 - (int)(Math.toDegrees(i) * 250.0 / 360.0) ;
			
			StdDraw.setPenColor(new Color(colValue, 255 - colValue, 255));
			StdDraw.line(x + r * Math.cos(i - step), y + r * Math.sin( i - step), x + r * Math.cos(i), y + r * Math.sin(i));
		}
	}
	
	private static int getFiboNumber(int pos)
	{
		if(pos == 0 || pos == 1) return 1;
		return getFiboNumber(pos - 1) + getFiboNumber(pos - 2);
	}
}
