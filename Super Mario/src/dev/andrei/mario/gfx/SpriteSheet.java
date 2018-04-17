package dev.andrei.mario.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage texture;
	
	public SpriteSheet(String path){
		
		texture = ImageLoader.loadImage(path);
	}
	
	public BufferedImage crop(int x,int y,int width,int height){
		return texture.getSubimage(x, y, width, height);
	}
}
