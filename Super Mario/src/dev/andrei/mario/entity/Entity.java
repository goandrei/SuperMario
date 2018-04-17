package dev.andrei.mario.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.andrei.mario.game.Handler;

public abstract class Entity {

	protected int x, y;
	protected int width, height;
	protected Rectangle bounds = new Rectangle();
	protected Handler handler;
	protected boolean solid;
	
	public Entity(Handler handler,int x,int y,int width,int height){
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.handler = handler;
	}
	
	public abstract void render(Graphics g);
	
	public abstract void update();
	
	public abstract void onCollision();
	
	public int getX(){
		return x;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getY(){
		return y;
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int) (x - handler.getWorld().getxOffset() - 1),y + bounds.y, bounds.width, bounds.height);
	}
	
	public boolean isSolid(){
		return solid;
	}
	
}
