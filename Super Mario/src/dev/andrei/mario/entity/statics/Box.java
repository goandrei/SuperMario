package dev.andrei.mario.entity.statics;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.andrei.mario.entity.Player;
import dev.andrei.mario.game.Handler;

public class Box extends Statics{

	public Box(BufferedImage texture,Handler handler, int x, int y) {
		super(texture,handler, x, y);
	
		bounds.width = 30;
		bounds.height = 32;
		
		solid = true;
	}

	@Override
	public void render(Graphics g) {
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCollision() {
		
		Player p = handler.getWorld().getPlayer();
		
		if(p.getX() < x && p.getBounds().y > y){
			p.setX(x - p.getWidth());
			return;
		}
		
		if(((p.getX() + p.getWidth() > x && p.getX() + p.getWidth() <= x + width) || (p.getX() <= x + width && p.getX() >= x )) && p.getBounds().y < y){
			p.setY(y - p.getHeight() + 20);
			p.setSpeedY(0);
			return;
		}
		
		if(p.getX() > x)
			p.setX(x + width + 1);
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int) (x - handler.getWorld().getxOffset()),y,bounds.width,bounds.height);
	}
}
