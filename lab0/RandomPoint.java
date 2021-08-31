import java.util.Random;

public class RandomPoint extends Point {
	private static Random rng = new Random(1);

	public RandomPoint(double minX, double maxX, double minY, double maxY) {
		super(getRandomDouble(minX, maxX), getRandomDouble(minY, maxY));
		// this.setX(randX);
		// this.setY(randY);
	}

	public static void setSeed(int seed) {
		RandomPoint.rng = new Random(seed);
	}

	private static double getRandomDouble(double min, double max) {
		return rng.nextDouble() * (max - min) + min;
	}
}
