package GameObject;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Manager.Content;
import TileMap.TileMap;

public class Pencil extends GameObject {
	
	private BufferedImage sprite;

	public Pencil(TileMap tm) {
		//Pencil은 ITEMS의 0,0에 존재하는 16*16 크기의 이미지
		super(tm);
		width = height = 16;
		sprite = Content.ITEMS[0][0];
	}
	//플레이어가 Pencil을 들고있는지의 여부를 판단
	public void collected(Player p) {
		p.gotPencil();
	}
	//Pencil Draw
	public void draw(Graphics2D g) {
		g.drawImage(sprite, x - width / 2, y - height / 2, null);
	}

}
