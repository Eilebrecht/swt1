package org.iMage.geometrify;

import java.awt.Point;

/**
 * Provides an infinite source of points at random coordinates within a given
 * range.
 *
 * @author Dominic Ziegler, Joshua Eilebrecht
 * @version 1.1
 */
public class RandomPointGenerator implements IPointGenerator {

	/*
	 * maximum values of the coordinate space the instance is working in.
	 */
	int maxX;
	int maxY;

	/**
	 * Constructs the generator for points within the specified coordinate
	 * space.
	 *
	 * @param width
	 *            the maximum x coordinate
	 * @param height
	 *            the maximum y coordinate
	 */
	public RandomPointGenerator(int width, int height) {
		maxX = width;
		maxY = height;
	}

	@Override
	public Point nextPoint() {
		/*
		 * this method will force a long into int, however, there will be no
		 * overflow, as the numbers can only have a value between 0 and an int
		 * value anyways.
		 */
		return new Point(new Long(Math.round(Math.random() * maxX - 1)).intValue(),
				new Long(Math.round(Math.random() * maxY - 1)).intValue());
	}
}
