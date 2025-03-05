package Model;

public interface Movable {
	void move(double[] position); // 이동 메서드
	void setPosition(double[] position); // 위치 설정
	double[] getPosition();
}
