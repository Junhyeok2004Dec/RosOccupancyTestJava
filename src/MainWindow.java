import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Paths;

public class MainWindow {



	JPanel mainPanel;
	public Container container;
	JFrame jframe;

	public static void main(String[] args)
	{
		new MainWindow();
	}


	public MainWindow() {



		jframe = new JFrame();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jframe.setResizable(false);

		jframe.setTitle("MainCraft");


		Simulator simulator = new Simulator();
		jframe.add(simulator);

		jframe.pack();

		jframe.setLocationRelativeTo(null);

		jframe.setVisible(true);
		simulator.startSimulator();

	}
}
