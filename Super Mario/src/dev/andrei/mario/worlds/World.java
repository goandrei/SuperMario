package dev.andrei.mario.worlds;

import java.awt.Graphics;

import dev.andrei.mario.entity.Enemy;
import dev.andrei.mario.entity.EntityManager;
import dev.andrei.mario.entity.Player;
import dev.andrei.mario.entity.statics.Box;
import dev.andrei.mario.entity.statics.QuestionBox;
import dev.andrei.mario.game.Handler;
import dev.andrei.mario.game.Score;
import dev.andrei.mario.gfx.Assets;
import dev.andrei.mario.sound.Sound;
import dev.andrei.mario.tiles.Tiles;
import dev.andrei.mario.utils.Utils;

public class World {

	private Handler handler;
	
	private int myTiles[][];
	private int width, height;
	
	private Player player;
	
	private EntityManager entityManager;
	
	private Score score;
	
	private float xOffset = 0;
	
	private int start, end , width_in_tiles;
	
	private final int initX = 30, initY = 415;
	
	public World(Handler handler,String path){
		
		this.handler = handler;

		player = new Player(handler,initX,initY);
	
		width_in_tiles = handler.getWidth() / Assets.TILE_WIDTH + 1;
		
		entityManager = new EntityManager(handler);
		
		start = 0;
		end = start + width_in_tiles + 1;
		
		score = new Score(handler);
		
		initialize(path);
	}
	
	public void initialize(String path){
		
		loadWorld(path);
		
		int i, j;
		
		player.init();
		
		for(i = 0;i < height; ++i)
			for(j = 0;j < width; ++j){
				if(myTiles[i][j] == 7)
					entityManager.addEntity(new Box(Assets.box,handler,j * Assets.TILE_WIDTH,i * Assets.TILE_HEIGHT));
				if(myTiles[i][j] == 4){
					entityManager.addEntity(new Enemy(Assets.enemy,handler,j * Assets.TILE_WIDTH,i * Assets.TILE_HEIGHT));
					myTiles[i][j] = 1;
				}
				if(myTiles[i][j] == 8){
					entityManager.addEntity(new QuestionBox(Assets.questionBox, handler, j * Assets.TILE_WIDTH,i * Assets.TILE_HEIGHT));
					myTiles[i][j] = 1;
				}
			}
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	private void loadWorld(String path){
		
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		
		myTiles = new int[height][width];
		
		for(int i = 0;i < height; ++i)
			for(int j = 0;j < width; ++j)
				myTiles[i][j] = Utils.parseInt(tokens[2 + i * width + j]);
		
	}
	
	private void centerOnPlayer(){
	
		float lerp = 1.0f; 
		xOffset = lerp * (player.getX() - handler.getWidth() / 3 + player.getWidth() / 2); 
		
		if(xOffset < 0)
			xOffset = 0;
		
		if(xOffset > width * Assets.TILE_WIDTH - handler.getWidth())
			xOffset = width * Assets.TILE_WIDTH - handler.getWidth();
			
	}
	
	public void update(){
		
		player.update();
		entityManager.update();
		score.update();
		centerOnPlayer();
	}
	
	public Tiles getTile(int i,int j){
		
		return Tiles.tiles[myTiles[i][j]];
	}
	
	public void checkTiles(Graphics g,int i,int j,Tiles tile){
		
		switch(myTiles[i][j]){
		case 2:
			tile.render(g, (int) (Assets.TILE_WIDTH * (j - 1) - xOffset), Assets.TILE_HEIGHT * i);
			break;
		case 3:
			tile.render(g, (int) (Assets.TILE_WIDTH * (j - 3) - xOffset), Assets.TILE_HEIGHT * i);
			break;
		case 5:
			tile.render(g, (int) (Assets.TILE_WIDTH * (j - 3) - xOffset), Assets.TILE_HEIGHT * i);
			break;
		case 6:
			tile.render(g, (int) (Assets.TILE_WIDTH * (j - 3) - xOffset), Assets.TILE_HEIGHT * i);
			break;
		default:
			tile.render(g, (int) (Assets.TILE_WIDTH * j - xOffset), Assets.TILE_HEIGHT * i);
			break;
		}
	}
	
	
	public void render(Graphics g){		
			
		
		for(int i = 0;i < height; ++i)
			for(int j = 0;j < width; ++j){
				Tiles tile = getTile(i,j);
				if(tile != null){
					int auxX = (int) (Assets.TILE_WIDTH * j - xOffset);
					if(auxX > -32 && auxX < handler.getWidth() + 128)
						checkTiles(g,i,j,tile);
				}
			}

		entityManager.render(g);
		player.render(g);
		score.render(g);
	}
	
	public void updateOffset(float xAmt){
		
		xOffset	+= xAmt;
	}
	
	public float getxOffset(){
		return xOffset;
	}
	
	public void setOffset(float xOffset){
		this.xOffset = xOffset;
	}
	
	public void setStart(int start){
		
		this.start += start;
		if(start < 0)
			this.start = 0;
	}
	
	public int[][] getMyTiles(){
		return myTiles;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public int getTileId(int i, int j){
		
		return myTiles[i][j];
	}
	
	public Score getScore(){
		return score;
	}
}
