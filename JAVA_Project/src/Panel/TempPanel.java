package Panel;

import javax.swing.JPanel;

// PanelController 클래스에서 resultPanel과 MenuPanel을 전환하기 위한 추상 클래스
abstract public class TempPanel extends JPanel {

	abstract public void gameStart();// 자식클래스에서 공통으로 구현할 메소드

}
