// 모든 sprites를 관리하는 클래스
// 프로젝트의 모든 곳에서 사용하기 때문에 데이터들을 static으로 선언

package Manager;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Content {

	public static BufferedImage[][] MENUBG = load("/Screen/menuscreen.gif", 352, 240);
	public static BufferedImage[][] CLEAR = load("/Screen/stageclear.gif", 352, 239);
	public static BufferedImage[][] OVER = load("/Screen/stagefailed.gif", 352, 239);
	public static BufferedImage[][] PLAYER = load("/Sprites/playersprites.gif", 16, 16);
	public static BufferedImage[][] FRIENDS = load("/Sprites/friendsprites.gif", 16, 16);
	public static BufferedImage[][] BOOK = load("/Sprites/book.gif", 16, 16);
	public static BufferedImage[][] SPARKLE = load("/Sprites/sparkle.gif", 16, 16);
	public static BufferedImage[][] ITEMS = load("/Sprites/items.gif", 16, 16);

	public static BufferedImage[][] load(String s, int c, int r) {
		BufferedImage[][] ret;

		try {
			BufferedImage spritesheet = ImageIO.read(Content.class.getResourceAsStream(s));

			int col = spritesheet.getWidth() / c;
			int row = spritesheet.getHeight() / r;

			ret = new BufferedImage[row][col];

			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					ret[i][j] = spritesheet.getSubimage(j * c, i * r, c, r);
				}
			}
			return ret;

		} catch (Exception e) {

		}
		return null;
	}

}
