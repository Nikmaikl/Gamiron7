package game.entity.mob;

import game.gfx.Screen;
import game.gfx.Sprite;
import game.input.Keyboard;

public class Player extends Mob{

	private Keyboard key;
	private boolean walking = false;
	private int anim;
	
	public Player(int x, int y, Keyboard key)
	{
		this.x = x;
		this.y = y;
		this.key = key;
		sprite = Sprite.player_forward;
	}
	int time = 0;
	public void tick()
	{
		//time++;
		int xa = 0, ya = 0;
		
		//if(time % 50 == 0) xa++;
		
		if(anim < 7500) anim++; else anim = 0;
		
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
		if(dir == 2)
		{
			sprite = Sprite.player_forward;
			
			if(walking)
			{
				if(anim % 20 > 10)
				{
					sprite = Sprite.player_forward1;
				}
				else
				{
					sprite = Sprite.player_forward2;
				}

			}
		}
		
		if(dir == 3)
		{
			sprite = Sprite.player_left;
			if(walking)
			{
				if(anim % 20 > 10)
				{
					sprite = Sprite.player_left1;
				}
				else
				{
					sprite = Sprite.player_left2;
				}

			}
		}
		
		screen.renderPlayer(x - 8, y - 8, sprite);
	}

}
