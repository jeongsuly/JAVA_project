
package GameObject;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Manager.Content;
import TileMap.TileMap;

public class Player extends GameObject {

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

	// gameplay�� ���� ������ ����
	private int numBooks;
	private int totalBooks;
	private boolean hasPencil;

	public Player(TileMap tm) {
		//16*16 ũ���� �̹����� ���� �����¿� �� �̵��� �ִϸ��̼��� ���� �ʱ�ȭ ����
		super(tm);

		width = 16;
		height = 16;
		//�̵��ӵ��� 2�� �ʱ�ȭ
		moveSpeed = 2;
		//���ʷ� ����ִ� å�� ������ 0���� �ʱ�ȭ
		numBooks = 0;
		//�����¿� �̵��� ���� sprites
		downSprites = Content.PLAYER[0];
		leftSprites = Content.PLAYER[1];
		rightSprites = Content.PLAYER[2];
		upSprites = Content.PLAYER[3];

		animation.setFrames(downSprites);
		animation.setDelay(10);

	}
	//�ִϸ��̼��� �¸޼ҵ�
	public void setAnimation(int i, BufferedImage[] bi, int d) {
		currentAnimation = i;
		animation.setFrames(bi);
		animation.setDelay(d);
	}

	public void collectedBook() {
		numBooks++;	//å�� ȹ���Ͽ��� �� ���� å�� ���� ������Ų��.
	}

	public int numBooks() {
		return numBooks; //	���� å�� ��
	}
	//totalBooks�� �� �޼ҵ�
	public int getTotalBooks() {
		return totalBooks;
	}
	//totalBooks�� �� �޼ҵ�
	public void setTotalBooks(int i) {
		totalBooks = i;
	}
	//Pencil�� ���������
	public void gotPencil() {
		hasPencil = true;
	}
	//Pencil�� ����ִ����� ���� �Ǵ�
	public boolean hasPencil() {
		return hasPencil;
	}



	// Ű �Է�. player�� �̵���Ŵ.
	public void setDown() {
		super.setDown();
	}

	public void setLeft() {
		super.setLeft();
	}

	public void setRight() {
		super.setRight();
	}

	public void setUp() {
		super.setUp();
	}

	// action input
	// map[][] = 21 �� �� ���� ����ϸ� å�� ȹ�� �����ϰ� ��
	public void setAction() {
		if (hasPencil) {
			if (currentAnimation == UP && tileMap.getIndex(rowTile - 1, colTile) == 21) {
				tileMap.setTile(rowTile - 1, colTile, 1);
			} else if (currentAnimation == DOWN && tileMap.getIndex(rowTile + 1, colTile) == 21) {
				tileMap.setTile(rowTile + 1, colTile, 1);
			} else if (currentAnimation == LEFT && tileMap.getIndex(rowTile, colTile - 1) == 21) {
				tileMap.setTile(rowTile, colTile - 1, 1);
			} else if (currentAnimation == RIGHT && tileMap.getIndex(rowTile, colTile + 1) == 21) {
				tileMap.setTile(rowTile, colTile + 1, 1);
			} else {
				return;
			}
		}
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

	// Draw Player.
	public void draw(Graphics2D g) {
		super.draw(g);
	}

}