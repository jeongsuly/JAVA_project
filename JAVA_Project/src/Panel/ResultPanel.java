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

// ������ ���â�� �����ִ� �г�
// ������ ���� ����� ���� 2���� �ٸ� �̹����� �ؽ�Ʈ ǥ��
public class ResultPanel extends TempPanel {

	private PanelController pControll;// UpCall�� ���� PanelController ��ü ����
	private JButton btnRestart, btnGoToMenu, btnQuit;
	private ResultActionListener resultL;
	private boolean bClear;// ���� Ŭ���� ���� �Ǵ��� ���� boolean ������

	public ResultPanel(PanelController p, boolean b) { // Upcall PanelController & gamePanel.play.GameClear

		this.pControll = p;
		bClear = b;

		setBackground(Color.darkGray);

		setLayout(null);

		resultL = new ResultActionListener();

		// Restart ��ư
		btnRestart = new JButton("Restart");
		btnRestart.addActionListener(resultL);
		btnRestart.setBounds(430, 850, 150, 100);
		btnRestart.setFont(new Font("Consolas", Font.BOLD, 25));
		btnRestart.setBackground(Color.LIGHT_GRAY);
		add(btnRestart);

		// Menu ��ư
		btnGoToMenu = new JButton("Menu");
		btnGoToMenu.addActionListener(resultL);
		btnGoToMenu.setBounds(630, 850, 150, 100);
		btnGoToMenu.setFont(new Font("Consolas", Font.BOLD, 25));
		btnGoToMenu.setBackground(Color.LIGHT_GRAY);
		add(btnGoToMenu);

		// quit ��ư
		btnQuit = new JButton("Quit");
		btnQuit.addActionListener(resultL);
		btnQuit.setBounds(830, 850, 150, 100);
		btnQuit.setFont(new Font("Consolas", Font.BOLD, 25));
		btnQuit.setBackground(Color.LIGHT_GRAY);
		add(btnQuit);

		setVisible(true);

	}

	// resultPanel�� ǥ�õ� �̹����� �ؽ�Ʈ�� �׷���
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension d = getSize();

		// Game Over
		if (bClear == false) {
			g.drawImage(Content.OVER[0][0], 280, 160, d.width - 550, d.height - 400, null);
			overMessage(g);// Game Over �޽���

			// Game Clear
		} else {
			g.drawImage(Content.CLEAR[0][0], 280, 160, d.width - 550, d.height - 400, null);
			clearMessage(g);// Game Clear �޽���
		}
	}

	// ActionListener
	private class ResultActionListener implements ActionListener {
		// Event Handling
		public void actionPerformed(ActionEvent e) {

			Object obj = e.getSource();

			if (obj == btnRestart) {
				gameStart();
				// Restart ��ư Ŭ�� �� ���� �����

			} else if (obj == btnGoToMenu) {
				goToMenu();
				// Menu ��ư Ŭ�� �� �޴� ȭ������ �̵�

			} else if (obj == btnQuit) {
				System.exit(0);
				// quit ��ư Ŭ�� �� ���α׷� ��=����
			}
		}

	}

	// ������ ������ϰ� ���ִ� �޼ҵ�
	public void gameStart() {
		pControll.replayGamePanel();

	}

	// �޴��� ���ư��� �޼ҵ�
	public void goToMenu() {
		pControll.showMenuPanel(this);
	}

	// Game Over �� �׷����� �ؽ�Ʈ
	public void overMessage(Graphics g) {
		g.setFont(new Font("�޸տ�����", 0, 40));
		g.setColor(Color.white);
		g.drawString("�������� ����� ���� ���� ģ���鿡�� ������", 280, 80);
		g.drawString("���� ���� ������ �ŵξ��׿� ", 450, 140);
		g.drawString("������ ģ������ �ΰ��̰�", 450, 770);
		g.drawString("����� ����� ���θ� �ϱ� �Ⱦ����� �ƴұ��?", 270, 830);
	}

	// Game Clear �� �׷����� �ؽ�Ʈ
	public void clearMessage(Graphics g) {
		g.setFont(new Font("�޸տ�����", 0, 40));
		g.setColor(Color.white);
		g.drawString("���ϵ帳�ϴ�!!", 550, 80);
		g.drawString("����� ���迡�� ���� ������ �ŵξ����ϴ�", 310, 140);
		g.drawString("������ ������ �߿�������", 470, 780);
		g.drawString("�������� �ֺ� ������� ì�ܺ��°� �����?", 285, 830);
	}

}
