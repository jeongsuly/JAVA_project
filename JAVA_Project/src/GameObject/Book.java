package GameObject;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Manager.Content;
import TileMap.TileMap;

public class Book extends GameObject {

	private BufferedImage[] sprites; //애니메이션구현을위한 sprites 선언

	private ArrayList<int[]> tileChanges; //Book의 애니메이션을 Arraylist로 구현하기위해 선언

	public Book(TileMap tm) {
		//16*16이미지를 배열을 통해 반복하여 애니메이션을 구현하도록 초기화 
		super(tm);
		
		width = 16;
		height = 16;

		sprites = Content.BOOK[0];
		animation.setFrames(sprites);
		animation.setDelay(7);
		tileChanges = new ArrayList<int[]>();
	}

	public ArrayList<int[]> getChanges() {
		return tileChanges; //tileChages의 Get메소드
	}

	public void update() {
		animation.update(); //애니메이션 구동
	}

	public void draw(Graphics2D g) {
		super.draw(g); //화면에 뿌리는 draw
	}

}
