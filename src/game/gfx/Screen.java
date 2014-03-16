package game.gfx;

import game.level.tile.Tile;
import game.main.Game;

public class Screen {
	public int width, height;
	public int[] pixels;
	public int[] tiles;
	public int xOffset, yOffset;
	
	public Screen()
	{
		width = Game.WIDTH;
		height = Game.HEIGHT;
		pixels = new int[width * height];
	}

	public void renderTile(int xp, int yp, Tile tile)
	{
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y < tile.sprite.size; y++)
		{
			int yy = yp + y;
			for(int x = 0; x < tile.sprite.size; x++)
			{
				int xx = xp + x;
				if(xx < -tile.sprite.size || yy < 0 || yy >= height || xx >= width) break;
				if(xx < 0) xx = 0;
				pixels[xx + yy * width] = tile.sprite.pixels[x + y * tile.sprite.size];
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset)
	{
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}
