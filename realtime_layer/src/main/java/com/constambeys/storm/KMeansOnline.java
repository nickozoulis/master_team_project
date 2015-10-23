package com.constambeys.storm;

import java.io.Serializable;
import java.util.Arrays;

public class KMeansOnline implements Serializable {

	final int CONSTANT = 1;

	private class D implements Comparable<D> {
		double dist;
		int index;

		public int compareTo(D o) {
			return Double.compare(dist, o.dist);
		}
	}

	private class Ds {
		int size = 0;
		final int max = CONSTANT - 1;
		D[] d = new D[max];
	}

	boolean initilization = true;
	int counters[];
	Point means[]; // k points
	int k;

	public KMeansOnline(int k) {
		this.k = k;
		counters = new int[k * CONSTANT];
		means = new Point[k * CONSTANT];
	}

	public void run(Point point) {

		if (initilization) {
			boolean found = false;
			for (int i = 0; i < counters.length; i++) {
				if (counters[i] == 0) {
					means[i] = point;
					counters[i] = 1;
					found = true;
					break;
				}
			}
			if (found == false) {
				initilization = false;
				run(point);
			}
		} else {
			int index = 0;
			double min = Point.distance(means[0], point);

			for (int i = 1; i < means.length; i++) {
				double dist = Point.distance(means[i], point);
				if (dist < min) {
					min = dist;
					index = i;
				}
			}

			counters[index]++;
			// meansX[index] = meansX[index] + 1.0 / counters[index] * (x -
			// meansX[index]);
			// meansY[index] = meansY[index] + 1.0 / counters[index] * (y -
			// meansY[index]);
			means[index].multibly(counters[index] - 1);
			means[index].add(point);
			means[index].divide(counters[index]);
		}
	}

	public void print() {
		if (initilization) {
			return;
		}

		boolean[] used = new boolean[k * CONSTANT];

		for (int u1 = 0; u1 < used.length; u1++) {
			if (used[u1] == false) {
				used[u1] = true;

				Ds ds = new Ds();

				for (int u2 = 0; u2 < used.length; u2++) {

					if (used[u2] == false) {
						insertDistance(means[u1], means[u2], ds, u2);
					}
				}

				Point s = new Point(means[u1].getDimesion());
				s.add(means[u1]);
				for (int i = 0; i < ds.d.length; i++) {
					s.add(means[ds.d[i].index]);
					used[ds.d[i].index] = true;
				}
				s.divide(CONSTANT);
				System.out.println(s.print());
			}

		}

		System.out.println();
	}

	private void insertDistance(Point x, Point y, Ds ds, int index) {
		if (ds.size > 0) {
			double z = Point.distance(x, y);
			if (ds.size < ds.max) {
				ds.d[ds.size] = new D();
				ds.d[ds.size].index = index;
				ds.d[ds.size].dist = z;
				ds.size++;
			} else {
				Arrays.sort(ds.d);
				if (ds.d[ds.size - 1].dist > z) {
					ds.d[ds.size - 1].index = index;
					ds.d[ds.size - 1].dist = z;
				}
			}
		}
	}

}
