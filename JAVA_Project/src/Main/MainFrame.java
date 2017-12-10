package Main;

import java.awt.Dimension;

import javax.swing.JFrame;

// �гε��� �߰��ϱ� ���� Main Frame Ŭ����
public class MainFrame extends JFrame {

	// ȭ�� size, �� scale
	public static final int WIDTH = 352;
	public static final int HEIGHT = 248;
	public static final int SCALE = 4;

	private PanelController p;// PanelController�� �߰��� �ֱ� ���� ��ü ����

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
