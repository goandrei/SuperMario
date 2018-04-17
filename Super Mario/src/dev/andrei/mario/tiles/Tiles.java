package dev.andrei.mario.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.andrei.mario.gfx.Assets;

public abstract class Tiles {

	public static Tiles[] tiles = new Tiles[257];
	public static Tiles ground = new Ground(0,Assets.ground);
	public static Tiles sky = new Sky(1,Assets.sky);
	public static Tiles smallCloud = new SmallCloud(2,Assets.smallCloud);
	public static Tiles bigCloud = new BigCloud(3,Assets.bigCloud);
	public static Tiles questionBox = new QuestionBox(8,Assets.questionBox);
	public static Tiles mountain = new Mountain(5,Assets.mountain);
	public static Tiles bush = new Bush(6,Assets.bush); 
	public static Tiles box = new Box(7,Assets.box);
	
	protected int width, height;
	protected int id;
	protected BufferedImage texture;
	
	public Tiles(int id,BufferedImage texture,int width,int height){
		
		this.id = id;
		this.texture = texture;
		this.height = height;
		this.width = width;
		
		tiles[256] = null;
	}
	
	public void render(Graphics g,int x,int y){
		
		g.drawImage(texture, x, y,width + 1,height + 1, null);
	}


	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getId(){
		return id;
	}
}
