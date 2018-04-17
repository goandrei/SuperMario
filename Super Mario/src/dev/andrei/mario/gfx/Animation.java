package dev.andrei.mario.gfx;

import java.awt.image.BufferedImage;

public class Animation {

	private BufferedImage texture[];
	private int index;
	private int frames;
	private int delay;
	private long lastTime, now, timer = 0;
	
	public Animation(BufferedImage texture[],int frames,int delay){
		
		this.texture = texture;
		this.frames = frames;
		this.delay = delay;
		
		lastTime = System.currentTimeMillis();
	}
	
	public void update(){
		
		now = System.currentTimeMillis();
		timer += now - lastTime;
		lastTime = now;
		
		if(timer > delay){
			index = (index + 1) % frames;
			timer = 0;
		}	
	}
	
	public BufferedImage getCurrentFrame(){
		return texture[index];
	}
	
	public void setIndex(int index){
		this.index = index;
	}
	
	public void setDelay(int delay){
		this.delay = delay;
	}
	
	
	
}
