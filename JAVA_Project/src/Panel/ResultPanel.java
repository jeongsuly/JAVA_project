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

// 게임의 결과창을 보여주는 패널
// 게임의 진행 결과에 따라 2개의 다른 이미지와 텍스트 표시
public class ResultPanel extends TempPanel {

	private PanelController pControll;// UpCall을 위한 PanelController 객체 생성
	private JButton btnRestart, btnGoToMenu, btnQuit;
	private ResultActionListener resultL;
	private boolean bClear;// 게임 클리어 여부 판단을 위한 boolean 데이터

	public ResultPanel(PanelController p, boolean b) { // Upcall PanelController & gamePanel.play.GameClear

		this.pControll = p;
		bClear = b;

		setBackground(Color.darkGray);

		setLayout(null);

		resultL = new ResultActionListener();

		// Restart 버튼
		btnRestart = new JButton("Restart");
		btnRestart.addActionListener(resultL);
		btnRestart.setBounds(430, 850, 150, 100);
		btnRestart.setFont(new Font("Consolas", Font.BOLD, 25));
		btnRestart.setBackground(Color.LIGHT_GRAY);
		add(btnRestart);

		// Menu 버튼
		btnGoToMenu = new JButton("Menu");
		btnGoToMenu.addActionListener(resultL);
		btnGoToMenu.setBounds(630, 850, 150, 100);
		btnGoToMenu.setFont(new Font("Consolas", Font.BOLD, 25));
		btnGoToMenu.setBackground(Color.LIGHT_GRAY);
		add(btnGoToMenu);

		// quit 버튼
		btnQuit = new JButton("Quit");
		btnQuit.addActionListener(resultL);
		btnQuit.setBounds(830, 850, 150, 100);
		btnQuit.setFont(new Font("Consolas", Font.BOLD, 25));
		btnQuit.setBackground(Color.LIGHT_GRAY);
		add(btnQuit);

		setVisible(true);

	}

	// resultPanel에 표시될 이미지와 텍스트를 그려줌
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension d = getSize();

		// Game Over
		if (bClear == false) {
			g.drawImage(Content.OVER[0][0], 280, 160, d.width - 550, d.height - 400, null);
			overMessage(g);// Game Over 메시지

			// Game Clear
		} else {
			g.drawImage(Content.CLEAR[0][0], 280, 160, d.width - 550, d.height - 400, null);
			clearMessage(g);// Game Clear 메시지
		}
	}

	// ActionListener
	private class ResultActionListener implements ActionListener {
		// Event Handling
		public void actionPerformed(ActionEvent e) {

			Object obj = e.getSource();

			if (obj == btnRestart) {
				gameStart();
				// Restart 버튼 클릭 시 게임 재시작

			} else if (obj == btnGoToMenu) {
				goToMenu();
				// Menu 버튼 클릭 시 메뉴 화면으로 이동

			} else if (obj == btnQuit) {
				System.exit(0);
				// quit 버튼 클릭 시 프로그램 졸=ㅇ료
			}
		}

	}

	// 게임을 재시작하게 해주는 메소드
	public void gameStart() {
		pControll.replayGamePanel();

	}

	// 메뉴로 돌아가는 메소드
	public void goToMenu() {
		pControll.showMenuPanel(this);
	}

	// Game Over 시 그려지는 텍스트
	public void overMessage(Graphics g) {
		g.setFont(new Font("휴먼엑스포", 0, 40));
		g.setColor(Color.white);
		g.drawString("불행히도 당신은 시험 전날 친구들에게 끌려가", 280, 80);
		g.drawString("좋지 못한 성적을 거두었네요 ", 450, 140);
		g.drawString("하지만 친구들은 핑계이고", 450, 770);
		g.drawString("사실은 당신이 공부를 하기 싫었던게 아닐까요?", 270, 830);
	}

	// Game Clear 시 그려지는 텍스트
	public void clearMessage(Graphics g) {
		g.setFont(new Font("휴먼엑스포", 0, 40));
		g.setColor(Color.white);
		g.drawString("축하드립니다!!", 550, 80);
		g.drawString("당신은 시험에서 좋은 성적을 거두었습니다", 310, 140);
		g.drawString("하지만 성적도 중요하지만", 470, 780);
		g.drawString("가끔씩은 주변 사람들을 챙겨보는건 어떤가요?", 285, 830);
	}

}
