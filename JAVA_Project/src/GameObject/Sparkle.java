package GameObject;

import java.awt.Graphics2D;

import Manager.Content;
import TileMap.TileMap;

public class Sparkle extends GameObject {

	private boolean remove; // �ִϸ��̼��� �ѹ� �����ϰ� ������� �ϱ� ������ booleanŸ���� ������ �����Ͽ� ����� ���� �Ǵܿ� ���

	public Sparkle(TileMap tm) {
		//16*16 �̹����� Sparkle �ִϸ��̼� ������ ���� �ʱ�ȭ
		super(tm);
		animation.setFrames(Content.SPARKLE[0]);
		animation.setDelay(5);
		width = height = 16;
	}
	//remove���� ���� �� �޼ҵ�
	public boolean getRemove() {
		return remove;
	}

	public void update() {
		//�ִϸ��̼��� �ѹ� ����Ǹ� ������
		animation.update();
		if (animation.hasPlayedOnce())
			remove = true;
	}
	//Draw
	public void draw(Graphics2D g) {
		super.draw(g);
	}

}
