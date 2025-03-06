import java.awt.*;
import java.awt.event.KeyListener;


public class RectangleModel extends PlaneModel implements Movable {


	private transient String direction;

	private KeyHandler keyHandler_;
	private int dx_, dy_;
	private Rectangle rectangleModel;
	private int dTheta_;

	public RectangleModel(int xPos, int yPos, int dx, int dy, KeyHandler keyhandler) {

		super(new int[]{xPos, yPos}, new int[]{dx, dy});
		this.rectangleModel = new Rectangle(dx, dy, xPos, yPos);
		this.keyHandler_ = keyhandler;

		this.dx_ = 0;
		this.dy_ = 0;


	}




	public RectangleModel(Rectangle rect , KeyHandler keyHandler) {

		super(new int[] {(int) rect.getX(), (int) rect.getY()}, new int[]{(int) rect.getWidth(), (int) rect.getHeight()});
		this.rectangleModel = rect;

		this.keyHandler_ = keyHandler;

		this.dx_ = 0;
		this.dy_ = 0;
		this.dTheta_ = 0;
	}

	public void draw(Graphics2D g) {
		if(this.rectangleModel.isEmpty()) return;
		g.setColor(Color.BLACK);
		g.rotate(this.dTheta_);
		g.draw(this.rectangleModel);
	}

	@Override
	public void update() {



		this.dx_ = this.dy_ = this.dTheta_ =  0;
		// KeyHandler update

		//  up, down, right, left 에 대한 부분 존재

		if(this.keyHandler_.up) this.dy_ = -1;
		if(this.keyHandler_.down) this.dy_ = 1;
		if(this.keyHandler_.right) this.dx_ = 1;
		if(this.keyHandler_.left) this.dx_ = -1;
		if(this.keyHandler_.rightTurn) this.dTheta_ = 1;
		if(this.keyHandler_.leftTurn) this.dTheta_ = -1;


		//System.out.println(this.keyHandler_.up + " " + this.keyHandler_.down + " " + this.keyHandler_.right + " " + this.keyHandler_.left + "");





		translateRectangle(this.dx_, this.dy_);
	}

	public void translateRectangle(int x, int y) {
		this.rectangleModel.translate(x, y);
	}

	public void translateRectangle(int x, int y, int theta) {
		this.rectangleModel.translate((int) (Math.cos(theta) * x - Math.sin(theta)*y ), (int) ((Math.cos(theta)*x) + (Math.sin(theta)*y)));
	}



	public double getPositionX() {
		return this.rectangleModel.getX();
	}

	public double getPositionY() {
		return this.rectangleModel.getY();
	}
}