
package Main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Manager.Content;
import Manager.Keys;
import Panel.MenuPanel;

//쓰레드를 동작시켜 게임의 진행을 그려주는 클래스
//키 입력을 받아 게임 플레이에 적용시켜 줌

public class GameThread extends JPanel implements Runnable {

	// public PlayState play;
	public MenuPanel menu;
	public PlayState play;
	private PanelController pControll;

	// game loop thread
	private Thread thread;
	private boolean isRunning;

	// drawing
	private BufferedImage image;
	private Graphics2D g;
	private Graphics g2;

	public GameThread(PanelController p) {// PanelController 객체를 받음
		this.pControll = p;

		if (thread == null) {
			thread = new Thread(this);// Thread 선언
			addKeyListener(new InputedKeyListener());// keyListener 추가
			thread.start();// Thread 시작
		}
		setFocusable(true);// GameThread 에서 키 입력 받을 수 있게 포커싱
	}

	// run new thread
	public void run() {

		init();// thread 동작 시 화면 초기화

		// game loop
		while (isRunning) {

			// PlayState에 따른 상태 update
			update();
			draw();
			drawToScreen();
			try {
				Thread.sleep(33);
				// 일지 정지
				if (play.getPaused() == true) {
					isRunning = false;
					pauseOption();
				}
				// 게임 오버 or 게임 클리어
				if (play.GameOver() == true || play.GameClear() == true) {
					// 종료 이벤트
					for (int i = 0; i < 4; i++) {
						Thread.sleep(100);
						this.event();
						Thread.sleep(50);
						this.drawToScreen();
					}
					Thread.sleep(300);
					Keys.init();// key입력 초기화
					isRunning = false;// 쓰레드 종료
					pControll.resultGamePanel();// resultPanel로 이동
				}

			} catch (Exception e) {
			}

		}
	}

	// 화면 초기화
	public void init() {
		isRunning = true;// 쓰레드 동작
		image = new BufferedImage(MainFrame.WIDTH, MainFrame.HEIGHT, 1);
		g = (Graphics2D) image.getGraphics();
		play = new PlayState();
	}

	// menuPanel을 불러오기 위한 메소드
	public void showMenu() {
		menu = new MenuPanel(pControll);
	}

	// update game
	private void update() {
		play.update();// Player 상태 update
		Keys.update();// key 입력 상태 update
	}

	// draws game
	private void draw() {
		play.draw(g);// PlayState에서 배치된 모든 오브젝트를 그려줌
	}

	// 이벤트 발생
	private void event() {
		g2 = this.getGraphics();// 현재 게임의 Graphics를 받아 옴
		if (play.GameOver() == true) {
			g2.drawImage(Content.OVER[0][0], 180, 120, MainFrame.WIDTH * MainFrame.SCALE - 350,
					MainFrame.HEIGHT * MainFrame.SCALE - 250, null);
			// Game Over 이미지 draw
		} else {
			g2.drawImage(Content.CLEAR[0][0], 180, 120, MainFrame.WIDTH * MainFrame.SCALE - 350,
					MainFrame.HEIGHT * MainFrame.SCALE - 250, null);
			// Game Clear 이미지 draw
		}
	}

	// 화면에 그림을 그려줌
	private void drawToScreen() {
		g2 = this.getGraphics();// 현재 게임의 Graphics를 받아 옴
		g2.drawImage(image, 0, 0, MainFrame.WIDTH * MainFrame.SCALE, MainFrame.HEIGHT * MainFrame.SCALE, null);
		// MainFrame의 크기만큼 이미지를 확대해서 화면에 표시
	}

	// JOption을 통한 게임 일지 정지 설정
	private void pauseOption() {
		int result;

		result = JOptionPane.showOptionDialog(null, "무엇을 하실건가요?", "게임 일지정지", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, new Object[] { "계속 진행", "메뉴", "재시작" }, null);

		if (result == JOptionPane.YES_OPTION) {
			play.setPaused(false);
			isRunning = true;
			// 계속하기
		} else if (result == JOptionPane.NO_OPTION) {
			play.setPaused(false);
			pControll.showMenuPanel();
			// 메뉴 패널로 이동
		} else if (result == JOptionPane.CANCEL_OPTION) {
			play.setPaused(false);
			isRunning = true;
			init();
			// 재시작을 위해 화면 초기화
		} else {
			play.setPaused(false);
			isRunning = true;
			// 기타 입력이 들어올 경우 게임을 계속 진행시킴
		}

	}

	// KeyListener
	private class InputedKeyListener implements KeyListener {

		// key event handling
		public void keyTyped(KeyEvent key) {
		}

		public void keyPressed(KeyEvent key) {
			Keys.keySet(key.getKeyCode(), true);
		}

		public void keyReleased(KeyEvent key) {
			Keys.keySet(key.getKeyCode(), false);
		}
	}

}
