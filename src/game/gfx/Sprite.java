package game.gfx;

public class Sprite 
{
	public int pixels[];
	public int size;
	public int x, y;
	public SpriteSheet ss;
	
	public static Sprite floor = new Sprite(16, 0, 0, SpriteSheet.tiles); 
	
	public Sprite(int size, int x, int y, SpriteSheet ss)
	{
		this.size = size;
		this.x = x * size;
		this.y = y * size;
		pixels = new int[size * size];
		this.ss = ss;
		load();
	}
	
	private void load()
	{
		for(int y = 0; y < size; y++)
		{
			for(int x = 0; x < size; x++)
			{
				pixels[x + y * size] = ss.pixels[(this.x + x) + (this.y + y) * ss.size]; 
			}
		}
	}
}
