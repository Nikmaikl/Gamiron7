package game.entity.mob;

import game.gfx.Screen;
import game.gfx.Sprite;
import game.input.Keyboard;

public class Player extends Mob{

	private Keyboard key;
	private boolean walking = false;
	
	public Player(int x, int y, Keyboard key)
	{
		this.x = x;
		this.y = y;
		this.key = key;
		sprite = Sprite.player;
	}
	
	public void tick()
	{
		int xa = 0, ya = 0;
		
		if(key.isRight) xa ++;
		if(key.isLeft) xa --;
		if(key.isUp) ya --;
		if(key.isDown) ya ++;
		
		if(xa != 0 || ya != 0)
		{
			move(xa, ya);
			walking = true;
		}
		else
		{
			walking = false;
		}
	}

	public void render(Screen screen) 
	{
		screen.renderPlayer(x - 8, y - 8, sprite);
	}

}
