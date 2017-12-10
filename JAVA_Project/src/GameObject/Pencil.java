package GameObject;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Manager.Content;
import TileMap.TileMap;

public class Pencil extends GameObject {
	
	private BufferedImage sprite;

	public Pencil(TileMap tm) {
		//Pencil�� ITEMS�� 0,0�� �����ϴ� 16*16 ũ���� �̹���
		super(tm);
		width = height = 16;
		sprite = Content.ITEMS[0][0];
	}
	//�÷��̾ Pencil�� ����ִ����� ���θ� �Ǵ�
	public void collected(Player p) {
		p.gotPencil();
	}
	//Pencil Draw
	public void draw(Graphics2D g) {
		g.drawImage(sprite, x - width / 2, y - height / 2, null);
	}

}
