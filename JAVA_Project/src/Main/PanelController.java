package Main;

import java.awt.Container;

import Panel.MenuPanel;
import Panel.ResultPanel;
import Panel.TempPanel;

//���ӿ� ���Ǵ� �гε��� ���� ��ȯ�ϴ� Ŭ����
public class PanelController {

	private MainFrame mainFrame;// UpCall�� ���� MainFrame ��ü�� ����
	private Container contentPane;// MainFrameŬ������ contentPane�ϱ� ���� ������ ��ü
	private GameThread gamePanel;// ������ �÷��̵Ǵ� �г��� ��ü ����
	private TempPanel currentPanel;// �޴�, ����� �����ִ� �г��� ��ü ����

	public PanelController(MainFrame frame) {
		this.mainFrame = frame;
		currentPanel = new MenuPanel(this); // ���� �г��� ManuPanel�� ������ ���α׷� ���� �� MenuPanel�� ���� ���� ��
		contentPane = mainFrame.getContentPane();
		contentPane.add(currentPanel);// ���� �г��� MainFrame�� �߰�
	}

	// ����ȭ������ �Ѿ�� �޼ҵ�
	public void showGamePanel(TempPanel currentPanel) {
		this.currentPanel = currentPanel;
		contentPane.remove(currentPanel);// ���� MainFrame�� �߰��� �г� ����
		gamePanel = new GameThread(this);// GameTread ����
		contentPane.add(gamePanel);// MainFrame�� �߰�
		show();// ȭ�鿡 ǥ��
	}

	// resultPanel�� �Ѿ�� �޼ҵ�
	public void resultGamePanel() {
		gamePanel.play.GameClear();// ���� ��� ����
		currentPanel = new ResultPanel(this, gamePanel.play.GameClear());// ���� Ŭ���� or ���� ���� �Ǻ����� boolean �����͸� �Ѱ� ��
		contentPane.remove(gamePanel);// ���� MainFrame�� �߰��� gameThread ����
		contentPane.add(currentPanel);// resultPanel�� add
		show();
	}

	// menuPanel�� �Ѿ�� �޼ҵ�(), gameThread���� ���
	public void showMenuPanel() {
		contentPane.remove(gamePanel);// ���� MainFrame�� �߰��� gameThread ����
		currentPanel = new MenuPanel(this);// MenuPanel ����
		contentPane.add(currentPanel);// menuPanel�� add
		show();
	}

	// menuPanel�� �Ѿ�� �޼ҵ�(p), resultPanel���� ���
	public void showMenuPanel(TempPanel currentPanel) {
		contentPane.remove(currentPanel);// ���� add �� �г� ����
		currentPanel = new MenuPanel(this);// MenuPanel ����
		contentPane.add(currentPanel);// add MenuPanel
		show();
	}

	// �ٽ��ϱ⸦ ���� �� ȭ���� �籸���ϱ� ���� �޼ҵ�
	public void replayGamePanel() {
		contentPane.remove(currentPanel); // ���� add�� �г� ����
		gamePanel = new GameThread(this);// ���ο� GameThread ��ü ����
		contentPane.add(gamePanel);// add GameThread
		show();
	}

	public void show() {
		mainFrame.setVisible(false); // ������������ ������ �ʰ��� ��
		mainFrame.setVisible(true); // �ٽ� ���̰� ��
	}

}
