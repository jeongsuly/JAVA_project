package GameObject;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Manager.Content;
import TileMap.TileMap;

public class Book extends GameObject {

	private BufferedImage[] sprites; //�ִϸ��̼Ǳ��������� sprites ����

	private ArrayList<int[]> tileChanges; //Book�� �ִϸ��̼��� Arraylist�� �����ϱ����� ����

	public Book(TileMap tm) {
		//16*16�̹����� �迭�� ���� �ݺ��Ͽ� �ִϸ��̼��� �����ϵ��� �ʱ�ȭ 
		super(tm);
		
		width = 16;
		height = 16;

		sprites = Content.BOOK[0];
		animation.setFrames(sprites);
		animation.setDelay(7);
		tileChanges = new ArrayList<int[]>();
	}

	public ArrayList<int[]> getChanges() {
		return tileChanges; //tileChages�� Get�޼ҵ�
	}

	public void update() {
		animation.update(); //�ִϸ��̼� ����
	}

	public void draw(Graphics2D g) {
		super.draw(g); //ȭ�鿡 �Ѹ��� draw
	}

}
