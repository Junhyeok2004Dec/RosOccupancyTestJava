package Model;

import java.awt.*;
import java.util.concurrent.RecursiveAction;

public class RectangleModel extends PlaneModel implements Movable {



	private int dx_, dy_;
	private Rectangle rectangleModel;

	public RectangleModel(int xPos, int yPos, int dx, int dy) {

		super(new int[]{xPos, yPos}, new int[]{dx, dy});
		this.rectangleModel = new Rectangle(dx, dy, xPos, yPos);


		this.dx_ = 0;
		this.dy_ = 0;


	}

	public RectangleModel(Rectangle rect) {

		super(new int[] {(int) rect.getX(), (int) rect.getY()}, new int[]{(int) rect.getWidth(), (int) rect.getHeight()});
		this.rectangleModel = rect;

		this.dx_ = 0;
		this.dy_ = 0;
	}

	public void draw(Graphics2D g) {
		if(this.rectangleModel.isEmpty()) return;
		g.setColor(Color.BLACK);
		g.draw(this.rectangleModel);
	}

	@Override
	public void update() {

		translateRectangle(this.dx_, this.dy_);
	}

	public void translateRectangle(int x, int y) {
		this.rectangleModel.translate(x, y);
	}
}