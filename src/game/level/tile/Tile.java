package game.level.tile;

import game.gfx.Screen;
import game.gfx.Sprite;

public abstract class Tile
{
	public Sprite sprite;
	
	public static Tile floor = new FloorTile(Sprite.floor);
	public static Tile wall = new WallTile(Sprite.wall);
	
	public Tile(Sprite sprite)
	{
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen)
	{
	}
	
	public boolean solid()
	{
		return false;
	}
}
