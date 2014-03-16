package game.main;

import game.gfx.Screen;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable
{
	public static final int WIDTH = 300;
	public static final int HEIGHT = WIDTH / 16 * 9;
	public static final int SCALE = 3;
	public static final String TITLE = "";
	
	private Thread thread;
	private boolean running = false;
	private boolean isVs = true;
	private String fps = "Fps: 60";
	
	private BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
	
	private Screen screen;
	
	private void init()
	{
		screen = new Screen();
	}
	
	public synchronized void start()
	{
		running = true;
		thread = new Thread(this, "GameThread");
		thread.start();
	}
	
	public synchronized void stop()
	{
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if(bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
		
		g.setColor(Color.white);
		g.setFont(new Font("Segoe UI", Font.BOLD, 13));
		g.drawString(fps, 5, 15);
		
		g.dispose();
		bs.show();
	}
	
	private void tick()
	{
	
	}
	
	public void run()
	{
		init();
		setFocusable(true);
		
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0;
		double ns = 1000000000/60.0D;
		int frames = 0;
		int ticks = 0;
		
		while(running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta > 1)
			{
				tick();
				ticks++;
				delta--;
			}
			
			frames++;
			render();
			
			if(isVs)
			{
				try {
					Thread.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			if(System.currentTimeMillis() - timer >= 1000)
			{
				fps = "Fps: " + frames;
				frames = 0;
				ticks = 0;
				timer += 1000;
			}
		}
	}
	
	public static void main(String[] args)
	{
		Game game = new Game();
		game.setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));
		JFrame frame = new JFrame(Game.TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();
	}

}