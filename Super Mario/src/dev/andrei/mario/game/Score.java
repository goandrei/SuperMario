package dev.andrei.mario.game;

import java.awt.Font;
import java.awt.Graphics;

import dev.andrei.mario.gfx.Assets;

public class Score {

	private int coins;
	private long lastTime, now;
	private long score;
	private int coinX, coinY;
	private int timeX, timeY;
	
	private Handler handler;
	
	public Score(Handler handler){
		
		coins = 0;
		score = 0;
		
		coinX = 770;
		coinY = 10;
		timeX = 10;
		timeY = 30;
		
		this.handler = handler;
	}
	
	public void start(){
		
		lastTime = System.currentTimeMillis();
	}
	
	public void update(){
		
		now = System.currentTimeMillis();
		score += (now - lastTime);
		lastTime = now;
	}
	
	public void render(Graphics g){
		
		g.drawImage(Assets.money,coinX,coinY,null);
		g.setFont(new Font("Arial",Font.BOLD,28));
		g.drawString(Integer.toString(coins), coinX - 20, coinY + 25);
		
		g.drawString(Long.toString(score), timeX, timeY);
	}
	
	public void addCoin(){
		++coins;
	}
}
