package game.main;

import game.entity.mob.Player;
import game.gfx.Screen;
import game.input.Keyboard;
import game.level.Level;
import game.level.RandomLevel;

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
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 300;
	public static final int HEIGHT = WIDTH / 4 * 3; //675
	public static final int SCALE = 3;
	public static final String TITLE = "Gamiron 7 (Alpha 1.0)";
	
	private Thread thread;
	private boolean running = false;
	private boolean isVs = true;
	private String fps = "Fps: 60";
	
	private BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
	
	private Screen screen;
	private Level level;
	private Keyboard key;
	private Player player;
	
	private int x, y;
	
	private void init()
	{
		key = new Keyboard();
		screen = new Screen();
		level = new RandomLevel(32, 32);
		player = new Player(WIDTH/2, HEIGHT/2, key);
		
		player.init(level);
		
		this.addKeyListener(key);
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
		int xScroll = player.x - screen.width / 2;
		int yScroll = player.y - screen.height / 2;
		
		level.render(xScroll, yScroll, screen);
		player.render(screen);
		for(int i = 0; i < pixels.length; i++)
		{
			pixels[i] = screen.pixels[i];
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
		key.tick();
		player.tick();
		
		if(x < -32) x = -32;
		if(y < -32) y = -32;
		if(x > WIDTH - 56) x = WIDTH - 56;
		if(y > HEIGHT + 96) y = HEIGHT + 96;

		if(key.isRight) x += 2;
		if(key.isLeft) x -= 2;
		if(key.isUp) y -= 2;
		if(key.isDown) y += 2;
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
