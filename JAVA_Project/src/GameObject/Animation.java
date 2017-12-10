package GameObject;

import java.awt.image.BufferedImage;

public class Animation {
	//�ִϸ��̼��� �����ϱ� ���� �������� ����
	private BufferedImage[] frames; 
	private int currentFrame;
	private int numFrames;
	private int count;
	private int delay;

	private int timesPlayed;	//��� ����Ǿ����� Ƚ���� ī��Ʈ�ϱ����� ���� ����
	
	public Animation() {
		timesPlayed = 0;	//�ִϸ��̼��� �ʱ� ����Ƚ���� 0
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
		return timesPlayed > 0; //�ѹ��̶� ����Ǵ��� booleanŸ�԰��� �����Ѵ�.
	}

	public boolean hasPlayed(int i) {
		return timesPlayed == i; //����Ǿ������� ���� booleanŸ�԰��� �����Ѵ�.
	}
	
	public void update() {

		if (delay == -1)
			//delay���� -1�� ��� �ƹ��͵� �����ʴ´�.
			return;

		count++;

		if (count == delay) {
			//delay���� count���� ���� �� currentFrame�� ������Ű�� count���� �ʱ�ȭ ��Ų��.
			currentFrame++;
			count = 0;
		}
		if (currentFrame == numFrames) {
			//currentFrame�� numFrames�� ������� currentFrame�� �ʱ�ȭ���� �ִϸ��̼��� ó������ �ٽ� �����Ű��
			currentFrame = 0;
			//�ִϸ��̼� ����Ƚ���� ����
			timesPlayed++;
		}

	}

}