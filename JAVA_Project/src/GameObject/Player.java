
package GameObject;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Manager.Content;
import TileMap.TileMap;

public class Player extends GameObject {

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

	// gameplay를 위한 변수들 선언
	private int numBooks;
	private int totalBooks;
	private boolean hasPencil;

	public Player(TileMap tm) {
		//16*16 크기의 이미지를 통해 상하좌우 및 이동의 애니메이션을 위해 초기화 선언
		super(tm);

		width = 16;
		height = 16;
		//이동속도는 2로 초기화
		moveSpeed = 2;
		//최초로 들고있는 책의 개수는 0으로 초기화
		numBooks = 0;
		//상하좌우 이동에 대한 sprites
		downSprites = Content.PLAYER[0];
		leftSprites = Content.PLAYER[1];
		rightSprites = Content.PLAYER[2];
		upSprites = Content.PLAYER[3];

		animation.setFrames(downSprites);
		animation.setDelay(10);

	}
	//애니메이션의 셋메소드
	public void setAnimation(int i, BufferedImage[] bi, int d) {
		currentAnimation = i;
		animation.setFrames(bi);
		animation.setDelay(d);
	}

	public void collectedBook() {
		numBooks++;	//책을 획득하였을 때 가진 책의 수를 증가시킨다.
	}

	public int numBooks() {
		return numBooks; //	가진 책의 수
	}
	//totalBooks의 겟 메소드
	public int getTotalBooks() {
		return totalBooks;
	}
	//totalBooks의 셋 메소드
	public void setTotalBooks(int i) {
		totalBooks = i;
	}
	//Pencil을 들고있을때
	public void gotPencil() {
		hasPencil = true;
	}
	//Pencil을 들고있는지의 유무 판단
	public boolean hasPencil() {
		return hasPencil;
	}



	// 키 입력. player를 이동시킴.
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
	// map[][] = 21 일 때 펜을 사용하면 책을 획득 가능하게 함
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