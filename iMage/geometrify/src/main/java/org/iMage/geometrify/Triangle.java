package org.iMage.geometrify;

import java.awt.Color;
import java.awt.Point;

/**
 * A triangle.
 *
 * @author Dominic Ziegler, Martin Blersch
 * @version 1.0
 */
public class Triangle implements IPrimitive {

	/*
	 * All 3 points to define the outline of the triangle.
	 */
	Point pointA;
	Point pointB;
	Point pointC;

	/*
	 * Boolean values on whether an x coordinate of one point is bigger than of
	 * another one or not.
	 */
	boolean isAXBiggerThanBX;
	boolean isAXBiggerThanCX;
	boolean isBXBiggerThanCX;

	/**
	 * Creates a new triangle from the given vertices.
	 *
	 * @param a
	 *            the first vertex
	 * @param b
	 *            the second vertex
	 * @param c
	 *            the third vertex
	 */
	public Triangle(Point a, Point b, Point c) {
		pointA = a;
		pointB = b;
		pointC = c;
	}

	@Override
	public boolean isInsidePrimitive(Point p) {
		/*
		 * YOUR SOLUTION HERE
		 */
		return false;
	}

	@Override
	public BoundingBox getBoundingBox() {
		/*
		 * YOUR SOLUTION HERE
		 */
		return null;
	}

	@Override
	public Color getColor() {
		/*
		 * YOUR SOLUTION HERE
		 */
		return null;
	}

	@Override
	public void setColor(Color c) {
		/*
		 * YOUR SOLUTION HERE
		 */
	}

}
