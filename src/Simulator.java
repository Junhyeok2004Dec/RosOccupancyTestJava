import Model.RectangleModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Simulator extends JPanel implements Runnable{


	int fps = 240;

	Thread simulatorThread;

	public static final Color GRID_COLOR = Color.BLACK;
	public static final Color BACK_GROUND_COLOR = Color.WHITE;
	public static final Color FILL_COLOR = Color.DARK_GRAY;
	public static final Color MISS_COLOR = Color.LIGHT_GRAY;


	public static final int ROOT_TILE_SCALE = 16;
	public static final int SIZE_FACTOR = 3;
	public static int TILE_SCALE = ROOT_TILE_SCALE * SIZE_FACTOR;



	public static final int maxScreenColumn =  16;
	public static final int maxScreenRow = 12;


	public static final int screenMultiX = 1;
	public static final int screenMultiY = 1;


	public static final Double pHIT = 0.55; // 0.55 , This value must to be larger than 0.5
	public static final Double pMISS = 0.49; // 0.49 , This value must to be small than 0.5




	public final int maxWorldCol = 60;
	public final int maxWorldRow = 60;


	double drawInterval = 1E+9/ fps; // 0.004166 sec
	double dt = 0;


	long lastTime = System.nanoTime();
	long currentTime;

	long timer = 0;
	long drawCount = 0;


	private ArrayList<Rectangle> rectangles;


	public static final int screenWidth = screenMultiX* TILE_SCALE * maxScreenColumn;
	public static final int screenHeight = screenMultiY * TILE_SCALE * maxScreenRow;

	private KeyHandler keyHandler = new KeyHandler(this);
	private MouseHandler mouseHandler = new MouseHandler(this);

	private Grid grid = new Grid(this, keyHandler, mouseHandler);
	private RectangleModel rectangleModel;

	public Simulator() {

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(BACK_GROUND_COLOR);

		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);

	}


	public void startSimulator() {
		simulatorThread = new Thread(this);
		simulatorThread.start();



		WorldGen worldGen = new WorldGen();
		worldGen.Data();

		this.addMouseListener(mouseHandler);

		this.rectangles = new ArrayList<>();
		rectangles.add(new Rectangle(0, 0, 10, 10));
		this.rectangleModel = new RectangleModel(rectangles.get(0));

	}


	@Override
	public void run() {


		drawInterval = 1E+9 / fps;
		dt = 0;
		lastTime = System.nanoTime();



		while (simulatorThread != null) {



			currentTime = System.nanoTime();

			dt += (currentTime - lastTime) / drawInterval;


			timer += (currentTime - lastTime);

			lastTime = currentTime;

			//Redraw
			if (dt >= 1) {
				update();
				repaint();
				dt--;
				drawCount++;
			}

			if (timer >= 1E+9) {
				drawCount = 0;
				timer = 0;
			}

		}
	}


	public void update() {


		this.grid.update();

	}




	public void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D g2d = (Graphics2D) g;


			this.grid.gridDraw(g2d);
			this.rectangleModel.draw(g2d);

			g2d.dispose();
		}

		public int getWidth() {
			return this.screenWidth;
		}

		public int getHeight() {
			return this.screenHeight;
		}

	public int getTileSize() {
		return this.TILE_SCALE;
	}

}
