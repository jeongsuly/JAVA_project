package Main;

import java.awt.Container;

import Panel.MenuPanel;
import Panel.ResultPanel;
import Panel.TempPanel;

//게임에 사용되는 패널들을 서로 전환하는 클래스
public class PanelController {

	private MainFrame mainFrame;// UpCall을 위해 MainFrame 객체를 생성
	private Container contentPane;// MainFrame클래스에 contentPane하기 위해 생성한 객체
	private GameThread gamePanel;// 게임이 플레이되는 패널의 객체 생성
	private TempPanel currentPanel;// 메뉴, 결과를 보여주는 패널의 객체 생성

	public PanelController(MainFrame frame) {
		this.mainFrame = frame;
		currentPanel = new MenuPanel(this); // 현재 패널을 ManuPanel로 설정해 프로그램 실행 시 MenuPanel을 먼저 보여 줌
		contentPane = mainFrame.getContentPane();
		contentPane.add(currentPanel);// 현재 패널을 MainFrame에 추가
	}

	// 게임화면으로 넘어가는 메소드
	public void showGamePanel(TempPanel currentPanel) {
		this.currentPanel = currentPanel;
		contentPane.remove(currentPanel);// 현재 MainFrame에 추가된 패널 제거
		gamePanel = new GameThread(this);// GameTread 선언
		contentPane.add(gamePanel);// MainFrame에 추가
		show();// 화면에 표시
	}

	// resultPanel로 넘어가는 메소드
	public void resultGamePanel() {
		gamePanel.play.GameClear();// 게임 결과 상태
		currentPanel = new ResultPanel(this, gamePanel.play.GameClear());// 게임 클리어 or 게임 오버 판별위해 boolean 데이터를 넘겨 줌
		contentPane.remove(gamePanel);// 현재 MainFrame에 추가된 gameThread 제거
		contentPane.add(currentPanel);// resultPanel을 add
		show();
	}

	// menuPanel로 넘어가는 메소드(), gameThread에서 사용
	public void showMenuPanel() {
		contentPane.remove(gamePanel);// 현재 MainFrame에 추가된 gameThread 제거
		currentPanel = new MenuPanel(this);// MenuPanel 선언
		contentPane.add(currentPanel);// menuPanel을 add
		show();
	}

	// menuPanel로 넘어가는 메소드(p), resultPanel에서 사용
	public void showMenuPanel(TempPanel currentPanel) {
		contentPane.remove(currentPanel);// 현재 add 된 패널 제거
		currentPanel = new MenuPanel(this);// MenuPanel 선언
		contentPane.add(currentPanel);// add MenuPanel
		show();
	}

	// 다시하기를 누를 때 화면을 재구성하기 위한 메소드
	public void replayGamePanel() {
		contentPane.remove(currentPanel); // 현재 add된 패널 제거
		gamePanel = new GameThread(this);// 새로운 GameThread 객체 선언
		contentPane.add(gamePanel);// add GameThread
		show();
	}

	public void show() {
		mainFrame.setVisible(false); // 메인프레임을 보이지 않게한 후
		mainFrame.setVisible(true); // 다시 보이게 함
	}

}
