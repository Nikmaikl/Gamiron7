package game.level.tile;

import game.gfx.Screen;
import game.gfx.Sprite;

public class FloorTile extends Tile
{
	public FloorTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen)
	{
		screen.renderTile(x << 4, y << 4, this);
	}
}
