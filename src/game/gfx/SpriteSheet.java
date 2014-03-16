package game.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet
{
	public int[] pixels;
	public int size;
	private String path;
	
	public static SpriteSheet tiles = new SpriteSheet(256, "/tiles.png");
	
	public SpriteSheet(int size, String path)
	{
		this.size = size;
		pixels = new int[size * size];
		this.path = path;
		load();
	}

	private void load()
	{
		BufferedImage img;
		try {
			img = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = img.getWidth();
			int h = img.getHeight();
			img.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
