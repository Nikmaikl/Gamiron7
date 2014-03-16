package game.gfx;

import game.main.Game;

public class Screen {
	public int width, height;
	public int[] pixels;
	public int[] tiles;
	
	public Screen()
	{
		width = Game.WIDTH;
		height = Game.HEIGHT;
		pixels = new int[width * height];
	}
	
	public void render()
	{
		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				pixels[x + y * width] = Sprite.floor.pixels[(x & 15) + (y & 15) * Sprite.floor.size];
			}
		}
	}
}
