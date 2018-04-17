package dev.andrei.mario.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import dev.andrei.mario.game.Handler;
import dev.andrei.mario.gfx.Assets;

public class Enemy extends Entity{

	private BufferedImage[] texture;
	private int speedX = 1;
	private int index = 0;
	private boolean alive = true;
	private boolean taken = false;
	private long timer, delay = 300, now, lastTime;
	
	public Enemy(BufferedImage[] texture,Handler handler, int x, int y) {
		super(handler, x, y, 32, 32);
		
		this.texture = texture;
		
		bounds.width = 30;
		bounds.height = 32;
		
		solid = true;
		
		lastTime = System.currentTimeMillis();
	}
	
	public boolean checkCollision(){
		
		ArrayList<Entity> list = handler.getWorld().getEntityManager().getEntities();
		int len = list.size();
		
		for(int i = 0;i < len; ++i){
			Entity e = list.get(i);
			if(e.equals(this))
				continue;
			if(this.getBounds().intersects(e.getBounds())){
				return true;
			}
		}
		
		return false;
	}
	
	
	private void move(){
		
		x += speedX;
		//System.out.println("Latime: " + (int) (x + width - handler.getWorld().getxOffset()) / Assets.TILE_WIDTH);
		int id = handler.getWorld().getTileId(15, (x + width) / Assets.TILE_WIDTH);
		
		if(id == 1){
			speedX *= (-1);
		}
		
		id = handler.getWorld().getTileId(15, x / Assets.TILE_WIDTH);
		
		if(id == 1){
			speedX *= (-1);
		}
				
		if(checkCollision())
			speedX *= (-1);
	}
	
	
	public BufferedImage getCurrentFrame(){
		
		if(alive)
			return texture[index];
		else
			return Assets.money;
	}

	@Override
	public void render(Graphics g) {
		
		if(!taken){
			g.drawImage(getCurrentFrame(), (int) (x - handler.getWorld().getxOffset()), y, null);
			//g.fillRect(rect.x,rect.y,rect.width,rect.height);
		}
	}

	@Override
	public void update() {
		move();
	}
	
	public void setAlive(boolean alive){
		this.alive = alive;
	}

	@Override
	public void onCollision() {
		
		Player p = handler.getWorld().getPlayer();
		
		if(alive){
			if(p.getX() < x && p.getBounds().y > getBounds().y){
				p.setX(x);
				handler.getWorld().getPlayer().setX(x - handler.getWorld().getPlayer().getWidth() - 50);
				p.updateMaxSpeedX(-1);
				return;
			}
			
			if(p.getX() >= x && p.getBounds().y > getBounds().y){
				p.setX(x + width + 50);
				p.updateMaxSpeedX(-1);
				return;
			}
			
			now = System.currentTimeMillis();
			timer += now - lastTime;
			lastTime = now;
			
			if(timer > delay){
				timer = 0;
				++index;
				if(index > 1){
					setAlive(false);
					speedX = 0;
					p.setY(p.getY() - 100);
					p.setX(p.getX() - 50);
					p.setSpeedY(1);
				}
			}
			
		}
		else{
			handler.getWorld().getScore().addCoin();
			taken = true;
			solid = false;
		}
	}
}

