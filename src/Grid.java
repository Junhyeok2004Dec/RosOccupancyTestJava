import java.awt.*;
import java.util.ArrayList;

public class Grid {

	private ArrayList<ArrayList<Double>> probability; // referenced map, 실제 맵이라 생각해도 좋을 듯
	private ArrayList<ArrayList<Double>> simulatorProbability;
	int rows, cols;

	Simulator simulator;
	WorldGen worldGen;
	private RayCasting rayCasting;

	KeyHandler kh;
	MouseHandler mh;

	public Grid(Simulator simulator, KeyHandler kh, MouseHandler mh) {
		this.worldGen = new WorldGen();
		worldGen.Data();
		probability = worldGen.getArrayData();
		this.simulator = simulator;
		rayCasting = new RayCasting(this);

		this.kh = kh;
		this.mh = mh;
	}

	// 확률 계산을 위한 Odds 계산
	public static double odds(Double x) {
		if(x == 1) {
			return 1;
		}
		return (x / (1 - x));  // x / (1 - x)
	}

	// 확률 계산을 위한 역 Odds 계산
	public static double oddInverse(Double x) {
		if(x == -1) {
			return -1;
		}
		return (x / (1 + x));  // x / (1 + x)

	}

	// 히트된 그리드 업데이트
	public void hitGrid(int row, int column) {
		System.out.println("Updated grid at " + row + " " + column + "to " + this.probability.get(row).get(column));

		updateGrid(row, column, Simulator.pHIT);
	}

	// 미스된 그리드 업데이트
	public void missGrid(int row, int column) {
		updateGrid(row, column, Simulator.pMISS);
	}



	// 그리드 값을 업데이트하는 공통 메서드


	public void updateGrid(int row, int column, double probabilityFactor) {
		double currentProbability = this.probability.get(row).get(column);
		double newProbability = oddInverse(odds(currentProbability) * odds(probabilityFactor));

		System.out.println(newProbability);
		newProbability = Math.max(0, Math.min(1, newProbability));

		this.probability.get(row).set(column, newProbability);
	}



	// 그리드의 확률을 설정
	public void setProbability(int row, int column) {
		this.probability.get(row).set(column, this.worldGen.getData(row, column));
	}



	public void update() {

		rayCasting.update();
	}


	// 그리드 그리기
	public void gridDraw(Graphics2D g2d) {
		rows = this.probability.size();
		cols = this.probability.get(0).size();

		g2d.setFont(new Font("Arial", Font.ITALIC, 13));

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {

				double probabilityValue = probability.get(i).get(j);

				// 색상 결정
				if (probabilityValue > 0.5) {
					g2d.setColor(Simulator.FILL_COLOR);
				} else if (probabilityValue == 0.5) {
					g2d.setColor(Simulator.BACK_GROUND_COLOR);
				} else {
					g2d.setColor(Simulator.MISS_COLOR);
				}





				// 타일 그리기
				int tileX = j * Simulator.TILE_SCALE;
				int tileY = i * Simulator.TILE_SCALE;
				g2d.fillRect(tileX, tileY, Simulator.TILE_SCALE, Simulator.TILE_SCALE);

				// 그리드 선 그리기
				g2d.setColor(Color.GRAY);
				g2d.drawRect(tileX, tileY, Simulator.TILE_SCALE, Simulator.TILE_SCALE);

				// 텍스트 그리기
				g2d.setColor(Color.RED);
				String text = String.format("%.2f", probabilityValue);
				FontMetrics fm = g2d.getFontMetrics();
				int textX = tileX + (Simulator.TILE_SCALE - fm.stringWidth(text)) / 2;
				int textY = tileY + (Simulator.TILE_SCALE + fm.getAscent()) / 2;
				g2d.drawString(text, textX, textY);
			}
		}

		rayCasting.draw(g2d);


	}

	public int getRows() {
		return this.probability.size();
	}

	public int getCols() {
		return this.probability.get(0).size();
	}

	public ArrayList<ArrayList<Double>> getProbability() {
		return this.probability;
	}
}
