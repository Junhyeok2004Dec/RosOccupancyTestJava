import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class MouseHandler implements MouseListener {

	public static boolean LMB, RMB;

	//LMB, RMB, ...
	private Simulator gp_;

	public static float x, y;

	public static boolean isPressed, isClicked;




	public MouseHandler(Simulator gp_) {
		this.gp_ = gp_;

	}
	/**
	 * Invoked when the mouse button has been clicked (pressed
	 * and released) on a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mouseClicked(MouseEvent e) {


	}

	/**
	 * Invoked when a mouse button has been pressed on a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mousePressed(MouseEvent e) {

		x = e.getX();
		y = e.getY();

		int code = e.getButton();

		switch(code) {
			case MouseEvent.BUTTON1:
				LMB = true;
				isClicked = true;
		}
	}

	/**
	 * Invoked when a mouse button has been released on a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mouseReleased(MouseEvent e) {


		int code = e.getButton();

		switch(code) {
			case MouseEvent.BUTTON1:
				LMB = false;
				isClicked = true;
				isPressed = false;


		}
	}

	/**
	 * Invoked when the mouse enters a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mouseEntered(MouseEvent e) {

	}

	/**
	 * Invoked when the mouse exits a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mouseExited(MouseEvent e) {

	}

	public void clearMouseClicked() {
		isClicked = false;
	}

	public boolean isClicked() {
		return isClicked;
	}

	public boolean isPressed() {
		return isPressed;
	}


}
