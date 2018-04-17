package dev.andrei.mario.entity.statics;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.andrei.mario.entity.Player;
import dev.andrei.mario.game.Handler;
import dev.andrei.mario.gfx.Assets;

public class QuestionBox extends Statics{

	private boolean coin = false;
	private boolean taken = false;
	private boolean empty = false;
	
	public QuestionBox(BufferedImage texture, Handler handler, int x, int y) {
		super(texture, handler, x, y);
		
		bounds.width = 30;
		bounds.height = 32;
		
		solid = true;
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(texture, (int) (x - handler.getWorld().getxOffset()), y, null);
		if(coin)
			if(!taken)
				g.drawImage(Assets.money, (int) (x - handler.getWorld().getxOffset()), y - height, null);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCollision() {
		
		Player p = handler.getWorld().getPlayer();
		
		if(p.getY() + p.getHeight() > y && p.getY() + p.getHeight()  > y + height){
			p.setSpeedY((p.getSpeedY() * (-1)));
			p.setY(y + height);
			if(!empty){
				coin = true;
				empty = true;
			}
			return;
		}
		
		if(p.getY() < y){
			p.setY(y - p.getHeight() + 20);
			p.setSpeedY(0);
			if(coin && !taken){
				taken = true;
				handler.getWorld().getScore().addCoin();
			}
			return;
		}
		
		//if(p.getX() > x)
		//	p.setX(x + width + 1);
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int) (x - handler.getWorld().getxOffset()),y,bounds.width,bounds.height);
	}


}
