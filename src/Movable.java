public interface Movable {
	void move(int[] position); // 이동 메서드
	void setPosition(int[] position); // 위치 설정
	int[] getScale();
	void update();
}
