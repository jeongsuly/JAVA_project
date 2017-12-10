package Main;

import java.awt.Dimension;

import javax.swing.JFrame;

// 패널들을 추가하기 위한 Main Frame 클래스
public class MainFrame extends JFrame {

	// 화면 size, 및 scale
	public static final int WIDTH = 352;
	public static final int HEIGHT = 248;
	public static final int SCALE = 4;

	private PanelController p;// PanelController를 추가해 주기 위해 객체 생성

	public MainFrame() {
		this.setTitle("Main Frame");
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();

		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);

		p = new PanelController(this);

	}
}
