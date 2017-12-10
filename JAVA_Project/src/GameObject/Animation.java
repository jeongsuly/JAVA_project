package GameObject;

import java.awt.image.BufferedImage;

public class Animation {
	//애니메이션을 구현하기 위한 변수들을 선언
	private BufferedImage[] frames; 
	private int currentFrame;
	private int numFrames;
	private int count;
	private int delay;

	private int timesPlayed;	//몇번 실행되었는지 횟수를 카운트하기위한 변수 선언
	
	public Animation() {
		timesPlayed = 0;	//애니메이션의 초기 실행횟수는 0
	}

	public void setFrames(BufferedImage[] frames) {
		//Frames set
		this.frames = frames;
		currentFrame = 0;
		count = 0;
		timesPlayed = 0;
		delay = 2;
		numFrames = frames.length;
	}

	public void setDelay(int i) {
		delay = i; //delay set
	}

	public void setFrame(int i) {
		currentFrame = i; //currentFrame set
	}

	public void setNumFrames(int i) {
		numFrames = i; //numFrame set
	}

	public int getFrame() {
		return currentFrame; //get currentFrame
	}

	public BufferedImage getImage() {
		return frames[currentFrame]; //getImage from number of currentFrame
	}

	public boolean hasPlayedOnce() {
		return timesPlayed > 0; //한번이라도 실행되는지 boolean타입값을 리턴한다.
	}

	public boolean hasPlayed(int i) {
		return timesPlayed == i; //실행되었는지에 대한 boolean타입값을 리턴한다.
	}
	
	public void update() {

		if (delay == -1)
			//delay값이 -1일 경우 아무것도 하지않는다.
			return;

		count++;

		if (count == delay) {
			//delay값과 count값이 같을 때 currentFrame을 증가시키고 count값을 초기화 시킨다.
			currentFrame++;
			count = 0;
		}
		if (currentFrame == numFrames) {
			//currentFrame과 numFrames가 같을경우 currentFrame을 초기화시켜 애니메이션을 처음부터 다시 실행시키고
			currentFrame = 0;
			//애니메이션 실행횟수를 증가
			timesPlayed++;
		}

	}

}