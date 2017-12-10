package GameObject;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import TileMap.Tile;
import TileMap.TileMap;

public abstract class GameObject {

	// ũ��
	protected int width;
	protected int height;

	// ��ǥ
	protected int x;
	protected int y;
	protected int xdest;
	protected int ydest;
	protected int rowTile;
	protected int colTile;

	// �̵�
	protected boolean moving;
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;

	// �̵��ӵ� ����
	protected int moveSpeed;

	// tilemap
	protected TileMap tileMap;
	protected int tileSize;

	// animation
	protected Animation animation;
	protected int currentAnimation;

	public GameObject(TileMap tm) {
		tileMap = tm;
		tileSize = tileMap.getTileSize();
		animation = new Animation();
	}
///////////////////////////////////////////////
	// x,y��ǥ�� ��,���� �� �޼ҵ�
	public int getx() {
		return x;
	}

	public int gety() {
		return y;
	}

	public int getRow() {
		return rowTile;
	}

	public int getCol() {
		return colTile;
	}

///////////////////////////////////////////////
	//�� ��ǥ�� �����¿��̵��� �¸޼ҵ�
	public void setPosition(int i1, int i2) {
		x = i1;
		y = i2;
		xdest = x;
		ydest = y;
	}

	public void setTilePosition(int i1, int i2) {
		y = i1 * tileSize + tileSize / 2;
		x = i2 * tileSize + tileSize / 2;
		xdest = x;
		ydest = y;
	}
	
	public void setUp() {
		if (moving)
			return;
		up = true;
		moving = validateNextPosition();
	}

	public void setDown() {
		if (moving)
			return;
		down = true;
		moving = validateNextPosition();
	}
	
	public void setLeft() {
		if (moving)
			return;
		left = true;
		moving = validateNextPosition();
	}

	public void setRight() {
		if (moving)
			return;
		right = true;
		moving = validateNextPosition();
	}

////////////////////////////////////////////////
	//�̵��� ���� �� �� �޼ҵ�
	public boolean getMoving() {
		return moving;
	}

	public void setMoving(boolean move) {
		moving = move;
	}

	// �浹 �˻�
	public boolean intersects(GameObject o) {
		return getRectangle().intersects(o.getRectangle());
	}

	// �浹�˻縦 ���� Rect get
	public Rectangle getRectangle() {
		return new Rectangle(x, y, 12, 12);
	}

	// �Էµ� Ű �������� �̵��� �� �ִ��� �Ǵ��ϴ� �޼ҵ�
	public boolean validateNextPosition() {

		if (moving)
			return true;

		rowTile = y / tileSize;
		colTile = x / tileSize;

		if (left) {
			if (colTile == 0 || tileMap.getType(rowTile, colTile - 1) == Tile.BLOCKED) {
				return false;
			} else {
				xdest = x - tileSize;
			}
		}
		if (right) {
			if (colTile == tileMap.getNumCols() - 1 || tileMap.getType(rowTile, colTile + 1) == Tile.BLOCKED) {
				return false;
			} else {
				xdest = x + tileSize;
			}
		}
		if (up) {
			if (rowTile == 0 || tileMap.getType(rowTile - 1, colTile) == Tile.BLOCKED) {
				return false;
			} else {
				ydest = y - tileSize;
			}
		}
		if (down) {
			if (rowTile == tileMap.getNumRows() - 1 || tileMap.getType(rowTile + 1, colTile) == Tile.BLOCKED) {
				return false;
			} else {
				ydest = y + tileSize;
			}
		}

		return true;

	}

	// �̵��� ��ǥ ����ϴ� �޼ҵ�
	public void getNextPosition() {

		if (left && x > xdest)
			x -= moveSpeed;
		else
			left = false;
		if (left && x < xdest)
			x = xdest;

		if (right && x < xdest)
			x += moveSpeed;
		else
			right = false;
		if (right && x > xdest)
			x = xdest;

		if (up && y > ydest)
			y -= moveSpeed;
		else
			up = false;
		if (up && y < ydest)
			y = ydest;

		if (down && y < ydest)
			y += moveSpeed;
		else
			down = false;
		if (down && y > ydest)
			y = ydest;

	}
	//���� ������Ʈ�� ���� ������Ʈ
	public void update() {

		// ���� ��ǥ �ް�
		if (moving)
			getNextPosition();

		// ���� ��ǥ�� �̵�
		if (x == xdest && y == ydest) {
			left = right = up = down = moving = false;
			rowTile = y / tileSize;
			colTile = x / tileSize;
		}
		// update animation
		animation.update();

	}

	// Draws the Object.
	public void draw(Graphics2D g) {
		g.drawImage(animation.getImage(), x - width / 2, y - height / 2, null);
	}

}
