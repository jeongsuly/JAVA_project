
package Manager;

import java.awt.event.KeyEvent;

public class Keys {

	public static final int NUM_KEYS = 6;

	public static boolean keyState[] = new boolean[NUM_KEYS];
	public static boolean prevKeyState[] = new boolean[NUM_KEYS];

	public static int UP = 0;
	public static int LEFT = 1;
	public static int DOWN = 2;
	public static int RIGHT = 3;
	public static int SPACE = 4;
	public static int ESCAPE = 5;

	public static void keySet(int i, boolean b) {
		if (i == KeyEvent.VK_UP) {
			keyState[UP] = b;
		} else if (i == KeyEvent.VK_LEFT) {
			keyState[LEFT] = b;
		} else if (i == KeyEvent.VK_DOWN) {
			keyState[DOWN] = b;
		} else if (i == KeyEvent.VK_RIGHT) {
			keyState[RIGHT] = b;
		} else if (i == KeyEvent.VK_SPACE) {
			keyState[SPACE] = b;
		} else if (i == KeyEvent.VK_ESCAPE) {
			keyState[ESCAPE] = b;
		}
	}

	// 게임을 재시작할때 이전에 남아있던 모든 키 입력값을 false로 바꿈
	public static void init() {
		for (int i = 0; i < NUM_KEYS; i++) {
			prevKeyState[i] = keyState[i] = false;
		}
	}

	// 쓰레드가 동작하는동안 키 입력을 업데이트
	public static void update() {
		for (int i = 0; i < NUM_KEYS; i++) {
			prevKeyState[i] = keyState[i];
		}
	}

	public static boolean isPressed(int i) {
		return keyState[i] && !prevKeyState[i];
	}

	public static boolean isDown(int i) {
		return keyState[i];
	}

	public static boolean anyKeyDown() {
		for (int i = 0; i < NUM_KEYS; i++) {
			if (keyState[i])
				return true;
		}
		return false;
	}

	public static boolean anyKeyPress() {
		for (int i = 0; i < NUM_KEYS; i++) {
			if (keyState[i] && !prevKeyState[i])
				return true;
		}
		return false;
	}

}
