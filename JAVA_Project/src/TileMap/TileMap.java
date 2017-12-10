package TileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Main.MainFrame;

public class TileMap {

	// 좌표
	private int x;
	private int y;
	private int xdest;// 이동할 x 좌표
	private int ydest;// 이동할 y 좌표
	private int speed;
	private boolean isMoving;

	private int[][] map;
	private int tileSize;
	private int numRows;
	private int numCols;
	private int[][] stageMap;// Tile type을 2차원 배열로 만들어 loadMap method에서 쉽게 맵 수정 가능하게 하기 위해

	private BufferedImage tileset;
	private int numTilesAcross;
	private Tile[][] tiles;

	public TileMap(int tileSize) {
		this.tileSize = tileSize;
		numRows = MainFrame.HEIGHT / tileSize + 2;
		numCols = MainFrame.WIDTH / tileSize + 2;
		speed = 4;
	}

	// Tile 생성
	public void loadTiles(String s) {

		try {

			tileset = ImageIO.read(getClass().getResourceAsStream(s));
			numTilesAcross = tileset.getWidth() / tileSize;

			tiles = new Tile[2][numTilesAcross];

			BufferedImage subimage;
			for (int col = 0; col < numTilesAcross; col++) {
				subimage = tileset.getSubimage(col * tileSize, 0, tileSize, tileSize);
				tiles[0][col] = new Tile(subimage, Tile.NORMAL);
				subimage = tileset.getSubimage(col * tileSize, tileSize, tileSize, tileSize);
				tiles[1][col] = new Tile(subimage, Tile.BLOCKED);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void loadMap() {

		map = new int[numRows][numCols];

		stageMap = new int[numRows][numCols];
		//0~19 이동가능 20~39 이동불가 21은 Pencil을 들고있을때 1로 변환가능
		int[][] stageMap = { 
				{ 6, 8, 9, 10, 11, 12, 13, 6, 7, 8, 9, 10, 11, 8, 9, 6, 7, 8, 9, 10, 11, 6, 20, 20 },
				{ 26, 28, 29, 30, 31, 32, 33, 26, 26, 28, 29, 30, 31, 28, 29, 26, 26, 28, 29, 30, 31, 27, 20, 20 },
				{ 21, 20, 20, 20, 1, 1, 1, 1, 20, 20, 20, 1, 1, 1, 1, 20, 20, 20, 1, 1, 34, 36, 20, 20 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 34, 1, 1, 1, 1, 1, 1, 1, 1, 1, 20, 20 },
				{ 22, 23, 1, 22, 23, 1, 34, 1, 1, 1, 1, 1, 35, 1, 20, 1, 1, 1, 1, 20, 20, 1, 20, 20 },
				{ 24, 25, 1, 24, 25, 1, 35, 1, 22, 23, 20, 1, 1, 1, 1, 1, 22, 23, 1, 20, 20, 1, 20, 20 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 22, 23, 21, 1, 1, 1, 1, 1, 22, 23, 1, 20, 20, 1, 20, 20 },
				{ 21, 20, 1, 20, 20, 1, 1, 1, 24, 25, 20, 1, 1, 20, 20, 1, 24, 25, 1, 1, 1, 1, 20, 20 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 20, 1, 1, 21, 1, 20, 20, 20, 20, 20 },
				{ 22, 23, 22, 1, 23, 23, 1, 1, 1, 1, 1, 34, 1, 1, 20, 1, 1, 34, 1, 1, 1, 1, 20, 20 },
				{ 24, 25, 24, 1, 25, 25, 1, 20, 1, 20, 1, 35, 1, 1, 1, 1, 1, 35, 1, 1, 20, 1, 20, 20 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 20, 1, 1, 1, 1, 1, 20, 20, 1, 20, 20 },
				{ 1, 1, 1, 34, 1, 20, 1, 1, 1, 20, 20, 20, 1, 20, 1, 1, 20, 20, 1, 1, 1, 1, 20, 20 },
				{ 1, 1, 1, 35, 1, 1, 1, 22, 23, 22, 23, 1, 1, 1, 1, 1, 22, 23, 1, 1, 1, 1, 20, 20 },
				{ 37, 1, 1, 1, 1, 1, 1, 24, 25, 24, 25, 1, 1, 1, 1, 1, 24, 25, 1, 21, 1, 1, 20, 20 },
				{ 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20 },
				{ 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20 }

		};


		for (int row = 0; row < numRows; row++) {

			for (int col = 0; col < numCols; col++) {
				map[row][col] = stageMap[row][col];
			}
		}

	}


	public int getTileSize() {
		return tileSize;
	}

	public int getx() {
		return x;
	}

	public int gety() {
		return y;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumCols() {
		return numCols;
	}


	public int getType(int row, int col) {
		int rc = map[row][col];
		int r = rc / numTilesAcross;
		int c = rc % numTilesAcross;
		return tiles[r][c].getType();
	}

	public int getIndex(int row, int col) {
		return map[row][col];
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setTile(int row, int col, int index) {
		map[row][col] = index;
	}

	// 이벤트에 따른 Tile 변경
	public void replace(int before, int after) {
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				if (map[row][col] == before)
					map[row][col] = after;
			}
		}
	}

	public void update() {
		if (x < xdest) {
			x += speed;
			if (x > xdest) {
				x = xdest;
			}
		}
		if (x > xdest) {
			x -= speed;
			if (x < xdest) {
				x = xdest;
			}
		}
		if (y < ydest) {
			y += speed;
			if (y > ydest) {
				y = ydest;
			}
		}
		if (y > ydest) {
			y -= speed;
			if (y < ydest) {
				y = ydest;
			}
		}

		if (x != xdest || y != ydest)
			isMoving = true;
		else
			isMoving = false;

	}

	public void draw(Graphics2D g) {

		for (int row = 0; row < numRows; row++) {

			for (int col = 0; col < numCols; col++) {

				int rc = map[row][col];
				int r = rc / numTilesAcross;
				int c = rc % numTilesAcross;

				g.drawImage(tiles[r][c].getImage(), x + col * tileSize, y + row * tileSize, null);// 맞는 Tile draw

			}

		}

	}

}