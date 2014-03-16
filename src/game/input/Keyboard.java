package game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener
{
	private boolean[] keys = new boolean[120];
	public boolean isUp, isDown, isRight, isLeft;
	
	public void tick()
	{
		isUp = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		isDown = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		isRight = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		isLeft = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
	}

	public void keyPressed(KeyEvent e)
	{
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e)
	{
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {
	}
}
