package GameObject;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Manager.Content;
import TileMap.TileMap;

public class Friends extends GameObject {

	// �����¿�sprites
	private BufferedImage[] downSprites;
	private BufferedImage[] leftSprites;
	private BufferedImage[] rightSprites;
	private BufferedImage[] upSprites;
	// ���� �¿� �̵��� ���� ���� ����
	private final int DOWN = 0;
	private final int LEFT = 1;
	private final int RIGHT = 2;
	private final int UP = 3;
	

	public Friends(TileMap tm) {
		//16*16 ũ���� �̹����� ���� �����¿� �� �̵��� �ִϸ��̼��� ���� �ʱ�ȭ ����
		super(tm);

		width = 16;
		height = 16;
		//�⺻ �̵��ӵ� �� 2�� �ʱ�ȭ
		moveSpeed = 2;

		downSprites = Content.FRIENDS[0];
		leftSprites = Content.FRIENDS[1];
		rightSprites = Content.FRIENDS[2];
		upSprites = Content.FRIENDS[3];
		animation.setFrames(downSprites);
		animation.setDelay(10);

	}

	//�ִϸ��̼� �� �޼ҵ�
	public void setAnimation(int i, BufferedImage[] bi, int d) {
		currentAnimation = i;
		animation.setFrames(bi);
		animation.setDelay(d);
	}


	// �����¿� �̵�
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