package game.level;

import game.gfx.Screen;
import game.level.tile.Tile;

public abstract class Level
{
	public int[] tiles;
	public int width, height;
	
	public Level(int width, int height)
	{
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		generateLevel();
	}
	
	protected abstract void generateLevel();
	
	public void render(int xScroll, int yScroll, Screen screen)
	{
		screen.setOffset(xScroll, yScroll);
		
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;
		
		for(int y = y0; y < y1; y++)
		{
			for(int x = x0; x < x1; x++)
			{
				getTile(x, y).render(x, y, screen);
			}
		}
	}
	
	public void tick()
	{
	}
	
	private Tile getTile(int x, int y)
	{
		if(x >= width || x < 0 || y >= height || y < 0) return Tile.floor;
		if(tiles[x + y * width] == 1) return Tile.wall;
		//(tiles[x + y * width] == 2) return Tile.wall;
		return Tile.floor;
	}
}
