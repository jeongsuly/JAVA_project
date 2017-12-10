package GameObject;

import java.awt.Graphics2D;

import Manager.Content;
import TileMap.TileMap;

public class Sparkle extends GameObject {

	private boolean remove; // 애니메이션이 한번 동작하고 사라져야 하기 때문에 boolean타입의 변수를 선언하여 사라질 여부 판단에 사용

	public Sparkle(TileMap tm) {
		//16*16 이미지의 Sparkle 애니메이션 구현을 위한 초기화
		super(tm);
		animation.setFrames(Content.SPARKLE[0]);
		animation.setDelay(5);
		width = height = 16;
	}
	//remove값에 대한 겟 메소드
	public boolean getRemove() {
		return remove;
	}

	public void update() {
		//애니메이션이 한번 재생되면 제거함
		animation.update();
		if (animation.hasPlayedOnce())
			remove = true;
	}
	//Draw
	public void draw(Graphics2D g) {
		super.draw(g);
	}

}
