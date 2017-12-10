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

// 프로그램 동작 시 처음 나오는 메튜 패널
public class MenuPanel extends TempPanel {

	private PanelController pControll;// UpCall을 위해 PanelController 객체 생성
	private JButton btnStart, btnQuit;// JButton 2개 생성
	private MenuActionListener menuL;// Listener 객체 생성

	public MenuPanel(PanelController p) {

		this.pControll = p;
		setBackground(Color.darkGray);

		setLayout(null);

		menuL = new MenuActionListener();

		// 버튼 배치
		// start 버튼
		btnStart = new JButton("Start");
		btnStart.addActionListener(menuL);
		btnStart.setBounds(500, 850, 150, 80);
		btnStart.setFont(new Font("Consolas", Font.BOLD, 25));
		btnStart.setBackground(Color.LIGHT_GRAY);
		add(btnStart);
		// quit 버튼
		btnQuit = new JButton("Quit");
		btnQuit.addActionListener(menuL);
		btnQuit.setBounds(700, 850, 150, 80);
		btnQuit.setFont(new Font("Consolas", Font.BOLD, 25));
		btnQuit.setBackground(Color.LIGHT_GRAY);
		add(btnQuit);

		setVisible(true);

	}

	// 화면 위, 아래 텍스트를 그려줌
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension d = getSize();// 화면의 size를 가져옴
		g.drawImage(Content.MENUBG[0][0], 180, 180, d.width - 350, d.height - 300, null);// MenuBackground 이미지를 그림
		g.setFont(new Font("휴먼모음T", Font.BOLD, 100));// 텍스트 폰트 설정
		g.setColor(new Color(150, 0, 0)); // 텍스트 그림자 색 결정
		g.drawString("Grand Theft A+", 305, 125);// 타이틀 텍스트의 그림자 효과를 내기 위해 겹쳐서 표시
		g.setColor(Color.white); // 타이틀 텍스트의 색
		g.drawString("Grand Theft A+", 300, 120);// 타이틀 텍스트
	}

	// GameThread 로 이동하는 메소드
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
				// start버튼 을 누르면 GameThread로 이동
			} else if (obj == btnQuit) {
				System.exit(0);
				// quit 버튼을 누르면 프로그램 종료
			}
		}

	}

}
