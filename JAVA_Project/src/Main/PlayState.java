package Main;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import GameObject.Book;
import GameObject.Friends;
import GameObject.Pencil;
import GameObject.Player;
import GameObject.Sparkle;
import Manager.Keys;
import TileMap.TileMap;

// ������ ������Ʈ�� ��ġ�ϰ� ������¸� GameThread�� �Ѱ� ��
public class PlayState {

	private boolean paused = false;// ���� ������ ���� boolean ������

	private Player player;

	private ArrayList<Friends> friends;
	private int range; // �÷��̾� Ž�� ����

	private TileMap tileMap;

	private ArrayList<Book> books;

	private ArrayList<Pencil> pencils;

	private ArrayList<Sparkle> sparkles;

	private boolean isGameOver;
	private boolean isGameClear;

	public PlayState() {

		range = 48;

		// GameObject Arraylists �ļ�
		books = new ArrayList<Book>();
		sparkles = new ArrayList<Sparkle>();
		pencils = new ArrayList<Pencil>();
		friends = new ArrayList<Friends>();

		// map ����
		tileMap = new TileMap(16);
		tileMap.loadTiles("/Tilesets/tileset.gif");
		tileMap.loadMap();

		// player ����
		player = new Player(tileMap);

		// ��Ÿ GameObject ����
		populateBooks();
		populatePencil();
		manyFriends();

		// player �ʱ�ȭ
		player.setTilePosition(14, 0);
		player.setTotalBooks(books.size());

		isGameOver = false;
		isGameClear = false;

	}

	// Friends���� ��ü�� �����ϰ� �ʿ� ��ġ
	private void manyFriends() {

		Friends f;

		f = new Friends(tileMap);
		f.setTilePosition(2, 2);
		friends.add(f);
		f = new Friends(tileMap);
		f.setTilePosition(10, 6);
		friends.add(f);

		f = new Friends(tileMap);
		f.setTilePosition(2, 16);
		friends.add(f);

		f = new Friends(tileMap);
		f.setTilePosition(12, 15);
		friends.add(f);

	}

	// Book ��ü�� �����ϰ� �ʿ� ��ġ
	private void populateBooks() {

		Book book;

		book = new Book(tileMap);
		book.setTilePosition(2, 0);
		books.add(book);
		book = new Book(tileMap);
		book.setTilePosition(7, 0);
		books.add(book);
		book = new Book(tileMap);
		book.setTilePosition(14, 19);
		books.add(book);
		book = new Book(tileMap);
		book.setTilePosition(8, 17);
		books.add(book);
		book = new Book(tileMap);
		book.setTilePosition(6, 10);
		books.add(book);

	}

	// Pencil ��ü�� �����ϰ� �ʿ� ��ġ
	private void populatePencil() {
		Pencil pen;
		pen = new Pencil(tileMap);
		pen.setTilePosition(14, 13);
		pencils.add(pen);
	}

	// ������ ���� ���¸� Ȯ���ؼ� GameThread�� �Ѱ��ֱ� ���� �޼ҵ�
	public void update() {

		// Ű �Է� check
		handleInput();

		// ���� Ŭ���� ����
		if (player.numBooks() == player.getTotalBooks()) {// ��� å�� ȹ���ϰ�

			tileMap.setTile(2, 20, 1);// �ʵ��� ��� å�� ȹ�� �� Ư�� ��ġ�� Ÿ�� ����

			if (player.getRow() == 2 && player.getCol() == 20) {// Ư�� ��ġ�� �̵�
				isGameClear = true;
			}
		}

		if (tileMap.isMoving())
			return;


		player.update();

		// friends �߰�
		Friends f0 = friends.get(0);
		setFriends(f0);
		Friends f1 = friends.get(1);
		setFriends(f1);
		Friends f2 = friends.get(2);
		setFriends(f2);
		Friends f3 = friends.get(3);
		setFriends(f3);

		// update books
		for (int i = 0; i < books.size(); i++) {

			Book book = books.get(i);
			book.update();

			// player�� book�� ȹ��
			if (player.intersects(book)) {

				books.remove(i);
				i--;

				// å�� ��� ȹ�� �Ͽ�����
				player.collectedBook();

				// sparkle ȿ�� �߰�
				Sparkle s = new Sparkle(tileMap);
				s.setPosition(book.getx(), book.gety());
				sparkles.add(s);

			}
		}

		for (int i = 0; i < sparkles.size(); i++) {
			Sparkle s = sparkles.get(i);
			s.update();
			if (s.getRemove()) {
				sparkles.remove(i);
				i--;
			}
		}

		// Player�� pencil�� ��ȣ�ۿ��� check
		for (int i = 0; i < pencils.size(); i++) {
			Pencil pen = pencils.get(i);
			if (player.intersects(pen)) {
				pencils.remove(i);
				i--;
				pen.collected(player);
				Sparkle s = new Sparkle(tileMap);
				s.setPosition(pen.getx(), pen.gety());
				sparkles.add(s);
			}
		}

	}

	// ������ ������Ʈ���� �ʿ� �׷��ִ� �޼ҵ�
	public void draw(Graphics2D g) {

		tileMap.draw(g);

		player.draw(g);

		for (Friends f : friends) {
			f.draw(g);
		}
	
		for (Book d : books) {
			d.draw(g);
		}

		for (Sparkle s : sparkles) {
			s.draw(g);
		}

		for (Pencil i : pencils) {
			i.draw(g);
		}

	}

	// key�Է� check
	public void handleInput() {
		if (Keys.isPressed(Keys.ESCAPE)) {
			setPaused(true);
		}
		if (Keys.isDown(Keys.LEFT))
			player.setLeft();
		if (Keys.isDown(Keys.RIGHT))
			player.setRight();
		if (Keys.isDown(Keys.UP))
			player.setUp();
		if (Keys.isDown(Keys.DOWN))
			player.setDown();
		if (Keys.isPressed(Keys.SPACE))
			player.setAction();
	}

	// ������ friends�� �����̰� �浹 ����
	public void setFriends(Friends f) {
		friendsMoving(f);// Friends�� �̵� ����
		f.update();// Friends�� ���� Update

		// Player�� �浹
		if (player.intersects(f)) {
			isGameOver = true;
		}
	}

	public boolean getPaused() {
		return paused;
	}

	public void setPaused(boolean b) {
		paused = b;
	}

	public boolean GameOver() {
		return isGameOver;
	}

	public boolean GameClear() {
		return isGameClear;
	}

	// friends�� �̵� ����
	public void friendsMoving(Friends f) {

		// Player�� friend�� range ���� �ȿ� ������ ��
		if ((Math.abs(player.getx() - f.getx()) < range && (Math.abs(player.gety() - f.gety())) < range)) {

			Random gen = new Random();
			int a = gen.nextInt(2) + 1;// �÷��̾��� ���� ���� 2�� ���� ����

			// player�� friends�� ��ġ�� ���� friends�� �̵����� ����
			if (player.getx() >= f.getx() && player.gety() >= f.gety()) {

				if (player.getx() == f.getx()) {
					f.setDown();
				} else if (player.gety() == f.gety()) {
					f.setRight();
				} else if (a == 1) {
					f.setRight();
				} else if (a == 2) {
					f.setDown();
				}

			} else if (player.getx() <= f.getx() && player.gety() >= f.gety()) {

				if (player.getx() == f.getx()) {
					f.setDown();
				} else if (player.gety() == f.gety()) {
					f.setLeft();
				} else if (a == 1) {
					f.setLeft();
				} else if (a == 2) {
					f.setDown();
				}
			} else if (player.getx() >= f.getx() && player.gety() <= f.gety()) {

				if (player.getx() == f.getx()) {
					f.setUp();
				} else if (player.gety() == f.gety()) {
					f.setRight();
				} else if (a == 1) {
					f.setRight();
				} else if (a == 2) {
					f.setUp();
				}
			} else if (player.getx() <= f.getx() && player.gety() <= f.gety()) {
				if (player.getx() == f.getx()) {
					f.setUp();
				} else if (player.gety() == f.gety()) {
					f.setLeft();
				} else if (a == 1) {
					f.setLeft();
				} else if (a == 2) {
					f.setUp();
				}
			}

		}

		// Player�� Friends ������ �Ÿ��� ������ ����� ���
		else {
			Random gen = new Random();
			int a = gen.nextInt(4) + 1; // ������ 4���� �޾� �̵�

			if (a == 1)
				f.setLeft();
			if (a == 2)
				f.setRight();
			if (a == 3)
				f.setUp();
			if (a == 4)
				f.setDown();
		}
	}

}
