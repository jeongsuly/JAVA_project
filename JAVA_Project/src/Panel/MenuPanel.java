package Panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Main.PanelController;
import Manager.Content;

// ���α׷� ���� �� ó�� ������ ��Ʃ �г�
public class MenuPanel extends TempPanel {

	private PanelController pControll;// UpCall�� ���� PanelController ��ü ����
	private JButton btnStart, btnQuit;// JButton 2�� ����
	private MenuActionListener menuL;// Listener ��ü ����

	public MenuPanel(PanelController p) {

		this.pControll = p;
		setBackground(Color.darkGray);

		setLayout(null);

		menuL = new MenuActionListener();

		// ��ư ��ġ
		// start ��ư
		btnStart = new JButton("Start");
		btnStart.addActionListener(menuL);
		btnStart.setBounds(500, 850, 150, 80);
		btnStart.setFont(new Font("Consolas", Font.BOLD, 25));
		btnStart.setBackground(Color.LIGHT_GRAY);
		add(btnStart);
		// quit ��ư
		btnQuit = new JButton("Quit");
		btnQuit.addActionListener(menuL);
		btnQuit.setBounds(700, 850, 150, 80);
		btnQuit.setFont(new Font("Consolas", Font.BOLD, 25));
		btnQuit.setBackground(Color.LIGHT_GRAY);
		add(btnQuit);

		setVisible(true);

	}

	// ȭ�� ��, �Ʒ� �ؽ�Ʈ�� �׷���
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension d = getSize();// ȭ���� size�� ������
		g.drawImage(Content.MENUBG[0][0], 180, 180, d.width - 350, d.height - 300, null);// MenuBackground �̹����� �׸�
		g.setFont(new Font("�޸ո���T", Font.BOLD, 100));// �ؽ�Ʈ ��Ʈ ����
		g.setColor(new Color(150, 0, 0)); // �ؽ�Ʈ �׸��� �� ����
		g.drawString("Grand Theft A+", 305, 125);// Ÿ��Ʋ �ؽ�Ʈ�� �׸��� ȿ���� ���� ���� ���ļ� ǥ��
		g.setColor(Color.white); // Ÿ��Ʋ �ؽ�Ʈ�� ��
		g.drawString("Grand Theft A+", 300, 120);// Ÿ��Ʋ �ؽ�Ʈ
	}

	// GameThread �� �̵��ϴ� �޼ҵ�
	public void gameStart() {
		pControll.showGamePanel(this);
	}

	// ActionListener
	private class MenuActionListener implements ActionListener {
		// Event Handling
		public void actionPerformed(ActionEvent e) {

			Object obj = e.getSource();

			if (obj == btnStart) {
				gameStart();
				// start��ư �� ������ GameThread�� �̵�
			} else if (obj == btnQuit) {
				System.exit(0);
				// quit ��ư�� ������ ���α׷� ����
			}
		}

	}

}
