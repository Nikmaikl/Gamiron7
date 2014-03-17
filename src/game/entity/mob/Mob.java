package game.entity.mob;

import game.entity.Entity;
import game.gfx.Screen;
import game.gfx.Sprite;

public abstract class Mob extends Entity{
	
	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving = false;
	
	public void move(int xa, int ya)
	{
		
		
		if(xa > 0) dir = 1;
		if(xa < 0) dir = 3;
		if(ya > 0) dir = 2;
		if(ya < 0) dir = 0;
		
		x += xa;
		y += ya;
		
	}
	
	public abstract void tick();
	public abstract void render(Screen screen);

	public boolean collision(int xa, int ya)
	{
		boolean solid = false;
		return solid;
	}
}
