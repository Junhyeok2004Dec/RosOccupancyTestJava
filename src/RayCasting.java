import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class RayCasting extends JFrame {


	private final ArrayList<Ray> rays_ = new ArrayList<>();
	private float centerX, centerY;
	private final int RAY_LENGTH = 30; // Ray의 길이
	private final int TILE_SIZE = Simulator.TILE_SCALE;   // 타일 크기
	private final int ANGLE_INCREMENT = 12; // 각도 증가 (12도)

	private int gridIndex[];

	private Grid gridInstance;




	public RayCasting(Grid grid) {
		this.gridInstance = grid;

	}


	public int[] getGridIndexFromMouse(int mouseX, int mouseY) {
		int row = ( mouseY) / Simulator.TILE_SCALE; // 마우스 Y 좌표를 타일 크기로 나누어 행 번호 계산
		int col = ( mouseX) / Simulator.TILE_SCALE; // 마우스 X 좌표를 타일 크기로 나누어 열 번호 계산


		return new int[]{row, col}; // [행, 열] 순으로 반환
	}

	public void update() {

		rays_.clear(); // 이전 ray 지우기
		centerX = MouseHandler.x; // 클릭한 x 위치
		centerY = MouseHandler.y; // 클릭한 y 위치
		boolean wasClicked = gridInstance.mh.isClicked(); // 클릭 상태 저장

		// 12도 간격으로 ray 발사
		for (int angle = 0; angle < 360; angle += ANGLE_INCREMENT) {
			// 각도에 맞는 ray를 추가
			rays_.add(new Ray((int) centerX, (int) centerY, angle, this, wasClicked));

		}

		this.gridIndex = getGridIndexFromMouse((int) centerX,(int) centerY);

		if (wasClicked) {
			gridInstance.mh.clearMouseClicked(); // **모든 Ray가 검사된 후 한 번만 클릭 상태 초기화**
		}


	}



	public void draw(Graphics2D g2d) {




		g2d.setColor(Color.GREEN);

		// 스레드 락 걸고 ray 추가하고 수정해야 하는데, 그냥 조금 쓰고 안 쓸 코드이기 때문에, 나중에 수정함.
		// 결론 : 수정안함. 그냥 참고 쓰세요
		for(Ray ray : rays_) {
			ray.draw(g2d);

		}

		g2d.fillOval((int) centerX - 5, (int) centerY - 5, 10, 10); // 클릭한 지점 중앙 표시
	}





	private class Ray {
		private int startX, startY;
		private final double angle;
		private final boolean wasClicked; // 클릭 상태 저장
		private RayCasting rayCasting;

		public Ray(int startX, int startY, int angle, RayCasting rayCasting, boolean wasClicked) {
			this.startX = startX;
			this.startY = startY;
			this.rayCasting  = rayCasting;
			this.angle = Math.toRadians(angle); // 각도를 라디안으로 변환
			this.wasClicked = wasClicked; // 클릭 상태 저장
		}

		public void draw(Graphics2D g) {
			for (int i = 0; i < RAY_LENGTH; i++) {


				double newX = startX + i * TILE_SIZE * Math.cos(angle);
				double newY = startY + i * TILE_SIZE * Math.sin(angle);



				int col = (int) Math.floor(newX / TILE_SIZE);
				int row = (int) Math.floor(newY / TILE_SIZE);



				if (row < 0 || row >= this.rayCasting.gridInstance.getRows() ||
						col < 0 || col >= rayCasting.gridInstance.getCols())
				{
					break;
				}



				if (rayCasting.gridInstance.getReferencedMapProbability().get(row).get(col) > 0.5) {
					g.setColor(Color.RED);
					g.draw(new Line2D.Double(startX, startY, newX, newY));
					g.fillOval((int) newX - 5, (int) newY - 5, 10, 10); // 클릭한 지점 중앙 표시


					if (wasClicked) { // 클릭한 경우에만 적용
						rayCasting.gridInstance.hitGrid(row, col);
					}
					return;
				} else {
					g.setColor(Color.BLUE);
					g.draw(new Line2D.Double(startX, startY, newX, newY));

					if (wasClicked) { // 클릭한 경우에만 적용
						rayCasting.gridInstance.missGrid(row, col);
					}

				}
			}
		}





		@Override
		public String toString() {
			return "Ray{" +
					"startX=" + startX +
					", startY=" + startY +
					", angle=" + angle+
					'}';
		}


	}}