package dev.andrei.mario.entity.statics;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.andrei.mario.entity.Entity;
import dev.andrei.mario.game.Handler;

public abstract class Statics extends Entity{

	protected final int width = 32, height = 32;
	protected BufferedImage texture;
	
	public Statics(BufferedImage texture,Handler handler, int x, int y) {
		super(handler, x, y, 32, 32);
		this.texture = texture;
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	

}
