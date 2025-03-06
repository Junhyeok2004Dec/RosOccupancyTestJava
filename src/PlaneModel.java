public class PlaneModel implements Movable {


	private int x_, y_, zIndex_;
	private int[] polygon;




	/**
	 *
	 * @param polygon bound of polygon
	 * @param position position of polygon
	 */
	public PlaneModel(int[] polygon, int[] position) {
		this.polygon = polygon;
		this.x_ = position[0];
		this.y_ = position[1];
		this.zIndex_ = -1; // 우리 프로그램에는 depth나 레이어 기능이 없습니다~~~ :D
	}

	public PlaneModel(int[] polygon) {
		this.polygon = polygon;
		this.x_ = 0;
		this.y_ = 0;
		this.zIndex_ = -1;
	}





	@Override
	public void move(int[] position) {

		this.x_ += position[0];
		this.y_ += position[1];
		this.zIndex_ += position[2];
	}

	@Override
	public void setPosition(int[] position) {
		this.x_ = position[0];
		this.y_= position[1];
		this.zIndex_ = position[2];
	}

	@Override
	public int[] getScale() {
		return new int[]{this.x_, this.y_};
	}

	@Override
	public void update() {

	}
}
