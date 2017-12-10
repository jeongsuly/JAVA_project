
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

//�����带 ���۽��� ������ ������ �׷��ִ� Ŭ����
//Ű �Է��� �޾� ���� �÷��̿� ������� ��

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

	public GameThread(PanelController p) {// PanelController ��ü�� ����
		this.pControll = p;

		if (thread == null) {
			thread = new Thread(this);// Thread ����
			addKeyListener(new InputedKeyListener());// keyListener �߰�
			thread.start();// Thread ����
		}
		setFocusable(true);// GameThread ���� Ű �Է� ���� �� �ְ� ��Ŀ��
	}

	// run new thread
	public void run() {

		init();// thread ���� �� ȭ�� �ʱ�ȭ

		// game loop
		while (isRunning) {

			// PlayState�� ���� ���� update
			update();
			draw();
			drawToScreen();
			try {
				Thread.sleep(33);
				// ���� ����
				if (play.getPaused() == true) {
					isRunning = false;
					pauseOption();
				}
				// ���� ���� or ���� Ŭ����
				if (play.GameOver() == true || play.GameClear() == true) {
					// ���� �̺�Ʈ
					for (int i = 0; i < 4; i++) {
						Thread.sleep(100);
						this.event();
						Thread.sleep(50);
						this.drawToScreen();
					}
					Thread.sleep(300);
					Keys.init();// key�Է� �ʱ�ȭ
					isRunning = false;// ������ ����
					pControll.resultGamePanel();// resultPanel�� �̵�
				}

			} catch (Exception e) {
			}

		}
	}

	// ȭ�� �ʱ�ȭ
	public void init() {
		isRunning = true;// ������ ����
		image = new BufferedImage(MainFrame.WIDTH, MainFrame.HEIGHT, 1);
		g = (Graphics2D) image.getGraphics();
		play = new PlayState();
	}

	// menuPanel�� �ҷ����� ���� �޼ҵ�
	public void showMenu() {
		menu = new MenuPanel(pControll);
	}

	// update game
	private void update() {
		play.update();// Player ���� update
		Keys.update();// key �Է� ���� update
	}

	// draws game
	private void draw() {
		play.draw(g);// PlayState���� ��ġ�� ��� ������Ʈ�� �׷���
	}

	// �̺�Ʈ �߻�
	private void event() {
		g2 = this.getGraphics();// ���� ������ Graphics�� �޾� ��
		if (play.GameOver() == true) {
			g2.drawImage(Content.OVER[0][0], 180, 120, MainFrame.WIDTH * MainFrame.SCALE - 350,
					MainFrame.HEIGHT * MainFrame.SCALE - 250, null);
			// Game Over �̹��� draw
		} else {
			g2.drawImage(Content.CLEAR[0][0], 180, 120, MainFrame.WIDTH * MainFrame.SCALE - 350,
					MainFrame.HEIGHT * MainFrame.SCALE - 250, null);
			// Game Clear �̹��� draw
		}
	}

	// ȭ�鿡 �׸��� �׷���
	private void drawToScreen() {
		g2 = this.getGraphics();// ���� ������ Graphics�� �޾� ��
		g2.drawImage(image, 0, 0, MainFrame.WIDTH * MainFrame.SCALE, MainFrame.HEIGHT * MainFrame.SCALE, null);
		// MainFrame�� ũ�⸸ŭ �̹����� Ȯ���ؼ� ȭ�鿡 ǥ��
	}

	// JOption�� ���� ���� ���� ���� ����
	private void pauseOption() {
		int result;

		result = JOptionPane.showOptionDialog(null, "������ �Ͻǰǰ���?", "���� ��������", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, new Object[] { "��� ����", "�޴�", "�����" }, null);

		if (result == JOptionPane.YES_OPTION) {
			play.setPaused(false);
			isRunning = true;
			// ����ϱ�
		} else if (result == JOptionPane.NO_OPTION) {
			play.setPaused(false);
			pControll.showMenuPanel();
			// �޴� �гη� �̵�
		} else if (result == JOptionPane.CANCEL_OPTION) {
			play.setPaused(false);
			isRunning = true;
			init();
			// ������� ���� ȭ�� �ʱ�ȭ
		} else {
			play.setPaused(false);
			isRunning = true;
			// ��Ÿ �Է��� ���� ��� ������ ��� �����Ŵ
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
