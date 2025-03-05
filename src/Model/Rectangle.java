package Model;

class Rectangle extends PlaneModel implements Movable {

	private double dx, dy, dz;


	public Rectangle(double[] points, double[] position) {
		super(points, position);
	}

}