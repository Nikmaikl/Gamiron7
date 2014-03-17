package game.entity;

import game.gfx.Screen;
import game.level.Level;

public abstract class Entity {
	public int x, y;
	public Level level;
	private boolean removed;
	
	public abstract void tick();
	public abstract void render(Screen screen);
	
	public void remove()
	{
		removed = true;
	}
	
	public boolean isRemoved()
	{
		return removed;
	}
	
	public void init(Level level)
	{
		this.level = level;
	}
}
