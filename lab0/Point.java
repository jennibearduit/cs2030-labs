/**
 * CS2030S Lab 0: Point.java
 * Semester 2, 2020/21
 *
 * The Point class encapsulates a point on a 2D plane.
 *
 * @author Jennifer (B03)
 */
import java.lang.Math;

class Point {
  
  private double x;
  private double y;

  public Point(double x, double y) {
	this.x = x;
	this.y = y;
  }

  public double distanceTo(Point p) { 
	return Math.sqrt(Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2));
  }

  public double getX() {
	return this.x;
  }

  public double getY() {
	  return this.y;
  }

  @Override
  public String toString() {
	return "(" + this.x + ", " + this.y + ")";
  } 

}
