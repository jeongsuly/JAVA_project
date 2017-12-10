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

// 게임의 오브젝트를 배치하고 진행상태를 GameThread에 넘겨 줌
public class PlayState {

	private boolean paused = false;// 일지 정지를 위한 boolean 데이터

	private Player player;

	private ArrayList<Friends> friends;
	private int range; // 플레이어 탐지 범위

	private TileMap tileMap;

	private ArrayList<Book> books;

	private ArrayList<Pencil> pencils;

	private ArrayList<Sparkle> sparkles;

	private boolean isGameOver;
	private boolean isGameClear;

	public PlayState() {

		range = 48;

		// GameObject Arraylists 셍성
		books = new ArrayList<Book>();
		sparkles = new ArrayList<Sparkle>();
		pencils = new ArrayList<Pencil>();
		friends = new ArrayList<Friends>();

		// map 생성
		tileMap = new TileMap(16);
		tileMap.loadTiles("/Tilesets/tileset.gif");
		tileMap.loadMap();

		// player 생성
		player = new Player(tileMap);

		// 기타 GameObject 생성
		populateBooks();
		populatePencil();
		manyFriends();

		// player 초기화
		player.setTilePosition(14, 0);
		player.setTotalBooks(books.size());

		isGameOver = false;
		isGameClear = false;

	}

	// Friends들의 객체를 선언하고 맵에 배치
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

	// Book 객체를 선언하고 맵에 배치
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

	// Pencil 객체를 선언하고 맵에 배치
	private void populatePencil() {
		Pencil pen;
		pen = new Pencil(tileMap);
		pen.setTilePosition(14, 13);
		pencils.add(pen);
	}

	// 게임의 진행 상태를 확인해서 GameThread에 넘겨주기 위한 메소드
	public void update() {

		// 키 입력 check
		handleInput();

		// 게임 클리어 조건
		if (player.numBooks() == player.getTotalBooks()) {// 모든 책을 획득하고

			tileMap.setTile(2, 20, 1);// 필드위 모든 책을 획득 시 특정 위치의 타일 변경

			if (player.getRow() == 2 && player.getCol() == 20) {// 특정 위치로 이동
				isGameClear = true;
			}
		}

		if (tileMap.isMoving())
			return;


		player.update();

		// friends 추가
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

			// player가 book을 획득
			if (player.intersects(book)) {

				books.remove(i);
				i--;

				// 책을 몇권 획득 하였는지
				player.collectedBook();

				// sparkle 효과 추가
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

		// Player와 pencil의 상호작용을 check
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

	// 생성된 오브젝트들을 맵에 그려주는 메소드
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

	// key입력 check
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

	// 생성된 friends를 움직이고 충돌 감지
	public void setFriends(Friends f) {
		friendsMoving(f);// Friends의 이동 설정
		f.update();// Friends의 상태 Update

		// Player와 충돌
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

	// friends의 이동 설정
	public void friendsMoving(Friends f) {

		// Player가 friend의 range 범위 안에 들어왔을 때
		if ((Math.abs(player.getx() - f.getx()) < range && (Math.abs(player.gety() - f.gety())) < range)) {

			Random gen = new Random();
			int a = gen.nextInt(2) + 1;// 플레이어을 향한 방향 2개 랜덤 추출

			// player와 friends의 위치에 따른 friends의 이동방향 결정
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

		// Player와 Friends 사이의 거리가 범위를 벗어났을 경우
		else {
			Random gen = new Random();
			int a = gen.nextInt(4) + 1; // 랜덤값 4개를 받아 이동

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
