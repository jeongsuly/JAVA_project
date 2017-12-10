package GameObject;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Manager.Content;
import TileMap.TileMap;

public class Friends extends GameObject {

	// 상하좌우sprites
	private BufferedImage[] downSprites;
	private BufferedImage[] leftSprites;
	private BufferedImage[] rightSprites;
	private BufferedImage[] upSprites;
	// 상하 좌우 이동을 위한 변수 선언
	private final int DOWN = 0;
	private final int LEFT = 1;
	private final int RIGHT = 2;
	private final int UP = 3;
	

	public Friends(TileMap tm) {
		//16*16 크기의 이미지를 통해 상하좌우 및 이동의 애니메이션을 위해 초기화 선언
		super(tm);

		width = 16;
		height = 16;
		//기본 이동속도 는 2로 초기화
		moveSpeed = 2;

		downSprites = Content.FRIENDS[0];
		leftSprites = Content.FRIENDS[1];
		rightSprites = Content.FRIENDS[2];
		upSprites = Content.FRIENDS[3];
		animation.setFrames(downSprites);
		animation.setDelay(10);

	}

	//애니메이션 셋 메소드
	public void setAnimation(int i, BufferedImage[] bi, int d) {
		currentAnimation = i;
		animation.setFrames(bi);
		animation.setDelay(d);
	}


	// 상하좌우 이동
	public void setUp() {
		super.setUp();
	}

	public void setDown() {
		super.setDown();
	}

	public void setLeft() {
		super.setLeft();
	}

	public void setRight() {
		super.setRight();
	}

	public void update() {

		// set animation
		if (down) {
			if (currentAnimation != DOWN) {
				setAnimation(DOWN, downSprites, 10);
			}
		}
		if (left) {
			if (currentAnimation != LEFT) {
				setAnimation(LEFT, leftSprites, 10);
			}
		}
		if (right) {
			if (currentAnimation != RIGHT) {
				setAnimation(RIGHT, rightSprites, 10);
			}
		}
		if (up) {
			if (currentAnimation != UP) {
				setAnimation(UP, upSprites, 10);
			}
		}

		// update position
		super.update();

	}

	// Draw Friends.
	public void draw(Graphics2D g) {
		super.draw(g);
	}

}