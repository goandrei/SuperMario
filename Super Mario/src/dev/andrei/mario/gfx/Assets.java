package dev.andrei.mario.gfx;

import java.awt.image.BufferedImage;

public class Assets {

	private SpriteSheet sheet = new SpriteSheet("/res/textures/finalsheet.png");
	public final static int TILE_WIDTH = 32, TILE_HEIGHT = 32;
	
	public static BufferedImage ground, money, questionBox, box, pipe, 
								smallCloud, bigCloud, mountain, bush, sky;
	public static BufferedImage[] front, backwards, standingLeft, standingRight, enemy;
	
	public void init(){
		
		front = new BufferedImage[3];
		backwards = new BufferedImage[3];
		
		standingLeft = new BufferedImage[2];
		standingRight = new BufferedImage[2];
		
		enemy = new BufferedImage[2];
		
		standingLeft[0] = standingLeft[1] = sheet.crop(TILE_WIDTH + TILE_WIDTH / 2 + 2, 0, TILE_WIDTH / 2, TILE_HEIGHT);
		standingRight[0] = standingRight[1] = sheet.crop(0, TILE_HEIGHT * 4, TILE_WIDTH / 2 - 2, TILE_HEIGHT);
		
		front[2] = sheet.crop(TILE_WIDTH / 2 + TILE_WIDTH + 2, TILE_HEIGHT * 4, TILE_WIDTH / 2 - 3, TILE_HEIGHT);
		front[1] = sheet.crop(TILE_WIDTH + 2, TILE_HEIGHT * 4, TILE_WIDTH / 2 - 3, TILE_HEIGHT);
		front[0] = sheet.crop(TILE_WIDTH / 2 + 2, TILE_HEIGHT * 4, TILE_WIDTH / 2 - 3, TILE_HEIGHT);
		

		backwards[0] = sheet.crop(TILE_WIDTH + 2, 0, TILE_WIDTH / 2 - 3, TILE_HEIGHT);
		backwards[1] = sheet.crop(TILE_WIDTH / 2 + 2, 0, TILE_WIDTH / 2 - 3, TILE_HEIGHT);
		backwards[2] = sheet.crop(0 + 2, 0, TILE_WIDTH / 2 - 3, TILE_HEIGHT);
		
		enemy[0] = sheet.crop(TILE_WIDTH, TILE_HEIGHT * 12, TILE_WIDTH, TILE_HEIGHT);
		enemy[1] = sheet.crop(TILE_WIDTH * 2, TILE_HEIGHT * 12, TILE_WIDTH, TILE_HEIGHT);
		
		ground = sheet.crop(0, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
		money = sheet.crop(TILE_WIDTH, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
		questionBox = sheet.crop(TILE_WIDTH * 3, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
		box = sheet.crop(0, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT);
		pipe = sheet.crop(TILE_WIDTH, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT);
		smallCloud = sheet.crop(TILE_WIDTH * 2, TILE_HEIGHT * 2, TILE_WIDTH * 2 - 1,TILE_HEIGHT * 2 - 1);
		bigCloud = sheet.crop(0, TILE_HEIGHT * 5, TILE_WIDTH * 4, 2 * TILE_HEIGHT);
		bush = sheet.crop(0, TILE_HEIGHT * 7, TILE_WIDTH * 4, 2 * TILE_HEIGHT);
		mountain = sheet.crop(0, TILE_HEIGHT * 9, TILE_WIDTH * 4, 2 * TILE_HEIGHT);
		sky = sheet.crop(0, TILE_HEIGHT * 11, TILE_WIDTH, TILE_HEIGHT);
	}
	
}
