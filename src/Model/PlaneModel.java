package Model;

import java.awt.*;
import java.util.ArrayList;

public class PlaneModel implements Movable {


	private double x_, y_, zIndex_;
	private double[] polygon;

	/**
	 *
	 * @param polygon bound of polygon
	 * @param position position of polygon
	 */
	public PlaneModel(double[] polygon, double[] position) {
		this.polygon = polygon;
		this.x_ = position[0];
		this.y_ = position[1];
		this.zIndex_ = position[2];
	}

	public PlaneModel(double[] polygon) {
		this.polygon = polygon;
		this.x_ = 0;
		this.y_ = 0;
		this.zIndex_ = -1;
	}





	@Override
	public void move(double[] position) {

		this.x_ += position[0];
		this.y_ += position[1];
		this.zIndex_ += position[2];
	}

	@Override
	public void setPosition(double[] position) {
		this.x_ = position[0];
		this.y_= position[1];
		this.zIndex_ = position[2];
	}

	@Override
	public double[] getPosition() {
		return new double[0];
	}
}
