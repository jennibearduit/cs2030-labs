import java.util.Scanner;

/**
 * CS2030S Lab 0: Estimating Pi with Monte Carlo
 * Semester 2, 2020/21
 *
 * This program takes in two command line arguments: the 
 * number of points and a random seed.  It runs the
 * Monte Carlo simulation with the given argument and print
 * out the estimated pi value.
 *
 * @author Jennifer (Group B03) 
 */


class Lab0 {

  public static double estimatePi(long numOfPoints, int seed) {
	  double radius = 0.5;
	  Point centre = new Point(0, 0);

	  Circle circle = new Circle(centre, radius);
	  double minX = centre.getX() - radius;
	  double maxX = centre.getX() + radius;
	  double minY = centre.getY() - radius;
	  double maxY = centre.getY() + radius;

	  Point p = new RandomPoint(minX, maxX, minY, maxY);

	  double numOfContainedPoints = 0;
	  for (int i = 0; i < numOfPoints; i++) {
		  if (circle.contains(p)) {
			  numOfContainedPoints++;
		  }
		  p = new RandomPoint(minX, maxX, minY, maxY);
	  }
	  double estimatedPi = 4 * numOfContainedPoints / numOfPoints;
	  return estimatedPi;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int numOfPoints = sc.nextInt();
    int seed = sc.nextInt();

    double pi = estimatePi(numOfPoints, seed);

    System.out.println(pi);
    sc.close();
  }
}
